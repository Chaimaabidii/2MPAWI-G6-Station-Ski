# ================================
# PROVIDER AWS
# ================================
provider "aws" {
  region = var.aws_region
  # ⚠️ Optionnel si tu veux forcer des credentials spécifiques (utile pour Jenkins)
  # access_key = var.aws_access_key
  # secret_key = var.aws_secret_key
}

# ================================
# CLUSTER EKS
# ================================
resource "aws_eks_cluster" "my_cluster" {
  name     = var.cluster_name
  role_arn = var.role_arn
  version  = "1.30"

  vpc_config {
    subnet_ids         = var.subnet_ids
    security_group_ids = [var.eks_cluster_sg_id]   # SG EXISTANT
  }

  # ⚠️ Important : attendre la disponibilité du cluster avant de créer des ressources dépendantes
  depends_on = []  # peut être vide si pas de dépendances
}

# ================================
# NODE GROUP
# ================================
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

  depends_on = [
    aws_eks_cluster.my_cluster
  ]
}
