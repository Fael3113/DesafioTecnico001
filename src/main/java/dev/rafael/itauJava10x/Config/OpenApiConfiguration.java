package dev.rafael.itauJava10x.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
			.info(new Info()
				.title("Desafio Itau + Java10x")
				.description("Api desafio técnico do Itau + Java10x")
				.version("1.0.0")
		);
	}

}
