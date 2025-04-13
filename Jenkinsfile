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
                junit '**/target/surefire-reports/TEST-*.xml'
            }
        }
    }
    post {
        always {
            mail body: "${env.BUILD_URL}", 
                from: 'garry@matribio.com', 
                subject: "${currentBuild.currentResult}: Job ${env.JOB_NAME} [${env.BUILD_NUMBER}]", 
                to: 'gkgarry911@gmail.com'
        }
    }
}