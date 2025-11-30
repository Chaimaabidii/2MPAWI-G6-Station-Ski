variable "aws_region" {
  type    = string
  default = "us-east-1"
}

variable "cluster_name" {
  type    = string
  default = "mykubernetes"
}

variable "subnet_ids" {
  type = list(string)
  default = [
    "subnet-022338cd7540b4755",
    "subnet-03cbd72fbbfc663f1",
    "subnet-0bb0d896bd2043f48"
  ]
}

variable "role_arn" {
  type    = string
  default = "arn:aws:iam::093685644326:role/LabRole"
}

variable "vpc_id" {
  type    = string
  default = "vpc-08161a2ce2c9925c1"
}

# ✅ SG EXISTANTS
variable "eks_cluster_sg_id" {
  type = string
}

variable "eks_worker_sg_id" {
  type = string
}
