from diagrams import Cluster, Diagram
from diagrams.k8s.compute import Pod, Deployment, ReplicaSet
from diagrams.k8s.network import Service, Ingress
from diagrams.onprem.database import PostgreSQL
from diagrams.onprem.queue import Kafka

with Diagram("Kubernetes Cluster Architecture", show=False, direction="LR"):
    with Cluster("Kubernetes Cluster"):
        # Order Service
        with Cluster("Order Service"):
            order_deployment = Deployment("order-dp")
            order_replicaset = ReplicaSet("order-rs")
            order_pod = Pod("order-pod")
            order_service = Service("order-svc")

            # Connections
            order_deployment >> order_replicaset >> order_pod
            order_pod >> order_service

        # Postgres Database
        with Cluster("Postgres Database"):
            postgres_deployment = Deployment("postgres-dp")
            postgres_replicaset = ReplicaSet("postgres-rs")
            postgres_pod = Pod("postgres-pod")
            postgres_service = Service("postgres-svc")

            # Connections
            postgres_deployment >> postgres_replicaset >> postgres_pod
            postgres_pod >> postgres_service

        # Kafka Broker
        with Cluster("Kafka Broker"):
            kafka_deployment = Deployment("kafka-dp")
            kafka_replicaset = ReplicaSet("kafka-rs")
            kafka_pod = Pod("kafka-pod")
            kafka_service = Service("kafka-svc")

            # Connections
            kafka_deployment >> kafka_replicaset >> kafka_pod
            kafka_pod >> kafka_service

    # Ingress
    ingress = Ingress("k8s-ingress")

    # Ingress connects only to Order Service
    ingress >> order_service

    # Order Service connects to Postgres and Kafka services
    order_service >> postgres_service
    order_service >> kafka_service
