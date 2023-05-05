
aws_eks_cluster_config = {

      "demo-cluster" = {

        eks_cluster_name         = "demo-cluster1"
        eks_subnet_ids = ["subnet-07c42b6474a607cca","subnet-0b9fb1620e996cced","subnet-0c36f408b579f5623"]
        tags = {
             "Name" =  "demo-cluster"
         }  
      }
}

eks_node_group_config = {

  "node1" = {

        eks_cluster_name         = "demo-cluster"
        node_group_name          = "mynode"
        nodes_iam_role           = "eks-node-group-general1"
        node_subnet_ids          = ["subnet-07c42b6474a607cca","subnet-0b9fb1620e996cced","subnet-0c36f408b579f5623"]

        tags = {
             "Name" =  "node1"
         } 
  }
}
