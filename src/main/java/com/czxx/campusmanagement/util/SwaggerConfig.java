package com.czxx.campusmanagement.util;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
 
@Configuration
@EnableSwagger2
@EnableWebMvc
@ComponentScan(basePackages = {"com.czxx.campusmanagement.handler"})
public class SwaggerConfig {
	 @Bean
	    public Docket customDocket() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .apiInfo(apiInfo())      
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build();
	    }
 
	    private ApiInfo apiInfo() {
	        Contact contact = new Contact("支付组", "", "");
	        return new ApiInfoBuilder()
	                .title("支付API接口")
	                .description("支付API接口")
	                .contact(contact)
	                .version("1.1.0")
	                .build();
	    }
}

