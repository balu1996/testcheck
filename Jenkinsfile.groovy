//Jenkinsfile for Build and check codequality changes //
pipeline { 
   agent any
 environment {
        registry = "190344882422.dkr.ecr.ap-south-1.amazonaws.com"
         ACCESS_KEY = credentials('AWS_ACCESS_KEY_ID')
        SECRET_KEY = credentials('AWS_SECRET_ACCESS_KEY')
         region ="ap-south-1"
         cluster_name ="demo-cluster1"
    }
    stages {  
		  
      stage('Create EKS Cluster : Terraform'){
            steps{
                script{

                    dir('eks_module') {
                      sh """
                          
                          terraform init 
                          terraform plan -var 'access_key=$ACCESS_KEY' -var 'secret_key=$SECRET_KEY' -var 'region=$region' --var-file=./config/terraform.tfvars
                          terraform apply -var 'access_key=$ACCESS_KEY' -var 'secret_key=$SECRET_KEY' -var 'region=$region' --var-file=./config/terraform.tfvars --auto-approve
                      """
                  }
                }
            }
        }
     stage('Connect to EKS '){
           steps{
              script{
             sh  'aws eks --region "$region" update-kubeconfig --name "$cluster_name" '
             
            }
        }
        } 
        stage('Deployment on EKS Cluster'){
           steps{
                script{
                  
                  def apply = false

                  try{
                    input message: 'please confirm to deploy on eks', ok: 'Ready to apply the config ?'
                    apply = true
                  }catch(err){
                    apply= false
                    currentBuild.result  = 'UNSTABLE'
                  }
                  if(apply){

                    sh """
                      kubectl apply -f deployment.yaml
                      kubectl apply -f service.yaml
                    """
                  }
                }
            }
        }    
    }
}


