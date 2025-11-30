provider "aws" {
  region = var.aws_region
}

# ================================
# UTILISATION DES RESSOURCES EXISTANTES
# ================================

# -------------------------
# CLUSTER EKS
# -------------------------
resource "aws_eks_cluster" "my_cluster" {
  name     = var.cluster_name
  role_arn = var.cluster_role_arn
  version  = "1.30"

  vpc_config {
    subnet_ids         = var.subnet_ids
    security_group_ids = [var.eks_cluster_sg_id]  # SG du cluster (existant)
  }
}

# -------------------------
# NODE GROUP
# -------------------------
resource "aws_eks_node_group" "my_node_group" {
  cluster_name    = aws_eks_cluster.my_cluster.name
  node_group_name = "noeud1"
  node_role_arn   = var.node_role_arn          # ⚠ Rôle IAM différent du cluster !
  subnet_ids      = var.subnet_ids

  scaling_config {
    desired_size = 2
    max_size     = 3
    min_size     = 1
  }

  # Accès SSH aux workers (optionnel)
  remote_access {
    ec2_ssh_key               = var.ssh_key_name           # Nom de ta clé .pem
    source_security_group_ids = [var.eks_worker_sg_id]     # SG des workers
  }

  depends_on = [
    aws_eks_cluster.my_cluster
  ]
}
