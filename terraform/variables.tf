variable "aws_region" {
  description = "La région AWS"
  type        = string
  default     = "us-east-1"
}

variable "cluster_name" {
  description = "Nom du cluster EKS"
  type        = string
  default     = "mykubernetes"
}

variable "subnet_ids" {
  description = "IDs des sous-réseaux EKS"
  type        = list(string)
  default     = [
    "subnet-022338cd7540b4755",
    "subnet-03cbd72fbbfc663f1",
    "subnet-0bb0d896bd2043f48"
  ]
}

variable "role_arn" {
  description = "ARN du rôle IAM pour EKS"
  type        = string
  default     = "arn:aws:iam::093685644326:role/LabRole"
}

variable "vpc_id" {
  description = "ID du VPC"
  type        = string
  default     = "vpc-08161a2ce2c9925c1"
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}

variable "eks_cluster_sg_id" {
  description = "Security Group existant du cluster EKS"
  type        = string
}

variable "eks_worker_sg_id" {
  description = "Security Group existant des workers EKS"
  type        = string
}
