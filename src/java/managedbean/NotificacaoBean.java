/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@SessionScoped
public class NotificacaoBean implements Serializable {

    private int contNotificacao;

    @ManagedProperty(value = "#{chatBean}")
    private ChatBean chat;

    /**
     * Creates a new instance of NotificacaoBean
     */
    public NotificacaoBean() {
    }

    public void adicionarMensagem(String msg) {

        chat.adicionarMensagem(msg);
        contNotificacao++;
    }

    public void atualizarNotificacao() {

        contNotificacao = chat.getContNotificacao();

    }

    public ChatBean getChat() {
        return chat;
    }

    public void setChat(ChatBean chat) {
        this.chat = chat;
    }

    public int getContNotificacao() {
        return contNotificacao;
    }

    public void setContNotificacao(int contNotificacao) {
        this.contNotificacao = contNotificacao;
    }

}
