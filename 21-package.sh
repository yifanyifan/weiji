#!/bin/bash
source ./20-functions.sh

echo '=================================================================='
echo 'step1: 打包weiji-eureka weiji-config weiji-service weiji-customer'
echo 'setp2: 压缩全部jar 到 /parcels/deploy/date/, 不包含三方lib。 默认将压缩包保存到windows D盘 weiji目录 或 linux的 /d/weiji/目录'
echo '=================================================================='

echo -e "\n\n============================================================更新代码\n"
read -p "是否pull, 更新代码?[y/n]?" isPull
if [[ $isPull == 'y' ]];then
    git reset --hard
    git pull
fi


echo -e "\n\n============================================================编译\n"
read -p "是否再编译[y/n]?" isCompile
if [ $isCompile == 'y' ]; then
    #cd ./eship-common
    #echo -e "\n\n package eship-common"
    #mvn install -Dmaven.test.skip=true

    #cd ../
    echo -e "\n\n 打包全部工程"
    mvn clean install -Dmaven.test.skip=true

    result=$?
    if [ $result != 0 ];then
        echo "执行mvn clean package 出错, 退出执行.........."
        exit 1
    fi
fi



:<<MULTILINECOMMENT
echo -e "\n\n============================================================压缩拷贝打好的包, LJ@2019-12-9\n"
read -p "是否包含三方包[y/n]?" isFat
read -p "是否包含conf[y/n]?" isConf

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

    if [ $isFat == 'y' ]; then #包含三方包

        cp -r lib/* $project/lib/
    else
        cp -r lib/eship* $project/lib/
    fi


    if [ $isConf == 'y' ]; then #包含conf

        cp -r conf/* $project/conf/
    fi

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
read -p "是否重新启动测试环境[y/n]?" isTestStartup

if [[ $isTestStartup == 'y' ]];then
    cp 23-unpress-startup-test-evn.sh $targetPath #拷贝测试环境启动命令到出包目录
    cp 20-functions.sh $targetPath #拷贝测试环境启动命令到出包目录
    cd $targetPath
    sh 23-unpress-startup-test-evn.sh
fi
MULTILINECOMMENT