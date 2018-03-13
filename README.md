# GCD Web Services
A simple enterprise Java application that demonstrates how RESTful and SOAP web services can be implemented with the following technologies and platform:
- Java API for RESTFul Web Services (JAX-RS) 2.0 
- Java API for XML-Based Web Services (JAX-WS) 2.2 
- Contexts and Dependency Injection (CDI) for Java 1.1
- Java Message Service (JMS) API 2.0 
- Java Persistence (JPA) 2.1 
- Enterprise JavaBeans (EJB) 3.2
- WildFly 10

## Functional Requirements
Develop an enterprise Java application that implements RESTful and SOAP web services that is secure.
#### 1. RESTful service will expose two methods:
-  `public String push(int i1, int i2);`

    Returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue.
-  `public List<Integer> list();`

    Returns a list of all the elements ever added to the queue from a database in the order added as a JSON structure. 
#### 2. SOAP service will expose the following method as operations:
- `public int gcd();`

    Returns the GCD (greatest common divisor) of the two integers at the head of the queue. These two elements will subsequently be discarded from the queue and the head replaced by the next two in line.
- `public List<Integer> gcdList();`

    Returns a list of all the computed greatest common divisors from a database. 
- `public int gcdSum();`

    Returns the sum of all computed greatest common divisors from a database.

## Geting Started
#### Prerequisites
To run the web application, the following softwares are needed on the machine:
- Java 1.8-x build
- Maven 3.0.4 or above
- WildFly 10.0.0.Final or above
- MySQL Community Edition 5.7.21 and JDBC driver (Optional)

#### Setup WildFly
Start WildFly 10 in full profile mode. This mode supports JMS.
> $ cd /PATH_TO/wildfly-10.0.0.Final/bin
> $ ./standalone.sh -c standalone-full.xml

Create a new JMS queue in a new console.
> $ ./jboss-cli.sh --connect
> $ jms-queue add --queue-address=gcdQueue --entries=queue/gcd,java:jboss/exported/jms/queue/gcd

Add MySQL JDBC driver to WildFly (Optional).
- Copy `mysql/mysql` folder from the project to 
   ` /PATH_TO/wildfly-10.0.0.Final/modules/system/layers/base/com/`
   This folder includes module.xml and JDBC jar file.
- Add the following segment to `<drivers>` section in standardalone-full.xml file.
   ```xml
    <driver name="mysql" module="com.mysql">
        <driver-class>com.mysql.jdbc.Driver</driver-class>
    </driver>
    ```
## Deploy the Application
1. Make sure the WildFly 10 server is running.
2. Open a console and change current directory to the local copy of this repository.
3. Run the following command to build and deploy the .ear file.
    >$ mvn clean package wildfly:deploy

## Access the Web Services
#### 1. RESTful service
- Push numbers

   >$ curl -d "i1=value1&i2=value2" -X POST http://localhost:8080/gcd-web/services/source/push
- Get all numbers added

   >$ curl -X GET http://localhost:8080/gcd-web/services/source/list
  
Other third party tools, like [Postman](https://www.getpostman.com), can help the testing.

#### 2. Soap service
- WSDL

   `http://localhost:8080/gcd-web/GcdWS?wsdl`
- SOAP endpoint

   `http://localhost:8080/gcd-web/GcdWS`

[SoapUI](https://www.soapui.org) can be used here for testing the operations.

## Undeploy the Application
1. Make sure the WildFly 10 server is running.
2. Open a console and change current directory to the local copy of this repository.
3. Run the following command to build and deploy the .ear file.
    >$ mvn wildfly:undeploy

## Datasource for Testing
By default this application uses H2 database for testing. The datasource is configured in `gcd-ds.xml` file in EAR module. The user does not need to do any change to deploy and run the application.

MySQL server is also supported with the completion of the optional steps mentioned above . To switch to MySQL,
1. Run `mysql/database.sql` to create the database in your MySQL server.
2. Update the `gcd-ds.xml' file. 
3. Update the value of `hibernate.hbm2ddl.auto` from `create-drop` to `validate`  in the `persitent.xml` file in EJB module. 

Of course, at anytime the user is able to create a mangaged datasource (`GcdDS`) in the WildFly server and switch to it by removing the `gcd-ds.xml' file in the archive.



