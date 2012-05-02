# NITHs
### The student community's API.
##  [API reference](http://ec2-46-137-46-84.eu-west-1.compute.amazonaws.com/)

## Setup

### Prerequisites
- Git
- Tomcat 7
- MySQL Server > 5
- JDK 1.7
- Maven
- Eclipse / Netbeans / IntelliJ IDEA

### Download the source code
- `git clone git@github.com:niths/niths.git`

### Create the database
- Create a database named `niths`
    - From the command line run: `mysql -u <MySQL username> -e 'CREATE DATABASE niths;'`
    - From any other tool, consult the appropriate manual

- If you are looking for an easy to use, free database administrative tool, check out MySQL Workbench

### Configure the properties file
- If you already have been provided with this project's property file, skip this entire section
- Create a file named `application.properties`
- Paste the contents of [this Gist](https://gist.github.com/2226677), and edit the values
- Place the file at `niths/src/main/resources/application.properties`
- Make a copy of the file, rename it to `test-application.properties`, and place it at `niths/src/test/resources/test-application.properties`

### Build the application

- Finally you can build the application running `mvn clean install` inside the project's root from the command line

### Deploy
- Place `niths/target/niths.war` in `path/to/tomcat/installation/webapps/niths.war`
- Restart Tomcat: `service tomcat restart`
