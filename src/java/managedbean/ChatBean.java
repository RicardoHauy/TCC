/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ApplicationScoped
public class ChatBean implements Serializable {

    private List<String> listademensagens;
    private int contNotificacao;

    /**
     * Creates a new instance of ChatBean
     */
    public ChatBean() {

        listademensagens = new ArrayList<String>();
        contNotificacao = 0;
        listademensagens.add("Notificações! --> Atualize a página clicando em recarregar no navegador se necessário");

    }

    public void adicionarMensagem(String msg) {

        listademensagens.add(msg);
        contNotificacao++;
    }

    public List<String> getListademensagens() {
        return listademensagens;
    }

    public void setListademensagens(List<String> listademensagens) {
        this.listademensagens = listademensagens;
    }

    public int getContNotificacao() {
        return contNotificacao;
    }

    public void setContNotificacao(int contNotificacao) {
        this.contNotificacao = contNotificacao;
    }

}
