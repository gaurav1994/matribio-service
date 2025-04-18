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
        stage("run") {
            steps {
                sh "java -jar /target/matribio-service.jar"
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