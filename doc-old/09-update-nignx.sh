#!/usr/bin/env bash

## 更新nginx类型：lb、ab
## lb：load balance，负载均衡，同时运行最新版本
## ab：两套服务器同时跑，a、b环境
type=$1

## 当前更新的版本号，前端调用的版本号
currentVersion=$2

## 上一次更新的版本号
previous_version=$3

if [[ -z $type ]] || [[ -z $currentVersion ]];then
    echo "请输入参数，负载均衡需要两个参数，例如：lb v1；两个版本同时运行需要三个参数，例如：ab v2 v1；参数1：类型（lb、ab）,参数2：更新的版本号，参数3：上一个版本号"

elif [[ $type == "ab" ]]  && [[ -z $previous_version ]];then
    echo "type=ab时，需要三个参数；参数1：类型（lb、ab）,参数2：更新的版本号，参数3：上一个版本号"

else
    if [[ $type == "lb" ]];then
        echo "当前选择使用负载均衡更新nginx"
        ## cp nginx-version-load-balance.conf dev-nginx.conf
        ## sed -i 's/\/current_version\//\/'$currentVersion'\//g' `grep "/current_version/" -rl --include="dev-nginx.conf" */`

    else
        echo "当前选择使用ab版本更新nginx"
        ## cp nginx-version-load-balance.conf dev-nginx.conf
        ## sed -i 's/\/current_version\//\/'$currentVersion'\//g' `grep "/current_version/" -rl --include="dev-nginx.conf" */`
        ## sed -i 's/\/previous_version\//\/'$previous_version'\//g' `grep "/previous_version/" -rl --include="dev-nginx.conf" */`

    fi

    result=`../sbin/nginx -t`
    if [[ $result ==  *"test is successful"* ]];then
        echo "配置文件有问题，请检查！！！"
    else
        echo "开始重启nginx"
        ## ../sbin/nginx -s reload
        echo "重启完成"
    fi

fi


