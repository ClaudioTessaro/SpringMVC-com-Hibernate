package br.com.curso.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.curso.config.web.conversor.TipoSexoConverter;

@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/resources/bootstrap/");
    }
	
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/"); //Aqui é mostrando qual o caminho que será utilizado as views
		resolver.setSuffix(".jsp"); //Aqui ele mostrará qual o prefixo do meu arquivo
		resolver.setViewClass(JstlView.class); //Informa qual o tipo de recursos serão utilizados
		
		return resolver;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new TipoSexoConverter()); //COm isso, o spring automaticamente quando encontrar o atributo tipo sexo, ele fará a conversão
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasename("messages"); //Aqui é o nome do arquivo sem a extensão
		return source;
	}

	
	
}
