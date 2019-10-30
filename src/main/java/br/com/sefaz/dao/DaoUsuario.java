package br.com.sefaz.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.sefaz.model.Usuario;
import br.com.sefaz.util.HibernateUtil;
//Herança do DaoGeneric Para Utiliza os cruds
public class DaoUsuario<E> extends DaoGeneric<Usuario> {
	
	private static EntityManager entityManager = HibernateUtil.getEntityManager();
	
	    
		public List<Usuario> obterUsuarios() {//DaoU1 lista todos os usuarios
			List<Usuario> listaUsuarios = new ArrayList<>();
			Query q = entityManager.createQuery("from Usuario c");
			listaUsuarios = q.getResultList();
			return listaUsuarios;
		}
		
		
		public static Usuario findByEmail(String email) {//DaoU2 busca o usuario por email
			String query = "from Usuario u WHERE u.email = :emailParam";
			Query q = entityManager.createQuery(query);
			q.setParameter("emailParam", email);
			Usuario user = null;
			try{
			user = (Usuario) q.getSingleResult();
			}
			catch (NoResultException nre) {//DaoU2.1 null caso não tenha esse email cadastrado
			
			}
			return user;
		}
		
		
		public static Usuario findById(Long id) {//DaoU3 buscar por id
			String query = "from Usuario u WHERE u.id = :idParam";
			Query q = entityManager.createQuery(query);
			q.setParameter("idParam", id);
			Usuario user = null;
			try{
			user = (Usuario) q.getSingleResult();
			}
			catch (NoResultException nre) {//DaoU3.1null caso não tenha esse id cadastrado
			
			}
			return user;
		}

}
