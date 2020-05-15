#! /bin/bash

echo "杀掉 java..........................................................."
keyword=$1
if [ -z "$keyword" ]; then
	echo "杀掉weiji所有java进程"
	ps -ef |grep weiji |grep -v grep |cut -c 9-15 |xargs kill -s 9
else
	echo "杀掉[$keyword]相关java进程"
	if [[ "weiji-eureka" ]]; then
		ps -ef |grep "weiji-eureka" |grep -v grep |cut -c 9-15 |xargs kill -s 9
	elif [[ "weiji-config" ]]; then
		ps -ef |grep "weiji-config" |grep -v grep |cut -c 9-15 |xargs kill -s 9
	elif [[ "weiji-service" ]]; then
		ps -ef |grep "weiji-service" |grep -v grep |cut -c 9-15 |xargs kill -s 9
	elif [[ "weiji-customer" ]]; then
		ps -ef |grep "weiji-customer" |grep -v grep |cut -c 9-15 |xargs kill -s 9
	else
		echo "输入的参数有问题，请检查..........................................................."
	fi
fi

echo -e '\n\n'

echo "更新 java code..........................................................."
cd /weiji/code/weiji
git reset --hard	#返回到上一个提交的版本（包括commit）
git pull
# sh 00_modify_host.sh 192.168.8.169
# sh 00_modify_version.sh guoshiwei

echo -e '\n\n'

echo "开始打包并运行jars..........................................................."
cd /weiji/code/weiji
# sh 05_run-jars.sh $keyword