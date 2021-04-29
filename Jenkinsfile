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
//def imageName = "${project_name}-${image_tag}:${tag}"
node {
    def mvnHome
    stage('Pull') {
        // 1. 拉取代码
        /* checkout([$class: 'GitSCM', branches: [[name: '*//* ${branch}']],
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: "${gitlab_auth}", url: "${project_url}"]]]) */

        checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']], extensions: [],
        userRemoteConfigs: [[credentialsId: '${gitlab_auth}', url: '${project_url}']]])
    }
    stage('remove') {
        echo '111111111111111'
        //cd "${dockerWKS}"
        //sh "docker-compose down"
    }
    stage('Build') {
        //1. 编译父工程【-N:取消递归，父子结构下需先编译父工程，否则子工程编译时失败（如：weiji-gateway报找不到父工程pom等）】
        sh "mvn clean install -N -DskipTests"
        //2. 编译打包，构建本地镜像
        sh "mvn -f weiji-interface clean install -DskipTests"
        sh "mvn -f weiji-utils clean install -DskipTests"
        sh "mvn -f ${project_name} clean package docker:build -DskipTests"
        //sh "mvn -f ${project_name} clean package -DskipTests"
        // 给镜像打标签 harbor uas
        //sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
    }
    /*stage('Push') {
        echo 'Push To Harbor...'
        withCredentials([usernamePassword(credentialsId: '8072ac6a-7b14-4948-8758-9ec8bd4498c2', passwordVariable: 'password', usernameVariable: 'username')]) {
            // 登录
            sh "docker login -u ${username} -p ${password} ${harbor_url}"
            //3. 上传镜像
            sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
            //docker pull 47.103.28.119/weiji/weiji-eureka-0.0.1:latest
            //docker run -p 6001:6001 -d 47.103.28.119/weiji/weiji-eureka-0.0.1:latest
        }
    }*/
    stage('UP') {
        echo '33333333333333333'
        //cd "${dockerWKS}"
        //sh "docker-compose -f docker-compose.yml build weiji-eureka"
        //sh "docker-compose -f docker-compose.yml up -d"
    }
}
