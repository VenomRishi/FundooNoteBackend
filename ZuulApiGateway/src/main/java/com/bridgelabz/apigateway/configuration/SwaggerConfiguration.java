package com.bridgelabz.apigateway.configuration;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Primary
@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements SwaggerResourcesProvider {
	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<SwaggerResource>();
		resources.add(swaggerResource("user-service", "/user/v2/api-docs", "2.0"));
		resources.add(swaggerResource("note-service", "/note/v2/api-docs", "2.0"));

		List<SwaggerResource> resources2 = new ArrayList<>();
		resources2.clear();
		resources2.addAll(resources);
		return resources2;

	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

//	@Primary
//	@Bean
//	@Lazy
//	public SwaggerResourcesProvider swaggerResourcesProvider(InMemorySwaggerResourcesProvider defaultResourcesProvider
//			) {
//		return () -> {
//			List<SwaggerResource> resources = new ArrayList<>(defaultResourcesProvider.get());
//			resources.clear();
//			resources.add(swaggerResource("user-service", "/user/v2/api-docs", "2.0"));
//			resources.add(swaggerResource("note-service", "/note/v2/api-docs", "2.0"));
////			resources.addAll(definitionContext.getSwaggerDefinitions());
//			return resources;
//		};
//	}

//	@Bean
//    public Docket api(){
//        return new Docket(DocumentationType.SWAGGER_2)
//          .select()
//          .apis(RequestHandlerSelectors.basePackage("com.bridgelabz.apigateway"))
//          .paths(PathSelectors.any())
//          .build().apiInfo(new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Online Store", "1.0",
//  				"Terms of service", new Contact("Rishikesh Mhatre", "", "mhatrerishikesh@gmail.com"),
//  				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0"));
//}

//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.bridgelabz.apigateway")).build().apiInfo(metaData());
//	}
//
//	/**
//	 * Purpose: this method is used to add extra data which will give user a proper
//	 * idea about the API(Application Programming Interface) information in the
//	 * swagger UI(User Interface) console
//	 * 
//	 * @return return the swagger API Info
//	 */
//	private ApiInfo metaData() {
//		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Online Store", "1.0",
//				"Terms of service", new Contact("Rishikesh Mhatre", "", "mhatrerishikesh@gmail.com"),
//				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
//		return apiInfo;
//	}
}
