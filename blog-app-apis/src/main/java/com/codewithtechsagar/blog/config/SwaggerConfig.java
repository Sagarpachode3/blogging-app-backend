package com.codewithtechsagar.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
//@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("your.controller.package"))
                .paths(PathSelectors.any())
                .build();
    }
    
    
    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("Blogging Aplication : Backend Course ")
                .description("This Project is develeoped by Sagar Pachode")
                .contact(new Contact("Sagar P", "https://pachodetechsolutions.com", "sagarpachode3@gmail.com"))
                .license("License of APIS")
                .licenseUrl("API License URL")
                .version("1.0.0")
                .build();
    }
}

/*
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.codewithtechsagar.blog"))
                .paths(PathSelectors.regex("/.*"))
                .build().apiInfo(apiInfoMetaData());
    }

    private ApiInfo apiInfoMetaData() {

        return new ApiInfoBuilder().title("Blogging Aplication : Backend Course ")
                .description("This Project is develeoped by Sagar Pachode")
                .contact(new Contact("Sagar P", "https://pachodetechsolutions.com", "sagarpachode3@gmail.com"))
                .license("License of APIS")
                .licenseUrl("API License URL")
                .version("1.0.0")
                .build();
    }

}
/*
	private ApiInfo getInfo() {
		
		return new ApiInfo("Blogging Aplication : Backend Course ", 
				"This Project is develeoped by Sagar Pachode",
				"1.0", "Terms of Service", new Contact("Sagar P", "https://pachodetechsolutions.com", "sagarpachode3@gmail.com"),
				"License of APIS", 
				"API License URL",
				Collections.emptyList());
	};
}*/
