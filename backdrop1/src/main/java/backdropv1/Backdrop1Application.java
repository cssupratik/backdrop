package backdropv1;

import java.util.Collection;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Backdrop1Application {

	public static void main(String[] args) {
		SpringApplication.run(Backdrop1Application.class, args);
	}
	
	@Bean
	public Docket swaggerConfig()  {
		return new Docket(DocumentationType.SWAGGER_2)
				          .select()
				          //.paths(PathSelectors.ant("/backdrop/v1/*"))
				          .paths(PathSelectors.any())
				          .apis(RequestHandlerSelectors.basePackage("backdropv1"))
				          .build()
				          .apiInfo(apiDetails());
				          
	}
	
	private ApiInfo apiDetails() {
		return new ApiInfo(
				           "Backdrop api", 
				           "Rest API to interact with DB data", 
				           "1.0", 
				           "Free to use", 
				           new Contact("Supratik", "https://github.com/cssupratik/backdrop", "cs.supratik@zoho.com"), 
				           "Free Licence", 
				           "https://github.com/cssupratik/backdrop", Collections.EMPTY_LIST);
	}

}
