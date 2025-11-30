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
    "subnet-0679e41751e67f56a",
    "subnet-03f7b4862fc55b6d4",
    "subnet-0567d2939279428b3"
  ]
}

variable "role_arn" {
  type    = string
  default = "arn:aws:iam::917654454800:role/LabRole"
}

variable "vpc_id" {
  type    = string
  default = "vpc-0fbaf3350fd25cf981"
}

# ✅ SG EXISTANTS
variable "eks_cluster_sg_id" {
  type = string
}

variable "eks_worker_sg_id" {
  type = string
}
