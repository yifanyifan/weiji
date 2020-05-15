#!/bin/bash
source ./20-functions.sh

echo '=================================================================='
echo 'step1: 打包weiji-eureka weiji-config weiji-service weiji-customer'
echo 'setp2: 压缩全部jar 到 /parcels/deploy/date/, 不包含三方lib。 默认将压缩包保存到windows D盘 weiji目录 或 linux的 /d/weiji/目录'
echo '=================================================================='

echo -e "\n\n============================================================更新代码, LJ@2019-12-13\n"
read -p "是否pull, 更新代码?[y/n]?" isPull

if [[ $isPull == 'y' ]];then

    git reset --hard
    git pull

fi


