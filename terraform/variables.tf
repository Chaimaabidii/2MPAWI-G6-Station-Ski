variable "aws_region" {
  description = "Région AWS"
  type        = string
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "Nom du cluster EKS"
  type        = string
  default     = "mykubernetes"
}

variable "subnet_ids" {
  description = "Subnets EKS (DOIVENT être dans le même VPC)"
  type        = list(string)
  default     = [
    "subnet-0679e41751e67f56a",
    "subnet-03f7b4862fc55b6d4",
    "subnet-0567d2939279428b3"
  ]
}

variable "role_arn" {
  description = "Rôle IAM EKS"
  type        = string
  default     = "arn:aws:iam::917654454800:role/LabRole"
}

variable "vpc_id" {
  description = "VPC du cluster EKS"
  type        = string
  default     = "vpc-0fbaf3350fd25cf98"   # ✅ CORRIGÉ
}

# ✅ SECURITY GROUPS EXISTANTS (même VPC que les subnets)
variable "eks_cluster_sg_id" {
  description = "Security Group du cluster EKS"
  type        = string
}

variable "eks_worker_sg_id" {
  description = "Security Group des workers EKS"
  type        = string
}
