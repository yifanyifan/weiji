#!/bin/bash
source ./20-functions.sh

echo '=================================================================='
echo '从 21-package.sh 拷贝而来，去掉每一步的询问, 直接执行'
echo 'step1: 打包eship-common, eship-export-data, eship-label-ex, customer'
echo 'setp2: 压缩全部jar 到 /parcels/deploy/date/, 不包含三方lib。 默认将压缩包保存到windows D盘 parcels目录 或 linux的 /d/parcels/目录'
echo '2019-12-9 by LJ'
echo '=================================================================='




echo -e "\n\n============================================================更新代码, LJ@2019-12-13\n"


    git reset --hard
    git pull





echo -e "\n\n============================================================编译, LJ@2019-12-9\n"


    cd ./eship-common
    echo -e "\n\n package eship-common"
    mvn install -Dmaven.test.skip=true

    cd ../
    echo -e "\n\n 打包全部工程"
    mvn clean install -Dmaven.test.skip=true

    result=$?
    if [ $result != 0 ];then
        echo "执行mvn clean package 出错, 退出执行.........."
        exit 1
    fi





echo -e "\n\n============================================================压缩拷贝打好的包, LJ@2019-12-9\n"

current_date=`date +%Y%m%d%H%M%S`
targetPath=/d/parcels/

mkdir -p $targetPath # 默认将压缩包保存到D盘 parcels 或 linux的 /d/parcels/目录


projects=("eship-export-data" "eship-label-ex" "eship-customer-center")
for project in ${projects[@]};
do

    cd $project/target
    rm -fr $project/*
    mkdir $project $project/lib $project/conf

    cp -r *.jar $project/


        cp -r lib/* $project/lib/



        cp -r conf/* $project/conf/


    cat ../../22-startup.sh > $project/startup.sh
    cat ../../22-startup.sh > $project/test-startup.sh
    cat ../../20-functions.sh > $project/20-functions.sh
    setRemoteDebugInTestStartup $project #为测试startup设置调试端口

    zipFile=$project.tar.gz

    echo -e "\n\n 压缩和复制 ......"
    tar -cvf $zipFile $project
    cp -v $zipFile $targetPath

    cd ../../

done





echo -e "\n\n============================================================重新启动测试环境, LJ@2019-12-9\n"

    cp 23-unpress-startup-test-evn.sh $targetPath #拷贝测试环境启动命令到出包目录
    cp 20-functions.sh $targetPath #拷贝测试环境启动命令到出包目录
    cd $targetPath
    sh 23-unpress-startup-test-evn.sh
