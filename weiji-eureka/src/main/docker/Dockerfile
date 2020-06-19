# 由于现在下载jdk的话需要使用认证，所以我们在生产环境可以使用这个，上线的时候我们可以自己来制作一个镜像就好了，-alpine  这个是一个极小的linux系统（这样可以减小docker镜像的体积），后面不建议使用这个，因为这个dns好像实现的不一样，在服务发现中可能会有问题，所以最好还是使用centos的镜像为基础来构建
FROM hub.c.163.com/library/java:8-alpine
VOLUME /tmp
# 是把本地的jar包拷贝到容器里面去
ADD weiji-eureka-0.0.1.jar app.jar
# 容器中执行构建命令
RUN sh -c 'touch /app.jar'
# 执行java运行jar包的命令
ENTRYPOINT  ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 6001