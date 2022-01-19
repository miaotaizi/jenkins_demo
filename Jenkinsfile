pipeline {
  agent {
    docker {
        image 'maven:3.6-alpine'
        args '-v /var/jenkins_home/mvnrepo:/root/.m2'
    }
  }
  stages {
    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }
  }

  post {
    always {
        junit "target/surefire-reports/**/*.xml"
    }
  }
}