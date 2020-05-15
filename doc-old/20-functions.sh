#!/bin/bash
echo '=================================================================='
echo '发布脚本的公共函数'
echo '2019-12-9 by LJ'
echo '=================================================================='



# '=================================================================='
# '为测试test-startup.sh设置远程调试端口'
# '2019-12-13 by lj'
# '=================================================================='
function setRemoteDebugInTestStartup()
{

    echo -e "\n\n===================================================== 为测试startup设置远程调试端口,LJ@2019-12-12\n"

    project=$1

    if [[ $project == 'eship-customer-center' ]];then

            sed -i 's/-Dspring.profiles.active=prod/-Dspring.profiles.active=test -Djava.rmi.server.hostname=192.168.8.167 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=6022 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7022,suspend=n/g' `grep "java -jar" -rl --include=test-startup.sh */`

    elif [[ $project == 'eship-label-ex' ]];then

            sed -i 's/-Dspring.profiles.active=prod/-Dspring.profiles.active=test -Djava.rmi.server.hostname=192.168.8.167 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=6028 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7028,suspend=n/g' `grep "java -jar" -rl --include=test-startup.sh */`

    elif [[ $project == 'eship-export-data' ]];then

            sed -i 's/-Dspring.profiles.active=prod/-Dspring.profiles.active=test -Djava.rmi.server.hostname=192.168.8.167 -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=6032 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7032,suspend=n/g' `grep "java -jar" -rl --include=test-startup.sh */`
    fi

}



# '=================================================================='
# '获取本机局域网IP'
# '2019-12-9 by Kee'
# '=================================================================='
function getIP()
{

    local_ip=`ip addr |grep inet |grep 255 |grep -v 127.0.0.1|grep -v inet6 | awk '{print $2}' | awk -F "/" '{print $1}'`

    hostIP=""
    for element in $local_ip
    do
        if [[ $element =~ "172.17" ]]
        then
          dockerIP=$element
        else
          hostIP=$element
          break
        fi
    done

    echo $hostIP
}