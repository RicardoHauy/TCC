/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.AdministradorDAO;
import dao.UsuarioDAO;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import modelo.Administrador;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@SessionScoped
public class LoginBean extends GenericBean implements Serializable {

    private String login;
    private String senha;
    private Administrador administrador;
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean notBean;

    private Integer logado;

    public LoginBean() {

        administrador = null;
        usuario = null;
        logado = 0;
    }

    public String logarAdmin() {

        try {

            AdministradorDAO administradorDao = new AdministradorDAO();

            administrador = administradorDao.getByLoginAndSenha(login, senha);
        } catch (Exception ex) {
            exibirMensagemErro("Logar Admin = Erro ao buscar Administrador");
        }

        if (administrador != null) {

            logado = 1;
            exibirMensagemAviso("Você está Logado! " + administrador.getNome());
            login = "";
            senha = "";
            return "Administrador/moduloAdministrador.xhtml";
        } else {

            exibirMensagemErro("Nome de Usuário e/ou Senha Inválido(s)");

            login = "";
            senha = "";

            return "/loginAdmin.xhtml";

        }

    }

    public String logarAdvog() {

        try {

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            usuario = usuarioDAO.getByLoginAndSenha(login, senha);
        } catch (Exception ex) {
            exibirMensagemErro("Logar Advogado = Erro ao buscar Advogado -->" + ex.getMessage());
        }

        if (usuario != null) {

            logado = 2;
            exibirMensagemAviso("Você está Logado! " + usuario.getNome());
            login = "";
            senha = "";
            notBean.adicionarMensagem(usuario.getNome() + " Acabou de Entrar no Sistema");
            return "Advogado/moduloAdvogado.xhtml";
        } else {

            exibirMensagemErro("Nome de Usuário e/ou Senha Inválido(s)");

            login = "";
            senha = "";

            return "/loginAdvog.xhtml";

        }

    }

    public String fazerLogoff() {

        administrador = null;
        logado = 0;
        usuario = null;
        login = "";
        senha = "";

        exibirMensagemAviso("Você Saiu do Sistema!");

        return "/index.xhtml";

    }

    public Integer getLogado() {
        return logado;
    }

    public void setLogado(Integer logado) {
        this.logado = logado;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NotificacaoBean getNotBean() {
        return notBean;
    }

    public void setNotBean(NotificacaoBean notBean) {
        this.notBean = notBean;
    }

}
