# **Distributed Programming Microservices**

Microservice based post management system implemented using Spring Boot and the Spring framework.

## **Components**

* **User Management Services**: Restful APIs for CRUD user management.

* **Post Management Services**: Restful APIs for CRUD post management.

* **Integration Service**: Distributed system backend entry point. Receives requests from clients and forwards them to the proper handler service.
  
* **Load Balancer Service**: Load balancing and failovering service based on Spring Cloud's Eureka Service.

* **File Management Service**: File upload and download microservice.

* **TCP Service**: TCP based push notification microservice. Contains TCP endpoints, allowing clients to subscribe in order to receive push notifications when a new resource is created.

* **Distributed System Client**: Client desktop application for testing the distributed system.