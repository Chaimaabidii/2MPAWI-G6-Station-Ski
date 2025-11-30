provider "aws" {
  region = var.aws_region
}

# ================================
# AUCUNE CREATION DE VPC NI SECURITY GROUP
# On utilise uniquement l'existant
# ================================

# -------------------------
# CLUSTER EKS
# -------------------------
resource "aws_eks_cluster" "my_cluster" {
  name     = var.cluster_name
  role_arn = var.role_arn
  version  = "1.30"

  vpc_config {
    subnet_ids         = var.subnet_ids
    security_group_ids = [var.eks_cluster_sg_id]   # SG EXISTANT
  }
}

# -------------------------
# NODE GROUP
# -------------------------
resource "aws_eks_node_group" "my_node_group" {
  cluster_name    = aws_eks_cluster.my_cluster.name
  node_group_name = "noeud1"
  node_role_arn   = var.role_arn
  subnet_ids      = var.subnet_ids

  scaling_config {
    desired_size = 2
    max_size     = 3
    min_size     = 1
  }

  remote_access {
    source_security_group_ids = [var.eks_worker_sg_id]   # SG EXISTANT
  }

  depends_on = [aws_eks_cluster.my_cluster]
}
