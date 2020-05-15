#!/usr/bin/env bash
source ./20-functions.sh


echo '=================================================================='
echo '全部重新启动测试环境 by lj'
echo '=================================================================='


tar -xvf eship-customer-center.tar.gz
tar -xvf eship-label-ex.tar.gz
tar -xvf eship-export-data.tar.gz



vhost=$(getIP)
echo "Modifing rabbit vhost 与 xxl 为: ${vhost}"
sed -i 's#vhost: /.*$#vhost: /'${vhost}'#'   `grep "vhost: /" -rl --include="application-test.yaml" */`
sed -i 's#ip:.*xxl-client-ip$#ip: '${vhost}' \#xxl-client-ip#'   `grep "xxl-client-ip" -rl --include="application-test.yaml" */`


cd eship-customer-center/
sh test-startup.sh

cd ../eship-label-ex/
sh test-startup.sh

cd ../eship-export-data/
sh test-startup.sh


