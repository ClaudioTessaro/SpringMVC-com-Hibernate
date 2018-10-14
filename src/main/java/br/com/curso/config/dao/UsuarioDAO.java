package br.com.curso.config.dao;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.curso.config.domain.TipoSexo;
import br.com.curso.config.domain.Usuario;

//essa notação é utilizada para persistencia de dados. Outra utilizada pode ser a componente
@Repository 
@Transactional //serve para metodos so de leitura e por definição o readOnly é false 

public class UsuarioDAO implements UsuarioDAOInterface<Usuario> {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public void salvar(Usuario usuario) {
		/*Aqui seriam transações caso o spring não fizesses essa tarefa para nós, portanto, teriamos que abriru uma transação e fazer tudo isso aqui
		 * 
		 * entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
		
		Como o spring vai gerenciar tudo isso, vamos precisar somente do metodo persist.
		*/
		entityManager.persist(usuario);
		
	}

	@Override
	public void editar(Usuario usuario) {
		entityManager.merge(usuario);
		
	}

	@Override
	public void excluir(Long id) {
		entityManager.remove(entityManager.getReference(Usuario.class, id));
		
	}

	@Transactional(readOnly=true)
	@Override
	public List<Usuario> getTodos() {
		String jpql= "from Usuario u";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		return query.getResultList();
	}

	 @Transactional(readOnly = true)
	 @Override
	 public Usuario getId(Long id) {
	           String jpql = "from Usuario u where u.id = :id";
	           TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
	           query.setParameter("id", id);
	           return query.getSingleResult();
	}

	@Transactional(readOnly = true)
	@Override
	public List<Usuario> geyBySexo(TipoSexo sexo) {
		String jpql = "from Usuario u where u.sexo = :sexo";
		TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
		query.setParameter("sexo", sexo);
		return query.getResultList();
	}
	@Transactional(readOnly = true)
    @Override
    public List<Usuario> getByNome(String nome) {
        String jpql = "from Usuario u where u.nome like :nome or u.sobrenome like :sobrenome";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        query.setParameter("nome", "%"+nome+"%");
        query.setParameter("sobrenome", "%"+nome+"%");
        return query.getResultList();
    }

	

	/*
	 * private static List<Usuario> us; // essa lista estático irá armazenar os
	 * usuarios durante o tempo de execução, já // que é uma aplicação que rodará
	 * sobre memoria
	 * 
	 * public UsuarioDAO() { createUserList(); }
	 * 
	 * private List<Usuario> createUserList() { if (us == null) { us = new
	 * LinkedList<>(); us.add(new Usuario(System.currentTimeMillis()+1L, "Ana",
	 * "da Silva", LocalDate.of(1992, 5, 10), TipoSexo.FEMININO)); us.add(new
	 * Usuario(System.currentTimeMillis()+2L, "Luiz", "dos Santos",
	 * LocalDate.of(1990, 8, 11), TipoSexo.MASCULINO)); us.add(new
	 * Usuario(System.currentTimeMillis()+3L, "Mariana", "Mello", LocalDate.of(1988,
	 * 9, 17), TipoSexo.FEMININO)); us.add(new
	 * Usuario(System.currentTimeMillis()+4L, "Caren", "Pereira")); us.add(new
	 * Usuario(System.currentTimeMillis()+5L, "Sonia", "Fagundes")); us.add(new
	 * Usuario(System.currentTimeMillis()+6L, "Norberto", "de Souza")); }
	 * 
	 * return us; }
	 * 
	 * @Override public void salvar(Usuario usuario) {
	 * usuario.setId(System.currentTimeMillis()); us.add(usuario);
	 * 
	 * }
	 * 
	 * @Override public void editar(Usuario usuario) { us.stream().filter((u) ->
	 * u.getId().equals(usuario.getId())).forEach((u) -> {
	 * u.setNome(usuario.getNome()); u.setSobrenome(usuario.getSobrenome());
	 * u.setDtNascimento(usuario.getDtNascimento()); u.setSexo(usuario.getSexo());
	 * });
	 * 
	 * }
	 * 
	 * @Override public void excluir(Long id) { us.removeIf((u) ->
	 * u.getId().equals(id));
	 * 
	 * }
	 * 
	 * @Override public List<Usuario> getTodos() { return us;
	 * 
	 * }
	 * 
	 * @Override public Usuario getId(Long id) {
	 * 
	 * return us.stream().filter((u) ->
	 * u.getId().equals(id)).collect(Collectors.toList()).get(0); }
	 */
}
