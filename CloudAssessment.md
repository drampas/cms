# Cloud assessment for Content Management System

A description of how the app would benefit from using cloud native services
along with the services that would be used to achieve a complete architecture in the cloud.

Initially for hosting our application we would use Lambda(AWS).Ideal for small applications
like ours that execute code in response to events (such as HTTP requests) or have low usage and
do not require to be run 24/7.Lambda takes care of any server updates or patches managing 
all aspects of the infrastructure.Also, Lambda scales automatically in response to the number
of requests,and so we don't need to manage scaling.

Next we would use API Gateway(AWS) which allows us to secure and monitor our application and also makes
it easier for our spring boot application to be deployed in Lambda.
API Gateway can also handle authentication and authorization for our application with built-in mechanisms like
IAM,Amazon Cognito User Pools and Lambda Authorizers.In our case we would use Amazon Cognito User Pools
that manages authentication and authorization using JWT (JSON Web Tokens).When it comes to monitoring,
 Api Gateway automatically publishes metrics to Amazon CloudWatch which allows us
to monitor the health and performance of our application.We can use these metrics(latency,error rates,
request count,etc.) to create alarms and notifications.

For our database we would use Amazon RDS (Relational Database Service) for
 MySql along with Amazon RDS Proxy which is a highly available database proxy designed to help 
with connection management in serverless environments like Lambda,in case we run into any problems with
the Hikari Connection pool of our spring application.Amazon RDS would host our database reducing 
the cost of maintaining a self-hosted 
database and also providing us with automatic updates.Furthermore, RDS provides us with
high availability meaning that our database would automatically failover to another instance in
case of a failure.Another benefit would be that we could dynamically scale our database storage
according to our needs.