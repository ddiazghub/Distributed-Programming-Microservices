Before deploying any services, create the namespace where the services will live by deploying the "group-03-namespace.yaml" file.

Services must be deployed in the following order for them to run correctly.
Services in the same list item can be deployed in any order:
- postgres-database-service and tcp-service
- user-management-service, post-management-service and file-management-service
- integration-service