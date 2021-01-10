#!/bin/bash
 
: ${SLEEP_SECOND:=2} #默认两秒后重试
wait_for() {
    echo Waiting for $1 to listen on $2...
    #while ! ncat -z $1 $2;
    while ! nc -z $1 $2; #nc命令用telnet协议测试端口
    do echo waiting...;
        sleep $SLEEP_SECOND;
    done }
     
declare DEPENDS
declare CMD
while getopts "d:c:" arg
do
    case $arg in
        d)
            DEPENDS=$OPTARG
            ;;
        c)
            CMD=$OPTARG
            ;;
        ?)
        echo "unkonw argument" exit 1
            ;;
    esac
done
 
for var in ${DEPENDS//,/ } ### ${DEPENDS//,/ }把DEPENDS中的,替换为空格
do
    host=${var%:*}
    port=${var#*:}
    wait_for $host $port
done
eval $CMD ##eval命令相当于把$CMD中的命令执行一次
#避免执行完命令之后退出容器
tail -f /dev/null