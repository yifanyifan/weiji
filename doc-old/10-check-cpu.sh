[root@www-17feia-com www]# vim 05-check-cpu-memory.sh

#!/usr/bin/env bash

## 查看服务器的cpu和内存使用率

arrayA=("172.19.108.101" "172.19.108.97" "172.19.108.94")
arrayB=("172.19.108.93" "172.19.108.96" "172.19.108.95")


echo ""
echo "【sendatek-172.19.108.101】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.101 "vmstat -S M"

echo ""
echo "【java2-172.19.108.97】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.97 "vmstat -S M"

echo ""
echo "【db-server-172.19.108.94】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.94 "vmstat -S M"

echo ""
echo "【web-server-172.19.108.93】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.93 "vmstat -S M"

echo ""
echo "【java1-172.19.108.96】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.96 "vmstat -S M"

echo ""
echo "【mq-server-172.19.108.95】"
sshpass -f /home/kee/webserver.txt ssh -o StrictHostKeyChecking=no root@172.19.108.95 "vmstat -S M"

