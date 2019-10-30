package br.com.sefaz.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.dao.DaoTelefones;
import br.com.sefaz.dao.DaoUsuario;
import br.com.sefaz.model.Telefone;
import br.com.sefaz.model.Usuario;
import br.com.sefaz.util.JSFMessageUtil;
//Classe dos dados de contato do usuario , Usuario é inicializado quando a pagina já é acessada
@ManagedBean(name = "telefoneManagedBean")
@ViewScoped
public class TelefoneManagedBean {

	private Usuario user = new Usuario();
	private Telefone telefone = new Telefone();
	private DaoUsuario<Usuario> daoUser = new DaoUsuario<Usuario>();
	private DaoTelefones<Telefone> daoTelefone = new DaoTelefones<Telefone>();
	private JSFMessageUtil message = new JSFMessageUtil();

	@PostConstruct
	public void init() {

		String coduser = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap()
				.get("codigouser");
		user = daoUser.pesquisar(Long.parseLong(coduser), Usuario.class);

	}
	
	public String salvar(){//BeanT1 Função para salvar o telefone
		telefone.setUsuario(user);
		daoTelefone.salvar(telefone);
		telefone = new Telefone();
		user = daoUser.pesquisar(user.getId(), Usuario.class);
		message.sendInfoMessage("Telefone Armazenado com sucesso!");		
		return "";
	}
	
	public String removeTelefone() throws Exception{//BeanT2 função para remover o telefone
		
		daoTelefone.deletarPorId(telefone);
		user = daoUser.pesquisar(user.getId(), Usuario.class);
		telefone = new Telefone();
		message.sendInfoMessage("Telefone Retirado com sucesso!");
		return "";
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Usuario getUser() {
		return user;
	}

	public void setDaoTelefone(DaoTelefones<Telefone> daoTelefone) {
		this.daoTelefone = daoTelefone;
	}

	public DaoTelefones<Telefone> getDaoTelefone() {
		return daoTelefone;
	}

}
