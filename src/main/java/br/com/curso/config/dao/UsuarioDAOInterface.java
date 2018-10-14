package br.com.curso.config.dao;

import java.util.List;

import br.com.curso.config.domain.TipoSexo;
import br.com.curso.config.domain.Usuario;

public interface UsuarioDAOInterface<T> {

	 void salvar(T obj);
	 void editar(T obj);
	 void excluir(Long id);
	 List<T> getTodos();
	 T getId(Long id);
	 List<T> geyBySexo (TipoSexo sexo);
	List<Usuario> getByNome(String nome);
}
