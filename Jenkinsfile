pipeline {

    agent any

    triggers {
        cron('H */8 * * *') //regular builds
        pollSCM('* * * * *') //polling for changes, here once a minute
    }

    stages {
        stage('Checkout') {
            steps { //Checking out the repo
                checkout scm
            }
        }
        stage('Clean Project') {
            steps {
                script {
                    sh './gradlew clean --no-daemon' //run a gradle task
                }
            }
        }
        stage('UI Test Run') {
            steps {
                    script {
                        sh './gradlew test -Dbrowser=ch --no-daemon'
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
    post {
        always { //Send an email to the person that broke the build
            step([$class                  : 'Mailer',
                  notifyEveryUnstableBuild: true,
                  recipients              : [emailextrecipients([[$class: 'CulpritsRecipientProvider'], [$class: 'RequesterRecipientProvider']])].join(' ')])
        }
    }
}
