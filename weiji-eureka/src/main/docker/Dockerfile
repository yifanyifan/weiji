# 基础镜像
FROM java:8
# 挂载点
VOLUME /tmp
# 复制文件
ADD weiji-eureka-0.0.1.jar app.jar
ADD ./wait-for-it.sh ./
RUN chmod +x ./wait-for-it.sh
RUN sh -c 'touch /app.jar'
# 配置容器启动后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

# 声明端口（不会打开端口）
EXPOSE 6001