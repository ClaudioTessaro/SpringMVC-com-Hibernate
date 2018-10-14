package br.com.curso.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration //Ele diz ao spring que essa é uma classe de configuração no spring
@ComponentScan("br.com.curso") //Serve para dizer ao framework onde ele deve procurar as classes que ele deve gerenciar
@EnableWebMvc //Mostra que usaremos o mvc
public class RootConfig {
	
	

}
