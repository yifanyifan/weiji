#!/bin/bash

enviroment=$1
keyword=$2

# 打包
if [ -z $2 ] || [[ "vue-customer-center" == *$keyword* ]];then
    echo 开始打包vue-customer-center...
    cd ../vue-customer-center
    rm -rf dist
    cnpm run build
fi


if [ -z $2 ] || [[ "vue-finance-center" =~ $keyword ]];then
    echo 开始打包vue-finance-center...
    cd ../vue-finance-center
    rm -rf dist
    cnpm run build
fi

if [ -z $2 ] || [[ "vue-operation-center" =~ $keyword ]];then
    echo 开始打包vue-operation-center...
    cd ../vue-operation-center
    rm -rf dist
    cnpm run build
fi

if [ -z $2 ] || [[ "vue-product-center" =~ $keyword ]];then
    echo 开始打包vue-product-center...
    cd ../vue-product-center
    rm -rf dist
    cnpm run build
fi

if [ -z $2 ] || [[ "vue-service-sales-center" =~ $keyword ]];then
    echo 开始打包vue-service-sales-center...
    cd ../vue-service-sales-center
    rm -rf dist
    cnpm run build
fi


if [ -z $2 ] || [[ "vue-author-center" =~ $keyword ]];then
    echo 开始打包vue-author-center...
    cd ../vue-author-center
    rm -rf dist
    cnpm run build
fi
# 运行



echo 打包完成...