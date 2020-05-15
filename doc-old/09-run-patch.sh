#!/usr/bin/env bash

### 补丁更新，需要重启两个服务器的同一个服务

## 代码目录A、B；必选
targetCode=$1

## 待重启的服务，没选择就是所有服务
targetService=$2

if [[ -z $targetCode || -z $targetService ]];then
    echo "请输入参数：代码目录(必填) 待重启服务名称(必填))；例如：A author-center；总共2个参数"
    echo "可选服务名称：eship-customer-center、eship-export-data、eship-label-ex"

elif [[ -n $targetService &&  $targetService != "eship-customer-center" && $targetService != "eship-export-data" &&  $targetService != "eship-label-ex" ]];then
    echo "输入服务名称错误！！！"
    echo "可选服务名称：eship-customer-center、eship-export-data、eship-label-ex"
else
    echo "使用【$targetCode】组代码部署服务【$targetService】"

    echo "开始在服务器组A重启"
    sh 09-run-by-params.sh $targetCode A $targetService
    echo "休眠100秒"
    sleep 100s
    echo "开始在服务器组B重启"
    sh 09-run-by-params.sh $targetCode B $targetService

fi