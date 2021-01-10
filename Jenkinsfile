def project_url = "https://github.com/yifanyifan/weiji.git"
def gitlab_auth = "7d820c97-7914-4257-beb5-aec604242c69"

//构建版本的名称
def tag = "latest"
//Harbor私服地址
def harbor_url = "47.103.28.119"
//Harbor的项目名称
def harbor_project_name = "weiji"
def harbor_auth = "8072ac6a-7b14-4948-8758-9ec8bd4498c2"
//定义镜像名称
def imageName = "${project_name}:${tag}"
node {
    def mvnHome
    stage('Pull') {
        echo 'pull By XM'
        checkout([$class: 'GitSCM', branches: [[name: '*/${branch}']],
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: "${gitlab_auth}", url: "${project_url}"]]])
    }
    stage('Build') {
        echo 'build By XM'
        sh "mvn -f weiji-interface clean install"
        // sh "mvn -f ${project_name} clean package"
        // 编译，构建本地镜像
        sh "mvn -f ${project_name} clean package docker:build -DskipTests"
        // 给镜像打标签
        sh "docker tag ${imageName} ${harbor_url}/${harbor_project_name}/${imageName}"
    }
    stage('Push') {
        withCredentials([usernamePassword(credentialsId: '8072ac6a-7b14-4948-8758-9ec8bd4498c2', passwordVariable: 'password', usernameVariable: 'username')]) {
            // 登录
            sh "docker login -u ${username} -p ${password} ${harbor_url}"
            //上传镜像
            sh "docker push ${harbor_url}/${harbor_project_name}/${imageName}"
        }
    }
    stage('Results') {
        echo 'deploy By XM'
    }
}
