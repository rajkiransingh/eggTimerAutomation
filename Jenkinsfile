pipeline {
    agent any

    options {
        skipDefaultCheckout true
    }

    stages {
        stage('checkout') {
            steps {
                checkout scm
            }
        }

        stage('test') {
            steps {
                sh "gradlew test -Dbrowser=ch"
            }
        }
}
}