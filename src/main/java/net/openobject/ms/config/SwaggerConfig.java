package net.openobject.ms.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket api() {
        List<Parameter> parameters = new ArrayList<>();
        parameters.add(getJwtHeader());
		
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.openobject.ms"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(parameters)
                .enable(CommonConfig.isDev());
    }
	
	private static Parameter getJwtHeader() {
		return new ParameterBuilder()
		    	.name("jwt-header")
		    	.modelRef(new ModelRef("string"))
		    	.parameterType("header")
		    	.description("JSON Web Token")
		    	.required(true)
		    	.build();
	}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Server API")
                .description("Server API를 정의한 문서입니다.")
                .contact(new Contact("1leaf - github", "https://github.com/kis6905", "kis6905@gmail.com"))
                .version("0.1")
                .build();
    }
    
}
