package br.univille;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@SpringBootApplication
public class FabsoftBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FabsoftBackendApplication.class, args);
	}

	// CONFIGURAÇÃO DE CORS DEFINITIVA
	// Colocada aqui para garantir que o Spring carregue junto com a app
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*"); // Libera qualquer origem
		config.addAllowedHeader("*");        // Libera qualquer header (incluindo Content-Type)
		config.addAllowedMethod("*");        // Libera GET, POST, PUT, DELETE...

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}