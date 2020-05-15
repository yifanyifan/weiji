#!/bin/bash
#------------------------------------------
# @Author: LJ, @Date: 2019/8/29 10:04
# @Description: 根据top查看进程并 jstack cpu消耗的进程
# 要在crontab中运行, 则命令要加完全路径
#------------------------------------------

#定义日志名称和路径
local_ip=`/sbin/ip addr |grep inet|grep -v 127.0.0.1|grep -v inet6 | head -1 | awk '{print $2}' | awk -F "/" '{print $1}'`
currentTime=$(date "+%Y-%m-%d-%H-%M-%S")
jstackLogFile=/17feia/nfsOthersFiles/01-shell/03-jstack-top-logs/${currentTime}_${local_ip}.log
runningLogFile=/17feia/nfsOthersFiles/01-shell/03-jstack-top-logs/running.log
echo "日志文件 $jstackLogFile"
echo "日志文件 $jstackLogFile" >> $runningLogFile

# ps 与 top 有差距，这里选择top,查看瞬时数据
# topProcess=`ps -aux | sort -k3,3nr |grep java |head -1`
topProcess=`top -b -c -n1 |grep java | sort -k9,9nr |head -1`
echo "$currentTime : $topProcess" >>  $runningLogFile
echo $topProcess

#获取进程号
topPid=`echo $topProcess |awk '{print $1}'`
#获取进程CPU数
cpuPercent=`echo $topProcess |awk '{print $9}'`

echo $cpuPercent

#只记录cpu 超过30的时候

# if [ $cpuPercent -lt 30] => 会报错，提示integer expression expected,这样只能判断整数，而不是浮点数
# 在把cpuPercent 转成整数： if [ $((cpuPercent)) -lt 30] => 会报错, cpuPercent有小数点，无法转换
# 这里借助了bc这个命令（bc是一个计算器，Bash内置了对整数四则运算的支持，但是并不支持浮点运算，而bc命令可以很方便的进行浮点运算，当然整数运算
if [ $(echo "$cpuPercent < 50"|bc) = 1 ]; then
    exit 0
fi


echo -e "\n================================================获取最大CPU消耗进程================================================" >> $jstackLogFile
#top显示信息不完整,这里又用回ps
echo "top 命令信息:"
echo $topProcess >> $jstackLogFile
echo "ps 命令信息:"
echo `ps -aux |grep $topPid |grep -v grep` >> $jstackLogFile



echo -e "\n================================================获取进程${topPid}的线程栈================================================" >> $jstackLogFile
# 要在crontab中运行, 则命令要加完全路径; 让jdk放置nfs目录
/17feia/nfsOthersFiles/02-tools/jdk1.8.0_151/bin/jstack $topPid >> $jstackLogFile



echo -e "\n================================================获取进程${topPid}的线程================================================" >> $jstackLogFile

top -b -n2 -H -p $topPid >> $jstackLogFile

