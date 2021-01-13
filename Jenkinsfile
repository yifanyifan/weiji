def project_url = "https://github.com/yifanyifan/weiji.git"
def gitlab_auth = "7d820c97-7914-4257-beb5-aec604242c69"

//构建版本的名称
def tag = "latest"
def image_tag = "0.0.1"
//Harbor私服地址
def harbor_url = "47.103.28.119"
//Harbor的项目名称
def harbor_project_name = "weiji"
def harbor_auth = "8072ac6a-7b14-4948-8758-9ec8bd4498c2"
//定义镜像名称
def imageName = "${project_name}-${image_tag}:${tag}"
node {
    def mvnHome
    stage('Pull') {
        // 1. 拉取代码
        checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']],
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: "${gitlab_auth}", url: "${project_url}"]]])
    }
    state('remove') {
        //cd "${dockerWKS}"
        //sh "docker-compose down"
    }
    stage('Build') {
        sh "mvn -f weiji-interface clean install"
        // 2. 编译打包，构建本地镜像
        sh "mvn -f ${project_name} clean package docker:build -DskipTests"
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
        //cd "${dockerWKS}"
        //sh "docker-compose -f docker-compose.yml build weiji-eureka"
        //sh "docker-compose -f docker-compose.yml up -d"
    }
}
