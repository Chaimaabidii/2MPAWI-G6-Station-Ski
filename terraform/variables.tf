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
  description = "IDs des sous-réseaux pour EKS"
  type        = list(string)
  default = [
    "subnet-0e633d0f09b8cf0f3", # us-east-1a
    "subnet-0b11651cda4f8fe8a", # us-east-1b
  ]
}

variable "role_arn" {
  description = "ARN du rôle IAM pour EKS"
  type        = string
  default     = "arn:aws:iam::387364506026:role/LabRole"  
}

variable "vpc_id" {
  description = "L'ID du VPC pour le cluster EKS"
  type        = string
  default     = "vpc-0d4062ef9a40db564"  # VPC correct correspondant aux subnets
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}
