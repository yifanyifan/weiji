#!/bin/bash

# 打包
mvn clean package install -Dmaven.test.skip=true

#echo "修改挂载的nfs....................................................."
#sed -i 's#^.*upload: X:.*$#    upload: /eship/nfsFiles/#' `grep -l "X:" -r --include="application-dev.*" ./`
#
#sed -i 's#^.*static.file.server.upload=X:.*$#static.file.server.upload=/eship/nfsFiles#' `grep -l "X:" -r --include="application.*" ./`
#
#sed -i 's#^.*static.file.server: X:/.*$#static.file.server: /eship/nfsFiles/#' `grep -l "X:" -r --include="application.*" ./`
#
#echo "修改rabbit........................................................"
#sed -i 's#^.*rabbitmq.addresses.*$#rabbitmq.addresses=192.168.8.169:6672#' `grep -l "rabbitmq.addresses" -r --include="application.*" ./`


# 运行

echo 启动web层开始...

## 根据关键字启动工程
keyword=$1
if [ -z $keyword ];then
  echo "启动所有工程..."
else
  echo "启动[$keyword]工程..."
fi


if [ -z $keyword ] || [[ "eship-author-center" == *$keyword* ]];then
  echo 启动authorWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7021,suspend=n  -jar -Dspring.profiles.active=test author-center/target/eship-author-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-customer-center" == *$keyword* ]];then
  echo 启动customerWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7022,suspend=n -jar -Dspring.profiles.active=test eship-customer-center/target/eship-customer-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-operator-center" == *$keyword* ]];then
  echo 启动operatorWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7027,suspend=n -jar -Dspring.profiles.active=test eship-operator-center/target/eship-operator-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-finance-center" == *$keyword* ]];then
  echo 启动financeWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7025,suspend=n -jar -Dspring.profiles.active=test eship-finance-center/target/eship-finance-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-product-center" == *$keyword* ]];then
  echo 启动productWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7024,suspend=n -jar -Dspring.profiles.active=test eship-product-center/target/eship-product-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-sales-center" == *$keyword* ]];then
  echo 启动salesWebApp...
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7026,suspend=n -jar -Dspring.profiles.active=test eship-sales-center/target/eship-sales-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s

fi

if [ -z $keyword ] || [[ "eship-label-internal" == *$keyword* ]];then
  echo 启动labelInternalApp
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7023,suspend=n -jar -Dspring.profiles.active=test eship-label-internal/target/eship-label-internal-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-label-ex" == *$keyword* ]];then
  echo 启动labelExApp
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7028,suspend=n -jar -Dspring.profiles.active=test eship-label-ex/target/eship-label-ex-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-api" == *$keyword* ]];then
  echo 启动ApiApp
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7029,suspend=n  -jar -Dspring.profiles.active=test eship-api/target/eship-api-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-statistics-center" == *$keyword* ]];then
  echo 启动eship-statistics-center
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7020,suspend=n  -jar -Dspring.profiles.active=test eship-statistics-center/target/eship-statistics-center-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &
  sleep 10s
fi


if [ -z $keyword ] || [[ "eship-export-data" == *$keyword* ]];then
  echo 启动eship-export-data
  nohup java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=7032,suspend=n  -jar -Dspring.profiles.active=test eship-export-data/target/eship-export-data-1.0-SNAPSHOT*.jar >/dev/null 2>&1 &

fi

echo 启动完成...