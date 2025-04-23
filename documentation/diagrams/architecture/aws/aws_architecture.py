from diagrams import Diagram, Cluster
from diagrams.aws.analytics import ManagedStreamingForKafka
from diagrams.aws.compute import EKS
from diagrams.aws.database import Aurora
from diagrams.aws.database import ElasticacheForRedis
from diagrams.aws.network import APIGateway, Route53

with Diagram("AWS Architecture", show=False, direction="LR"):  # Left-to-right flow

    # DNS
    dns = Route53("DNS")

    # API Gateway
    api_gateway = APIGateway("API Gateway")

    # EKS Cluster
    with Cluster("EKS Cluster"):
        eks = EKS("EKS Cluster")

    # Aurora Database
    aurora_db = Aurora("Aurora Database")

    # Redis Cache
    redis_cache = ElasticacheForRedis("Redis Cache")

    # Kafka MSK
    kafka_msk = ManagedStreamingForKafka("Kafka MSK")

    # Connectivity
    dns >> api_gateway >> eks
    eks >> aurora_db
    eks >> redis_cache
    eks >> kafka_msk
