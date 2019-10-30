
package br.com.sefaz.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.sefaz.util.HibernateUtil;
//DaoGeneric Com os Cruds
@SuppressWarnings("unchecked")
public class DaoGeneric<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();


	public void salvar(E entidade) {// DaoG1 Grava no banco
		EntityTransaction transaction = entityManager.getTransaction(); 
		transaction.begin(); 
		entityManager.persist(entidade); 
		transaction.commit(); 
	}
	
	

	public E updateMerge(E entidade) {// DaoG2 salva, atualiza e retorna. 
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E entidadeSalva = entityManager.merge(entidade); 
		transaction.commit();

		return entidadeSalva;
	}

	public E pesquisar(Long id, Class<E> entidade) {
		entityManager.clear();
		E e = (E) entityManager.createQuery("from " + entidade.getSimpleName() + " where id = " + id).getSingleResult();
		return e;
	}
	
	public E pesquisar(E entidade) {
		Object id = HibernateUtil.getPrimaryKey(entidade);
		E e = (E) entityManager.find(entidade.getClass(), id);
		return e;
	}
   
	
	public void deletarPorId(E entidade) throws Exception{//DaoG3 obtem o id e deleta do banco
		Object id = HibernateUtil.getPrimaryKey(entidade);  
		EntityTransaction transaction = entityManager.getTransaction(); 
		transaction.begin(); 

		entityManager
				.createNativeQuery(
						"delete from " + entidade.getClass().
						getSimpleName().toLowerCase() + " where id =" + id)
				.executeUpdate(); 
		transaction.commit(); 
	}

	public List<E> listar(Class<E> entidade) {// DaoG4 retorna a lista.
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		List<E> lista = entityManager.
				createQuery("from " + entidade.getName())
				.getResultList(); 
		transaction.commit();

		return lista;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}

}
