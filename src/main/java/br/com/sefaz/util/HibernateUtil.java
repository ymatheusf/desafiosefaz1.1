

package br.com.sefaz.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
//Responsavel por guardar os metodos da persistence.
@ApplicationScoped
public class HibernateUtil {
	
	public static EntityManagerFactory factory = null;

	static {
		init();
	}
	
	private static void init() {
		try {
			if (factory == null) {
				factory = Persistence.
						createEntityManagerFactory("PERSISTENCE"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}

	@Produces
    @RequestScoped
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

	public static Object getPrimaryKey(Object entity) { //Hu1 função para obter a chave primaria(OPCIONAL).
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
