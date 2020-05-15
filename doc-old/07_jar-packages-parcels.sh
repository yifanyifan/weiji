#!/bin/bash
# 例如： sh 06_jar-packages-prod.sh d:\\temp

echo "修改配置ip"
#sh 06_modify_prod_cfg.sh

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


# 打包
echo 开始出包.......................................
echo 开始编译，打包..................................
mvn clean package -Dmaven.test.skip=true

result=$?
if [ $result != 0 ];then
    echo "执行mvn clean package 出错.........."
    echo "打包错误！"
    exit 1
fi



for file in $(ls | grep -E 'customer|export|label-ex')
do

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

      ## 如果是dubbo服务，启动的时候加prod
      ## 如果是springboot的，启动的时候加-Dspring.profiles.active=prod
      if [[ $file = *"service"* ]]; then
          echo "nohup java -jar $jarFileName prod >/dev/null 2>&1 &" >> $file/startup.sh
      else
          cat ../../22-startup.sh > $file/startup.sh
          cat ../../22-startup-local.sh > $file/startup-local.sh
          cat ../../22-startup-download.sh > $file/startup-download.sh
          cat ../../20-functions.sh > $file/20-functions.sh
      fi


      echo 复制文件完成....................................


      zipFileName=$file-$date.tar.gz
      echo 开始压缩.......................................
      tar cf $zipFileName $file
      echo 压缩完成.......................................


      echo 复制文件到出包目录..............................
      mkdir -p $targetPath
      cp -v $zipFileName $targetPath/

      ## 返回主目录
      cd ../../


done

echo 打包完成,文件目录路径：$targetPath
