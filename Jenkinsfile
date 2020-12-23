def project_url = "https://github.com/yifanyifan/weiji.git"
def gitlab_auth = "7d820c97-7914-4257-beb5-aec604242c69"
node {
    def mvnHome
    stage('拉取代码') {
        echo 'pull By XM'
        checkout([$class: 'GitSCM', branches: [[name: '*/master']],
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: "${gitlab_auth}", url: "${project_url}"]]])
    }
    stage('Build') {
        echo 'build By XM'
    }
    stage('Results') {
        echo 'deploy By XM'
    }
}
