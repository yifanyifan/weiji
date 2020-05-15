#!/bin/bash

## 打包时间戳
date=`date +%Y%m%d%H%M%S`

## 存放打包文件的目录
targetPath=$1

## 默认目录
if [ -z $targetPath ];then
    targetPath=/eship/deploy
fi

## 加上时间戳
targetPath=$targetPath/java/$date

echo 打包文件目录路径：$targetPath



# 修改配置文件
redisHost=10.105.117.39
javaNginxHost=10.105.222.68
version=product
rabbitmqAddresses=10.105.117.39
rabbitmqUsername=admin
rabbitmqPassword=admin


# 打包
echo 开始出包.......................................
echo 开始编译，打包..................................
mvn clean package install -Dmaven.test.skip=true

result=$?
if [ $result != 0 ];then
    echo "执行mvn clean package install出错.........."
    echo "打包错误！"
    exit 1
fi

# -------------------------------- 修改配置文件 -------------------------------start
# 1. redis ip pass
sed -i 's#^redis.host.*$#redis.host='$redisHost' #' `grep "redis.host" -rl --include="application.properties" */target/conf/`
sed -i 's#^redis.port.*$#redis.port=6379#' `grep "redis.port" -rl --include="application.properties" */target/conf/`
sed -i 's#^redis.pass.*$#redis.pass=attackRedis#' `grep "redis.pass" -rl --include="application.properties" */target/conf/`

sed -i 's#^.*host: 192.168.8.169$#    host: '$redisHost' #' `grep "host: 192.168.8.169" -rl --include="application.yaml" */target/conf/`
sed -i 's#^.*port: 6380$#    port: 6379#' `grep "6380" -rl --include="application.yaml" */target/conf/`
sed -i 's#^.*password.*$#    password: attackRedis#' `grep "123456789" -rl --include="application.yaml" */target/conf/`

# 2. 修改db ip pass
sed -i 's#192.168.8.168#'$redisHost'#' `grep "jdbc.url" -rl --include="application.properties" */target/conf/`
sed -i 's#^jdbc\.password.*$#jdbc.password=HrxNkXPMG5V7#' `grep "jdbc.password" -rl --include="application.properties" */target/conf/`

# 3. 修改zookeeper的ip,缓存目录
sed -i 's#address=\"192.168.8.168:2181\"#address=\"'$redisHost':2181\"#' `grep "dubbo:registry" -rl --include="*dubbox.xml" */target/conf/`
# 如果windows打包把目录给了，这里改回来
sed -i 's#file=.*/bubbo#file=\"${user.home}/bubbo#' `grep "dubbo:registry" -rl --include="*dubbox.xml" */target/conf/`

# 4 修改dubbo 自己ip
## sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20880\" serialization=\"kryo\"/>#' `grep "dubbo:protocol" -rl --include="*dubbox.xml" */target/conf/`
sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20880\" serialization=\"kryo\"/>#' eship-order-service/target/conf/applicationContext-dubbox.xml
sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20881\" serialization=\"kryo\"/>#' eship-finance-service/target/conf/applicationContext-dubbox.xml
sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20882\" serialization=\"kryo\"/>#' author-service/target/conf/applicationContext-dubbox.xml
sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20883\" serialization=\"kryo\"/>#' eship-product-service/target/conf/applicationContext-dubbox.xml
sed -i 's#^.*dubbo:protocol.*$#\t<dubbo:protocol name=\"dubbo\" host=\"'$javaNginxHost'\" port=\"20884\" serialization=\"kryo\"/>#' eship-sales-service/target/conf/applicationContext-dubbox.xml

# 5. 修改dubbo的版本
sed -i 's#^.*dubbo:provider.*$#\t<dubbo:provider payload=\"'${dubbo.protocol.dubbo.payload}'\" timeout=\"10000\" version=\"'$version'\" filter="dubboContextFilter"/>#' `grep "dubbo:provider" -rl --include="*dubbox.xml" */target/conf/`
sed -i 's#^.*dubbo:consumer.*$#\t<dubbo:consumer timeout=\"10000\" version=\"'$version'\" check=\"false\" />#' `grep "dubbo:consumer" -rl --include="*dubbox.xml" */target/conf/`

# 6. 修改  http请求 ip
sed -i 's#http://label-internal.eship.com#http://'$javaNginxHost'#' */target/conf/application.yaml
sed -i 's#http://label-ex.eship.com#http://'$javaNginxHost'#' */target/conf/application.properties

# 修改rabbit配置
sed -i 's#^rabbitmq.addresses.*$#rabbitmq.addresses='$rabbitmqAddresses' #' `grep "rabbitmq.addresses" -rl --include="application.properties" */target/conf/`
sed -i 's#^rabbitmq.username.*$#rabbitmq.username='$rabbitmqUsername' #' `grep "rabbitmq.username" -rl --include="application.properties" */target/conf/`
sed -i 's#^rabbitmq.password.*$#rabbitmq.password='$rabbitmqPassword' #' `grep "rabbitmq.password" -rl --include="application.properties" */target/conf/`

# -------------------------------- 修改配置文件 -------------------------------end


for file in $(ls | grep -E 'eship|author')
do
  if [ -d "$file" -a $file != "eship-common" -a $file != "eship-dubbo-core" -a $file != "eship-service-core" -a $file != "eship-web-core" ];then
        echo "####################################################"
        echo 开始处理$file

        echo 开始复制包到目标目录............................
        cd $file/target




        jarFileName=`ls *.jar`
        echo 找到jar包：$jarFileName

        echo 开始复制文件到指定目录..........................
        mkdir $file
        cp -r conf $file/conf 
        cp -r lib $file/lib
        cp -r *.jar $file/
        echo "#!/bin/bash" > $file/startup.sh
        echo "nohup java -jar $jarFileName &" >> $file/startup.sh
        echo 复制文件完成....................................


        zipFileName=$file-$date.tar.gz
        echo 开始压缩.......................................
        tar cf $zipFileName $file
        echo 压缩完成.......................................
        

        echo 复制文件到出包目录..............................
        mkdir -p $targetPath
        cp $zipFileName $targetPath/

        ## 返回主目录
        cd ../../
    
  fi
done

echo 打包完成,文件目录路径：$targetPath
  