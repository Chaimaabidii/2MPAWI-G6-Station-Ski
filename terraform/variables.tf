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
    "subnet-02cdac93ab4752d48", # us-east-1a
    "subnet-0875d677e0fd4275b", # us-east-1b
    "subnet-0c2f8648097b5a631"  # us-east-1c
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
  default     = "vpc-02bc069273df78b1d"  # VPC correct correspondant aux subnets
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}
