pipeline {
    agent none
    parameters {
        string(name: 'IMAGE', defaultValue: 'knowing-server', description: 'image name')
        string(name: 'VERSION', defaultValue: '0.0.1', description: 'image version')
    }

    stages {
        stage('Package') {
            agent {
                docker {
                    image 'maven:3-alpine'
                    args '-v /var/maven/.m2:/root/.m2'
                }
            }
            steps {
                sh 'mvn clean package -Pprod'
            }
        }

        stage('Build and Publish') {
            agent {
                node {
                    label 'master'
                }
            }
            steps {
                sh "docker rm -f ${params.IMAGE} || echo 'continue exec'"
                sh "docker rmi -f ${params.IMAGE}:${params.VERSION} || echo 'continue exec'"
                sh "pwd"
                sh "docker build -t ${params.IMAGE}:${params.VERSION} --rm=true ."
                sh "docker run -it -v /var/knowing/log:/var/knowing/log --name ${params.IMAGE} -d -p 9000:9000 --restart=always ${params.IMAGE}:${params.VERSION}"
            }
        }
    }
}
