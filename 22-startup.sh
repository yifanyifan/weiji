#!/usr/bin/env bash
source ./20-functions.sh

echo '=================================================================='
echo '获取最新的jar来运行, 对日志 和 xxl设置当前机器的局域网IP'
echo '=================================================================='

targetIp=$(getIP)

# echo -e "\n\n==========================================================复制logback"
# logbackFile="logback-$targetIp.xml"
# cp conf/logback-prod.xml conf/$logbackFile
# sed -i 's/.log</-'$targetIp'.log</g' `grep "<file>" -rl --include="$logbackFile" */`
# echo "logback文件名称：$logbackFile"

echo -e "\n\n==========================================================杀死进程"
dir=$PWD
echo 'dir' + $dir
runingFile=${dir##*/}
echo 'runingFile' + $runingFile
preStopProcess=`ps -ef |grep "$runingFile.*.jar" |grep -v grep |cut -c 9-15`

if [ -z "$preStopProcess" ];then
    echo "$runingFile 没有运行"
else
    kill -9 $preStopProcess
    echo -e "\e[31m已杀死进程：$runingFile $preStopProcess \e[0m"
    sleep 5s
fi

echo -e "\n\n==========================================================启动最新的jar"
targetFile=`ls -lt | grep -E '\.jar' | head -n 1 | awk '{print $9}'`
echo -e "最新的jar：$targetFile"

source /etc/profile # 先source一下，不然会找不到环境变量java

nohup java -jar -Dspring.profiles.active=prod -Dlogging.config=classpath:$logbackFile  -Dxxl.job.executor.ip=$targetIp $targetFile  >/dev/null 2>&1 &

sleep 2s
newProcess=`ps -ef |grep $targetFile |grep -v grep |cut -c 9-15`
echo -e "\e[31m已启动进程：$newProcess \e[0m \n\n\n\n\n\n"




