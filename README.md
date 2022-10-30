# ParkingLotApplication

Practice with Spring for learning Dispatcher Servlet and Component Scan in Spring.

### Dispatcher Servlet
The **Dispatcher Servlet** is responsible for correctly coordinating *HttpRequests* to their right handlers.
The Spring **Dispatcher Servlet** handles an incoming *HttpRequest*, delegates the request and processes the request according to the configured 
*HandlerAdapter* interfaces that have been implemented withint the Spring application along with annotations specifying handlers, controller endpoints 
and response objects.

![dispatcher-servlet](https://user-images.githubusercontent.com/27693622/198833854-12be29ce-1529-41ab-bec3-885eaa5fde33.png)

1. **DispatcherServlet** receives the request.
2. **DispatcherServlet** dispatches the task of selecting an appropriate controller to **HandlerMapping**. **HandlerMapping** selects the controller
which is mapped to the incoming request URL and returns the selected **Handler** and **Controller** to **DispatcherServlet**
3. **DispatcherServlet** dispatches the task of executing business logic to **HandlerAdapter**
4. **HandlerAdapter** calls the business logic process of the **Controller**
5. The **Controller** executes the business logic, sets the processing result in **Model** and returns the logical name of the view to **HAndlerAdapter**
6. **DispatcherServlet** dispatches the task of resolving the **View** corresponding to the View name to **ViewResolver**. **ViewResolver** returns the **View**
mapped to the View name
7. **DispatcherServlet** dispatches the rendering process to the returned **View**
8. **View** renders **Model** data and returns the response

### Component Scan
The Spring ComponentScan scans the base package and reads classes with annotations and creates beans.

![component-scan](https://user-images.githubusercontent.com/27693622/198837369-d077b23d-fb85-4b32-b9b3-de812599574a.png)

**@ComponentScan** without arguments tells Spring to scan the current package and all of its sub-packages. The annotation
**@SpringBootApplication** is a combination of three annotations: **@Configuration**, **@EnableAutoConfiguration** and **@ComponentScan**.

### Swagger
Swagger is an open source project used to generate the REST API documents for RESTful web services. It provides a user interface to access our RESTful web services via the web browser.

![Screenshot 2022-10-30 at 15 46 57](https://user-images.githubusercontent.com/27693622/198888046-e6b4c2ae-4d30-4096-8111-7438bc6211fc.png)

It is added by inserting springdoc-openapi-ui as a dependency in the pom.xml file:
```java
		<dependency>
			<groupId>org.springdoc</groupId>
			<artifactId>springdoc-openapi-ui</artifactId>
			<version>1.6.9</version>
		</dependency>
```

The custom open API docs can then be added to the Spring context as a bean:
```java
	@Bean
	public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info().title("EMS API").version(appVersion)
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}
```
Documentation for the API is then available on http://localhost:8080/swagger-ui/index.html
