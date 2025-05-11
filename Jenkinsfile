node {
    def WORKSPACE = "/var/lib/jenkins/workspace/springboot-deploy"
    def dockerImageTag = "springboot-deploy-${env.BUILD_NUMBER}"

    try {
        stage('Clone Repo') {
            git url: 'https://gitlab.com/asyst-dev/qgx/kemenpar-revamp/frontend/kemenpar.git',
                credentialsId: 'adminasyst2021',
                branch: 'development'
        }

        stage('Build with Maven') {
            echo 'Building project...'
            sh 'mvn clean install'
        }

        stage('Run Tests') {
            echo 'Running tests...'
            sh 'mvn test'
        }

        stage('Build Docker Image') {
            echo "Building Docker image: ${dockerImageTag}"
            sh "docker build -t ${dockerImageTag} ."
        }

        stage('Push Docker Image') {
            echo "Pushing Docker image..."
            withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                sh 'echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin'
                sh "docker tag ${dockerImageTag} your-dockerhub-user/${dockerImageTag}"
                sh "docker push your-dockerhub-user/${dockerImageTag}"
            }
        }

    } catch (err) {
        echo "Build failed: ${err}"
        currentBuild.result = 'FAILURE'
        throw err
    } finally {
        stage('Cleanup') {
            cleanWs()
        }
    }
}
