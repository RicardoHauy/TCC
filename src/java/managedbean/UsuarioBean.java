/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.UsuarioDAO;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import lazy.UsuarioLazy;
import modelo.Administrador;
import modelo.Usuario;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@SessionScoped
public class UsuarioBean extends GenericBean implements Serializable {

    private Usuario usuario;

    @ManagedProperty(value = "#{loginBean.administrador}")
    private Administrador administrador;

    private LazyDataModel<Usuario> usuarios;

    public UsuarioBean() {

        usuarios = new UsuarioLazy();

    }

    public void novoCadastro() {

        usuario = new Usuario();
        usuario.setAdministrador(administrador);

    }

    public void salvarUsuario() {

        if (usuario.getCodigo() == null) {

            try {

                UsuarioDAO daoUsuario = new UsuarioDAO();

                daoUsuario.inserir(usuario);
                exibirMensagemAviso("Advogado Inserido Com Sucesso!");
                novoCadastro();

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Cadastrar Advogado! -> " + ex.getMessage());

            }
        } else {

            try {

                UsuarioDAO daoUsuario = new UsuarioDAO();
                daoUsuario.alterar(usuario);
                exibirMensagemAviso("Dados Alterados com Sucesso!");

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Dados! -> " + ex.getMessage());

            }
        }
    }

    public void alterarUsuario(Usuario usuario) {

        // System.out.println("Altera Usuario u.cod= "+usuario.getCodigo());
        this.usuario = usuario;

    }

    public void excluirUsuario(Usuario u) {

        try {

            UsuarioDAO daoUsuario = new UsuarioDAO();

            daoUsuario.excluir(u);

            exibirMensagemAviso("Excluído Com Sucesso!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Exclusão! -> " + ex.getMessage());
        }

    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public LazyDataModel<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(LazyDataModel<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
