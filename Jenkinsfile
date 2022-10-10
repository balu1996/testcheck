pipeline {
    agent any
      stages {  

          stage('Checkout SCM') {
            steps {
			script{ 
			    sh 'pwd'
                dir('gitclone'){
			      git branch: '*/master', credentialsId: 'balu', url: 'https://github.com/balu1996/testcheck.git'  
                }
			    }
				}
				}

          stage('Build') {
            steps {
			      script {
              echo "Hello"  
                }
				}
				}
	}
	}
