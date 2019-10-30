package br.com.sefaz.managedbean;

import br.com.sefaz.dao.DaoUsuario;
import br.com.sefaz.model.Usuario;
import br.com.sefaz.util.JSFMessageUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "usuarioManagedBean")
@ViewScoped
public class UsuarioManagedBean implements Serializable {

	/**
	 * @author MATHEUS
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();
	private List<Usuario> list = new ArrayList<Usuario>();
	private DaoUsuario<Usuario> daoGeneric = new DaoUsuario<Usuario>();
	private JSFMessageUtil message = new JSFMessageUtil();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public String novo() {
		usuario = new Usuario();
		return "";
	}
	
	
	public Usuario userpadrao() {//Definindo um Usuario Padrão
    	Usuario useradmin = new Usuario();
    	useradmin.setId(null);
    	useradmin.setEmail("desafio@sefaz.com");
    	useradmin.setNome("Sefaz");
    	useradmin.setSenha("sefaz123");
    	daoGeneric.salvar(useradmin);
        return useradmin;    	
	}

	public String salvar() {//Patterns para email,senha,nome
		
		String patternemail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		String patternsenha = "(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{6,15}";
		String patternname = "^[a-zA-Z\\s]+";
		
		String email1 = usuario.getEmail();
		Long id1 = usuario.getId();
		
		
		if (DaoUsuario.findById(id1) != null){//Verificação de Id Se ainda não foi registado 
            message.sendErrorMessage("Erro,Clique Novamente.");
                return "id-invalido";
        
        }
		
		
		else if (!usuario.getNome().matches(patternname)) {//Fazendo verificação se o nome contem caracteres especiais.
            message.sendErrorMessage("Nome invalido Tente Outro.");
				return "nome-invalido";
		
        }
		
		
		
		else if(!(DaoUsuario.findByEmail(email1) == null) || !email1.matches(patternemail)) { //fazendo a verificação do email.
            message.sendErrorMessage("email invalido tente outro.");
				return "email-existe";
		
        }
		
		
		
		else if (!usuario.getSenha().matches(patternsenha)) {//Verificação de senha se esta dentro dos padroes
            message.sendErrorMessage("Senha Invalida,Deve Conter Letras e Numeros!");
				return "senha-invalida";

        } 
		
		else { //Se Tudo ocorrer corretamente chama pessist e adiciona no banco o novo usuario
            daoGeneric.salvar(usuario);
				list.remove(usuario);
				list.add(usuario);
				message.sendInfoMessage("Salvo com sucesso!");
				return "usuario-salvo";
        }
   
}
	
	public String editar() {
		
		
		if (usuario.getId() == null) {//Verificando se o usuario clicou anteriomente e salvou o id para fazer a alteção
			message.sendErrorMessage("ID Invalido,Clique Novamente no Editar.");
			return "missing-id";
			
		}
		
		
		else {//Se o email ja existe ou email valido. 
			
			String email1 = usuario.getEmail();
			String email2 = DaoUsuario.findById(usuario.getId()).getEmail();
			
			if (!email1.equals(email2) && DaoUsuario.findByEmail(email1) != null) {
			message.sendErrorMessage("e-mail já Esta Cadastrado Tente Outro!");
			return "email-existe";
			
		
		} 
			
			
			else {//edita usando o usuario como parametro para a edição	
			daoGeneric.updateMerge(usuario);
			message.sendInfoMessage("Salvo com sucesso!");
			return "usuario-editado";
		}
			
	  }
		
	}
	
	public String remover() {
		try {//Faz um select pelo id e remove o usuario
			daoGeneric.deletarPorId(usuario);
			list.remove(usuario);
			usuario = new Usuario();
			message.sendInfoMessage("Deletado com sucesso!");

		
		} catch (Exception e) {//Verifica se não tem nenhum telefone salvo no usuario
			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
				message.sendErrorMessage("Usuario Contem Telefones deleteos primeiro!");
			}else {
				e.printStackTrace();
			}
		}

		return "";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Usuario> obterUsuario() {//Lista todos os usuarios
		DaoUsuario usuarioDAO = new DaoUsuario();
		return usuarioDAO.obterUsuarios();
	}

}
