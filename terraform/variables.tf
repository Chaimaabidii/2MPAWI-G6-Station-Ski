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
    "subnet-05b52a01889df5b88",  # us-east-1a
    "subnet-078097c663bd549b0",  # us-east-1b
    "subnet-06398b1e0d6cfb7c1"   # us-east-1c
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
  default     = "vpc-071a06bd6415b1a4b"
}

variable "vpc_cidr" {
  description = "CIDR block for the VPC"
  type        = string
  default     = "10.0.0.0/16"
}
