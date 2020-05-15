#!/usr/bin/env bash

#### 全量更新：先更新服务器组A，再更新服务器组B
#### 用于当前普通部署，直接部署负载均衡

## 选择需要运行的代码目录
chooseCode(){

    read -p "请选择代码目录：A或B:" targetCode;

    if [[ -z $targetCode ]] || [[ $targetCode != "A" && $targetCode != "B" ]];then
        chooseCode;
    fi

}

chooseCode;

## 1. 更新服务器组A
echo "############################## 开始重启服务器组A ##################################"
sh 09-run-by-params.sh $targetCode A

## 2. 更新额外的label-ex：下单校验使用
#echo "############################## 开始重启Mq-server上的label-ex ##################################"
#sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.95 "cd /17feia/nfsOthersFiles/parcels/$targetCode/eship-label-ex; sh startup.sh"
#result=$?
#if [ $result != 0 ];then
#    currentTime=$(date "+%Y-%m-%d %H:%M:%S")
#    echo "[ERROR] $currentTime -启动失败 -代码目录：$targetCode -服务器组：B -服务器IP：172.19.108.95 -服务名称：eship-label-ex" >> ./startup.log
#    echo "【启动失败】"
#else
#    currentTime=$(date "+%Y-%m-%d %H:%M:%S")
#    echo "[INFO] $currentTime -启动成功 -代码目录：$targetCode -服务器组：B -服务器IP：172.19.108.95 -服务名称：eship-label-ex" >> ./startup.log
#    echo "启动成功"
#fi

echo "开始休眠120秒..."
sleep 120s

## 3. 更新服务器组B
echo "############################## 开始重启服务器组B ##################################"
#sh 09-run-by-params.sh $targetCode B

## 4. 更新额外的label-ex：欧洲服务器、野鸡
echo "############################## 开始重启欧洲服务器、野鸡 ##################################"
echo "需要手动复制代码过去搞！！！"

