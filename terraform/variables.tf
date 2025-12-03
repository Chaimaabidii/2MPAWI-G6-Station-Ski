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
    "subnet-0e5564c9201fb69d7", # us-east-1a
    "subnet-01c3cbdcb8d9047a1", # us-east-1b
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
  default     = "vpc-0a95472fbb4ef3c1f"  # VPC correct correspondant aux subnets
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}
