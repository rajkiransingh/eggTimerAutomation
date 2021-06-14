pipeline {

    agent any

    stages {
        stage('Checkout') {
            steps { //Checking out the repo
                checkout scm
            }
        }
        stage('Clean Project') {
            steps {
                script {
                    sh './gradlew clean --no-daemon'
                }
            }
        }
        stage('Check Build') {
            steps {
                script {
                    sh './gradlew check --no-daemon'
                }
            }
        }
    }
}
