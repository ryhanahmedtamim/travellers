# travellers

To run this application:
	create a database
	update application.properties according to you new database.
	
		spring.datasource.url=jdbc:mysql://localhost:3306/travellers
		spring.datasource.username=root
		spring.datasource.password=
	
	Open the project in a IDE. Then run.
	
	Application address : localhost:9090
	
	
	At first you need to register a user. Then you can do all the operations. 
	
	
	
	For packaging:
		make the following changes in the pom.xml
			for war:
				<packaging>war</packaging>
				
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
					<scope>provided</scope>
				</dependency>
		</dependency>
			for jar:
				<packaging>jar</packaging>
				
				<dependency>
					<groupId>org.springframework.boot</groupId>
					<artifactId>spring-boot-starter-tomcat</artifactId>
				</dependency>
				
		Then run the following command:
			mvn clean package
			
			For jar file: 
				java -jar <file>
			For war file:
				use Tomcat server