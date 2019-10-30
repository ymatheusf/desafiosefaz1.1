
package br.com.sefaz.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sefaz.model.Usuario;
//recebe chamadas de usuario 
@SessionScoped
@ManagedBean(name = "usuarioController")
public class UsuarioController {
	
    public static final String INJECTION_NAME = "#{usuarioController}";
    private Usuario user;

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
}
