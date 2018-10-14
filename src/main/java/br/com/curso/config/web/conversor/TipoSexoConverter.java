package br.com.curso.config.web.conversor;

import org.springframework.core.convert.converter.Converter;

import br.com.curso.config.domain.TipoSexo;

public class TipoSexoConverter implements Converter<String, TipoSexo> {

	//Essa classe foi criada, porque quando o spring recebe o dado do formulario, ele irá receber no formato texto, porém, o sexo é descrito no formato do tipo enum. Devemos fazer a conversão do texto para o enum, por isso a criação da classe, implementando a interface converter passando os dois parametros que serão convertido
	
	@Override
	public TipoSexo convert(String texto) {
		char tipo= texto.charAt(0);
		return tipo == TipoSexo.FEMININO.getDesc()? TipoSexo.FEMININO : TipoSexo.MASCULINO;
	}

}
