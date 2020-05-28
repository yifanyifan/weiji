#!/bin/bash


echo '=================================================================='
echo '拷贝test环境配置文件到开发者配置文件'
echo 'Author:lj, Date:2019-6-18'
echo '=================================================================='

currentDir=$PWD
echo "当前目录=${currentDir}"


for file in $(ls | grep -E 'eship-customer-center|eship-export-data|eship-label-ex')
do
    echo -e "\n---------------------------------------------------------------"

    if [ -e ${file}/src/main/resources/ ]; then
        cd ${file}/src/main/resources/
    else
        echo "非法目录"
        continue
    fi


    echo "进入到 `pwd`"

    if [ -e application-test.yml ]; then
        cp -fv application-test.yml application-dev.yml
    fi

    cd ${currentDir}
done

sed -i 's#profiles: test$#profiles: dev#' `grep "profiles:" -rl --include="application-dev.yml" */`
sed -i 's#upload: /eship/nfsFiles/$#upload: X:/#' `grep "upload: /eship/nfsFiles/" -rl --include="application-dev.yml" */`


echo -e '\n\n=========================================================== ======='
echo "将Rabbit的端口5672(开发用) 修改为 端口 6672(测试用)， 并添加vhost"
echo 'Author:tsd, Date:2019-6-21'
echo '=================================================================='

# 修改开发环境的rabbit服务器, 测试在docker:6672, 开发在docker:5672
sed -i 's#addresses: 192.168.8.169:6672.*$#addresses: 192.168.8.169:5672#' `grep "addresses: 192.168.8.169:6672" -rl --include="application-dev.yml" */`


read -p "请输入rabbit vhost:" vhost
while [ -z "vhost" ]; do
    read -p "不能为空, 再输入:" vhost
done

echo "Begin modifing rabbit vhost: ${vhost}"

sed -i 's#^rabbitmq.vhost=/.*$#rabbitmq.vhost=/'${vhost}'#' `grep "rabbitmq.vhost=/" -rl --include="application.properties" */`
sed -i 's#vhost: /.*$#vhost: /'${vhost}'#' `grep "vhost: /" -rl --include="application-dev.yml" */`
sed -i 's#vhost_md:.*$#vhost_md: /'$vhost'#' `grep "vhost" -rl --include="application-dev.yml" */`




echo -e '\n\n=================================================================='
echo '修改 xxl 的 host ip, 与整个局域网中的开发机区别开来'
echo 'Author:lj, Date:2019-6-18'
echo '=================================================================='

read -p "请输入你的IP:" host
while [ -z "$host" ]; do
    read -p "不能为空, 再输入:" host
done

echo "Begin modifing xxl and dubbo: ${host}"
sed -i 's#ip:.*xxl-client-ip$#ip: '$host' \#xxl-client-ip#' `grep "xxl-client-ip" -rl --include="application-dev.yml" */`





#echo -e '\n\n=================================================================='
#echo '修改你的调试数据库前缀, 如果你在168上创建了自己的独立数据库。'
#echo 'Author:lj, Date:2019-6-18'
#echo '=================================================================='
#
#read -p "请输入你的调试数据库前缀(不输入的话直接使用测试环境DB 的 test 结尾的库: parcels_2_test):" dbPrefix
#
#if [ -z "$dbPrefix" ]; then
#    dbPrefix='test'
#fi
#echo "modify db to $dbPrefix"

# 不改数据库（label-ex是独立的数据库）
# sed -i 's#url: jdbc:mysql.*$#url: jdbc:mysql://192.168.8.168/parcels_2_'$dbPrefix'?useUnicode=true\&characterEncoding=utf-8#' `grep "jdbc" -rl --include="application-dev.yml" */`




