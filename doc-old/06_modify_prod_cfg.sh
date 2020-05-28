#!/usr/bin/env bash

echo "开始修改prod配置文件"
param_dubboxVersion=prod

echo "------------------------------------------------------"
read -p "请选择部署到的服务器组：A或B:" service_group
while [ -n "$service_group" ] && [[ $service_group != "A" &&  $service_group != "B" ]]; do
    read -p "请选择部署到的服务器组：A或B:" service_group
done
if [ -z $service_group ];then
    service_group="A";
    echo "未选择服务器组，默认使用：A"
fi
echo "选择的服务器组为：$service_group"

if [[ "A" == "$service_group" ]];then
    echo "当前选择的服务器是-----------A"

    param_dubboxVersion=$param_dubboxVersion"A"

    param_product_center_ip=172.19.108.97
    param_sales_center_ip=172.19.108.101
    param_author_center_ip=172.19.108.101
    param_finance_center_ip=172.19.108.101
    param_operator_center_ip=172.19.108.97
else
    echo "当前选择的服务器是-------------B"

    param_dubboxVersion=$param_dubboxVersion"B"

    param_product_center_ip=172.19.108.96
    param_sales_center_ip=172.19.108.93
    param_author_center_ip=172.19.108.93
    param_finance_center_ip=172.19.108.93
    param_operator_center_ip=172.19.108.96
fi

echo "dubbo的版本号为：$param_dubboxVersion"
echo "------------------------------------------------------"


echo "开始修改dubbo的版本号"
sed -i 's/param_dubboxVersion/'$param_dubboxVersion'/' `grep "param_dubboxVersion" -rl --include="application-prod.yml" */`

echo "开始修改dubbo服务提供者host和xxl-job客户端ip"
sed -i 's/param_product_center_ip/'$param_product_center_ip'/' `grep "param_product_center_ip" -rl --include="application-prod.yml" */`
sed -i 's/param_sales_center_ip/'$param_sales_center_ip'/' `grep "param_sales_center_ip" -rl --include="application-prod.yml" */`
sed -i 's/param_author_center_ip/'$param_author_center_ip'/' `grep "param_author_center_ip" -rl --include="application-prod.yml" */`
sed -i 's/param_finance_center_ip/'$param_finance_center_ip'/' `grep "param_finance_center_ip" -rl --include="application-prod.yml" */`
sed -i 's/param_operator_center_ip/'$param_operator_center_ip'/' `grep "param_operator_center_ip" -rl --include="application-prod.yml" */`


## 修改项目内部通过rest接口调用的版本号
echo "------------------------------------------------------"
read -p "请输入前端版本号（例如：v1）:" rest_version
if [ -z "$rest_version" ];then
    rest_version="v1"
    echo "未输入版本，默认使用：v1"
fi
echo "前端版本号为：$rest_version"

sed -i 's/\/v1\//\/'$rest_version'\//g' `grep "/v1/" -rl --include="application-prod.yml" */`


## zookeeper服务器参数化

## rabbitmq服务器参数化

## xxl-job服务器参数化


echo "修改完成"