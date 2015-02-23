/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Lucas Whiather
 */
public class GenericBean implements Serializable {

    public void exibirMensagemAviso(String msg) { // Método que cria uma mensagem de aviso e lista na tela com o growl
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        System.out.println("Mensagem = " + msg);
        fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, "");
        fc.addMessage(null, fm);
    }

    public void exibirMensagemErro(String msg) {// Método que cria uma mensagem de Erro e lista na tela com o growl
        FacesContext fc = FacesContext.getCurrentInstance();
        FacesMessage fm;
        fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, "");
        fc.addMessage(null, fm);

    }

}
