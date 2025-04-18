echo "MatriBio Project : SCM"

pipeline {
    agent any
    stages {
        stage("compile") {
            steps {
                sh "./mvnw compile"
            }
        }
        stage("build") {
            steps {
                sh "./mvnw package"
            }
        }
        stage("captured") {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar'
            }
        }
        stage('Docker Build') {
            agent {
                docker {
                    image 'openjdk'
                    reuseNode true
                }
            }
            steps {
                sh 'docker build -t garry/matribio-project:1.0 .'
            }
        }
    }
    post {
        always {
            mail body: "${env.BUILD_URL}", 
                from: 'garry@matribio.com', 
                subject: "${currentBuild.currentResult}: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]", 
                to: 'gkgarry911@gmail.com'
            junit '**/target/surefire-reports/TEST-*.xml'
        }
    }
}