package br.com.sefaztest;

import java.util.List;

import org.junit.Test;

import br.com.sefaz.dao.DaoGeneric;
import br.com.sefaz.model.Telefone;
import br.com.sefaz.model.Usuario;

public class JUnitTests {

	@Test
	public void testeHU() {
		
		DaoGeneric<Usuario> daoGeneric =
				new DaoGeneric<Usuario>();// inicia o Dao
		Usuario pessoa = new Usuario(); // inicia o objeto pra salvar
		//Cria o primeiro usuario
		pessoa.setId(null);
		pessoa.setNome("Sefaz");
		pessoa.setEmail("TouTestandoParceiro@sefaz.com");
		pessoa.setSenha("4dm1n");

		daoGeneric.salvar(pessoa);//salva no banco

	}

	@Test
	public void testedebusca() {
		DaoGeneric<Usuario> daoGeneric = 
				new DaoGeneric<Usuario>();//inicia o Dao
		Usuario pessoa = new Usuario();// Inicia o Objeto 
		pessoa.setId(2L);// Busca so pelo ID

		pessoa = daoGeneric.pesquisar(pessoa); // Chama a pesquisa

		System.out.println(pessoa); // Printa Na Tela

	}

	@Test
	public void testedebusca12() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		Usuario pessoa = daoGeneric.pesquisar(1L, Usuario.class);

		System.out.println(pessoa);

	}

	@Test
	public void updateteste() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		Usuario pessoa = daoGeneric.
				pesquisar(1L, Usuario.class);//Puxa o obejeto pra editar

		// Editar e salvar 
		pessoa.setNome("atualizado");
		pessoa.setSenha("S4LV3r");

		pessoa = daoGeneric.updateMerge(pessoa);// Salva a edição feita no banco

		System.out.println(pessoa);

	}

	@Test
	public void delete() {
		DaoGeneric<Usuario> daoGeneric = new 
				DaoGeneric<Usuario>();// inicia o dao

		Usuario pessoa = daoGeneric.
				pesquisar(3L, Usuario.class);// busca o obejto antes da remoção

		try {
			daoGeneric.deletarPorId(pessoa);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// chama e remove do banco

	}

	@Test
	public void consultar() {//Inicia o dao
		DaoGeneric<Usuario> daoGeneric = 
				new DaoGeneric<Usuario>(); 

		List<Usuario> list = daoGeneric.//chama passando a classe
				listar(Usuario.class);

		for (Usuario Usuario : list) {//procura na lista se esta correto
			System.out.println(Usuario);
			System.out.println("--------------------------------------------------");
		}

	}

	@Test
	public void querry() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		List<Usuario> list = daoGeneric.getEntityManager()
				.createQuery(" from Usuario where nome = 'sefaz'").getResultList();

		for (Usuario Usuario : list) {
			System.out.println(Usuario);
		}

	}

	@SuppressWarnings("unchecked")
	
	@Test
	public void querrymax() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();
		List<Usuario> list = daoGeneric.getEntityManager().
				createQuery(" from Usuario order by id")
				.setMaxResults(5).getResultList();

		for (Usuario Usuario : list) {
			System.out.println(Usuario);
		}

	}

	@SuppressWarnings("unchecked")
	
	@Test
	public void querryparam() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		List<Usuario> list = (List<Usuario>) daoGeneric.getEntityManager()
				.createQuery("from Usuario where nome = :nome")
				.setParameter("nome", "sefaz");

		for (Usuario Usuario : list) {
			System.out.println(Usuario);
		}
	}

	@SuppressWarnings("unchecked")
	
	
	@Test
	public void namequerry() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		List<Usuario> list = daoGeneric.getEntityManager().
				createNamedQuery("Usuario.todos")
				.getResultList();

		for (Usuario Usuario : list) {
			System.out.println(Usuario);
		}

	}

	@SuppressWarnings("unchecked")
	
	@Test
	public void namequerry2() {
		DaoGeneric<Usuario> daoGeneric = new DaoGeneric<Usuario>();

		List<Usuario> list = daoGeneric.getEntityManager().
				createNamedQuery("Usuario.buscarEmail")
				.setParameter("email", "toutestando@sefaz.com")
				.getResultList();

		for (Usuario Usuario : list) {
			System.out.println(Usuario);
		}

	}
	
	@Test
	public void salvephone(){
		DaoGeneric daoGeneric = new DaoGeneric();
		
		Usuario pessoa =  (Usuario) daoGeneric.pesquisar(28L, Usuario.class);
		
		Telefone Telefone = new Telefone();
		
		Telefone.setTipo("Fixo");
		Telefone.setDdd("(84)");
		Telefone.setNumero("0500-2011");
		Telefone.setUsuario(pessoa);
		
		daoGeneric.salvar(Telefone);
		
	}
	
	
	
	@Test
	public void buscaphone(){
		DaoGeneric daoGeneric = new DaoGeneric();
		
		Usuario pessoa =  (Usuario) daoGeneric.pesquisar(24L, Usuario.class);
		
		for (Telefone fone : pessoa.getTelefoneUsers()) {
			System.out.println(fone.getNumero());
			System.out.println(fone.getDdd());
			System.out.println(fone.getTipo());
			System.out.println(fone.getUsuario().getNome());
			System.out.println("----------------------------------");
		}
		
	}

}