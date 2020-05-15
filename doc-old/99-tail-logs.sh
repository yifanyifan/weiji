#!/bin/bash
#------------------------------------------
# @Author: LJ, @Date: 2019/8/27 17:30
# @Description: 有选择读取服务器全部日志
#------------------------------------------

local_ip=`ip addr |grep inet|grep -v 127.0.0.1|grep -v inet6 | awk '{print $2}' | awk -F "/" '{print $1}'`
echo -e "本机内网IP: $local_ip \n"

echo -e "本机运行的java进程: \n"
jps -l

echo  -e "-----------------------------------------------------------\n"

read -p "请输入工程的模糊名称, 也可以直接回车:" projectName

if [ -z $projectName ];then
	projectName="log"
	echo -e '\n没有输入工程名, 列出全部工程日志:\n'
else
    echo -e "\n输入的工程名: $projectName\n"
fi

#这里的小括号, 可以把结果转换成数组
str=(`ls -l /tmp/logs/eship/ |grep $projectName |grep -n 172 | awk '{print $9}'`)

for i in ${!str[@]}
do
  echo  "${i} : ${str[i]}"
done

read -p "请输入日志的序号进行tail查看:" logIndex
tail -f -n 300 /tmp/logs/eship/${str[logIndex]}