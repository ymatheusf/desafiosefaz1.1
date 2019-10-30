
package br.com.sefaz.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
//Contém metodos que facilitam a chamada de mensagens de informação ou erro
public class JSFMessageUtil {
	
     public void sendInfoMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_INFO, message);
        addMessageToJsfContext(facesMessage);
    }
 
    public void sendErrorMessage(String message) {
        FacesMessage facesMessage = createMessage(FacesMessage.SEVERITY_ERROR, message);
        addMessageToJsfContext(facesMessage);
    }
 
    private FacesMessage createMessage(FacesMessage.Severity severity, String mensagem) {
        return new FacesMessage(severity, "Informação: ", mensagem);
    }
 
    private void addMessageToJsfContext(FacesMessage facesMessage) {
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
    
}
