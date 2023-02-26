# **Distributed Programming First Exam**

Microservice based post management system implemented using Spring Boot and the Spring framework.

## **Components**

* **User Management Services**: Restful APIs for CRUD user management.

* **Post Management Services**: Restful APIs for CRUD post management. These services contain TCP endpoints, allowing clients to subscribe for receiving push notifications when a new resource is created.

* **Integration Service**: Distributed system backend entry point. Receives requests from clients and forwards them to the proper handler service.
  
* **Load Balancer Service**: Load balancing and failovering service based on Spring Cloud's Eureka Service.
  
* **Shared Database Service**: Simple Spring Boot Application which runs an H2 database and exposes a TCP port to allow access to it.

* **Distributed System Client**: Client desktop application for testing the distributed system.