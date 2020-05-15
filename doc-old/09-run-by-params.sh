#!/usr/bin/env bash

#### 根据参数任意启动，相当于一个公有方法，供其他shell调用
#### 选择代码目录、服务器组、服务名称

## 代码目录A、B；必选
targetCode=$1

## 服务器组A、B；必选
service_group=$2

## 待重启的服务，没选择就是所有服务
targetService=$3


## 重启服务方法
restartService(){
    if [[ "$service_group" == "A" || "$service_group" == "a" ]];then
            ## A组服务器ip
            if [[ "$targetDir" == "eship-customer-center/" ]];then
                targetIp=172.19.108.102
            elif [[ "$targetDir" == "eship-export-data/" ]];then
                targetIp=172.19.108.102
            elif [[ "$targetDir" == "eship-label-ex/" ]];then
                targetIp=172.19.108.102
            fi

        else
            ## B组服务器ip
            if [[ "$targetDir" == "eship-customer-center/" ]];then
                targetIp=172.19.108.111
            elif [[ "$targetDir" == "eship-export-data/" ]];then
                targetIp=172.19.108.111
            elif [[ "$targetDir" == "eship-label-ex/" ]];then
                targetIp=172.19.108.111
            fi

        fi

        ## 开始启动
        if [[ -n $targetIp ]];then
            echo "开始重启服务：/17feia/nfsOthersFiles/parcels/$targetCode/$targetDir($targetIp)"
            sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@$targetIp "cd /17feia/nfsOthersFiles/parcels/$targetCode/$targetDir; sh startup.sh"

            ## 启动结果
            result=$?
            if [ $result != 0 ];then
                currentTime=$(date "+%Y-%m-%d %H:%M:%S")
                echo "[ERROR] $currentTime -启动失败 -代码目录：$targetCode -服务器组：$service_group -服务器IP：$targetIp -服务名称：$targetDir" >> ./startup.log
                echo "【启动失败】"
            else
                currentTime=$(date "+%Y-%m-%d %H:%M:%S")
                echo "[INFO] $currentTime -启动成功 -代码目录：$targetCode -服务器组：$service_group -服务器IP：$targetIp -服务名称：$targetDir" >> ./startup.log
                echo "启动成功"
            fi
        fi

        ## 重置ip
        targetIp=
}


if [[ -z $targetCode || -z $service_group ]];then
    echo "请输入参数：代码目录(必填) 服务器组(必填) 待重启服务名称(选填)；例如：A A author-center；总共3个参数，待重启服务名称可以为空"
    echo "可选服务名称：eship-customer-center、eship-export-data、eship-label-ex"

elif [[ -n $targetService && $targetService != "author-center" && $targetService != "eship-operator-center" && $targetService != "eship-sales-center" && $targetService != "eship-finance-center" && $targetService != "eship-customer-center" && $targetService != "eship-product-center" && $targetService != "eship-api" && $targetService != "eship-label-ex" && $targetService != "eship-label-internal" && $targetService != "eship-statistics-center" ]];then
    echo "输入服务名称错误！！！"
    echo "可选服务名称：eship-customer-center、eship-export-data、eship-label-ex"
else

    echo "使用【$targetCode】组代码部署到服务器组【$service_group】"

    ## 代码目录
    codeDir=/17feia/nfsOthersFiles/parcels/$targetCode

    if [[ -z $targetService ]];then
        echo "未选择服务，重启所有服务"
        targetDirs=`ls ./$targetCode -F | grep "/$"`;
#        targetDirs=`ls  -F | grep "/$"`;
        for targetDir in $targetDirs
        do
            ## echo "当前目录：$targetDir"
            restartService;
        done

        echo "$targetCode" > currentCode

    else
        echo "选择的服务是：$targetService"
        targetDir=$targetService/
        restartService;
    fi
fi






