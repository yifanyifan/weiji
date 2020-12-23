def project_url = "https://github.com/yifanyifan/weiji.git"
def gitlab_auth = "7d820c97-7914-4257-beb5-aec604242c69"
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
        # sh "mvn -f weiji-interface clean install"
        # sh "mvn -f ${project_name} clean install"

    }
    stage('Results') {
        echo 'deploy By XM'
    }
}
