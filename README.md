# Content Management System
Content Management System (cms) is a Rest API which is build using Spring Boot 3 
and JSON Web Tokens(JWT).The API will allow users to perform CRUD (Create, Read,
Update, Delete) operations on articles and manage images associated with those articles.In the project we make use of the N-tier architecture
which in our case is comprised of three layers.The presentation layer (our endpoints
where clients interact with our api),the business layer (where the business 
logic is implemented) and finally the data layer (where our application interacts
with a database).

## Features
• Login: Admin users log in with a username and password.<br>
• Guest Access: Guests can browse articles without logging in.<br>
• CRUD operations: Admin can create,update or delete an article and upload an image<br>
• Read Articles: Both admins and guests can view a list of articles, read individual articles,
and see uploaded images.<br>

## Tecnologies
• Spring Boot 3.1.4<br>• Spring Security<br> • JSON Web Tokens(JWT)<br> 
• Project Lombok<br> • MySql 8.0.31<br> •
OpenApi<br> • Maven

## Getting started
To get started with this project, you will need to have the following installed on your local machine:<br>
• JDK 17+<br>
• Maven 3+<br>
• MySql 8<br>

To build and run the project, follow these steps:<br>
• Clone the repository to your local machine<br>
• Add database cms_prod to MySql by running this [script]() *.<br>
• Build the project: mvn clean install<br>
• Run the project: mvn spring-boot:run<br>

The application will be available at http://localhost:8080.<br>
Documentation will be available at http://localhost:8080/swagger-ui/index.html.