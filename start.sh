#!/bin/bash

echo 'weiji-eureka'
cd weiji-eureka
mvn clean package docker:build -Dmaven.test.skip
echo 'weiji-config'
cd ../weiji-config
mvn clean package docker:build -Dmaven.test.skip
echo 'weiji-service'
cd ../weiji-service
mvn clean package docker:build -Dmaven.test.skip
echo 'weiji-customer'
cd ../weiji-customer
mvn clean package docker:build -Dmaven.test.skip
