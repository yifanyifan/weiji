# 基础镜像
FROM centos-jdk
# 挂载点
VOLUME /tmp
# 复制文件
ADD weiji-eureka-0.0.1.jar app.jar
# 延迟插件启动
ADD entrypoint.sh ./
RUN chmod +x ./entrypoint.sh
# 执行后面命令
RUN sh -c 'touch /app.jar'
# 配置容器启动后执行的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
# 声明端口（不会打开端口）
EXPOSE 6001