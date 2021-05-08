def gitlab_auth = "e167b9b4-f48b-43b4-acf8-384d06970c09"
def project_url = "git@github.com:yifanyifan/weiji.git"

//构建版本的名称
def tag = "latest"
def image_tag = "0.0.1"
//Harbor私服地址
def harbor_url = "47.103.28.119"
//Harbor的项目名称
def harbor_project_name = "weiji"
def harbor_auth = "e167b9b4-f48b-43b4-acf8-384d06970c09"
//定义镜像名称
def imageName = "${project_name}-${image_tag}:${tag}"
node {
    def mvnHome
    stage('拉取代码') {
        //拉取代码
        checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], extensions: [],
        userRemoteConfigs: [[credentialsId: "${gitlab_auth}", url: "${project_url}"]]])
    }
    stage('删除镜像') {
        AAA = sh(script: "docker ps -f 'name=${project_name}' | wc -l", returnStdout: true)
        AAA = AAA.trim()
        echo AAA
        if (AAA == '2') {
            echo "=======================> docker stop ${project_name}"
            sh "docker stop ${project_name}"
        }
        BBB = sh(script: "docker ps -a -f 'name=${project_name}' | wc -l", returnStdout: true)
        BBB = BBB.trim()
        if (BBB == "2") {
            echo "=======================> docker rm ${project_name}"
            sh "docker rm ${project_name}"
        }
        CCC = sh(script: "docker images ${imageName} | wc -l", returnStdout: true)
        CCC = CCC.trim()
        if (CCC == "2") {
            echo "=======================> docker rmi ${imageName}"
            sh "docker rmi ${imageName}"
        }
    }
    stage('编译打包') {
        //1. 编译父工程【-N:取消递归，父子结构下需先编译父工程，否则子工程编译时失败（如：weiji-gateway报找不到父工程pom等）】
        sh "mvn clean install -N -DskipTests"
        //2. 编译打包，构建本地镜像
        sh "mvn -f weiji-interface clean install -DskipTests"
        sh "mvn -f weiji-utils clean install -DskipTests"
        //判断当前目录是否有该工程，工程也可能在weiji-module里面（如weiji-auth）。
        ZZZ = sh(script: "find . -maxdepth 1 -name ${project_name} | wc -l", returnStdout: true)
        ZZZ = ZZZ.trim();
        if(ZZZ == '0'){
            sh "mvn -f weiji-module/${project_name} clean package -P prod docker:build -DskipTests"
        }
        if(ZZZ == '1'){
            sh "mvn -f ${project_name} clean package -P prod docker:build -DskipTests"
        }
    }
    stage('启动镜像') {
        //docker run -d  --net app_net --name weiji-gateway-0.0.1 -p 9999:9999 weiji-gateway-0.0.1:latest
        sh "ls"
        echo "sssssssssssssssssssssssssss"
        ZZZ = sh(script: "ls", returnStdout: true)
        ZZZ = ZZZ.trim();
        echo ZZZ
        echo "ddddddddddddddddddddddddddddddd"
        sh "docker-compose -f docker-compose.yml up -d ${project_name}"
    }
}
