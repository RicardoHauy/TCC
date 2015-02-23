/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.PastaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Pasta;
import modelo.Pessoa;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class PastaBean extends GenericBean {

    private Pasta pasta;
    private Pessoa pessoa;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public PastaBean() {

        pasta = null;

    }

    public List<Pasta> exibirPastas(Pessoa pes) {//Exibe pastas no datatable

        pessoa = pes;

        try {

            PastaDAO daoPasta = new PastaDAO();
            return daoPasta.getByPessoa(pessoa);

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Mostrar Pastas");
            System.out.println("Erro na busca de Pastas --> " + ex.getMessage());
            return null;
        }

    }

    public void novoCadastroPasta() {

        pasta = new Pasta();

    }

    public void excluirPastaDaPessoa(Pasta pta) {

        try {
            PastaDAO daoPasta = new PastaDAO();
            daoPasta.getSession().clear();
            daoPasta.excluir(pta);
            exibirMensagemAviso("Excluída Com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Pasta!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro ao Excluír Pasta!");
            System.out.println("Erro Ao Excluir Pasta --> " + ex.getMessage());

        }

    }

    public void salvarPasta() {

        Pasta pst;

        PastaDAO pastaDao = new PastaDAO();
        try {
            pst = pastaDao.getByNumeroAndArmario(pasta.getNumeropasta(), pasta.getIdarmario());

            if (pst != null) {

                exibirMensagemErro("Pasta Já Existente neste Armário!");

            } else {

                pasta.setPessoa(pessoa);

                pastaDao.inserir(pasta);

                exibirMensagemAviso("Pasta Inserida com Sucesso!");

                novoCadastroPasta();
                chat.adicionarMensagem(usuario.getNome() + " Adicionou Pasta!");

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao SALVAR Pasta!");
            System.out.println("Erro Ao SALVAR PASTA --> " + ex.getMessage());
        }

    }

    public Pasta getPasta() {
        return pasta;
    }

    public void setPasta(Pasta pasta) {
        this.pasta = pasta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public NotificacaoBean getChat() {
        return chat;
    }

    public void setChat(NotificacaoBean chat) {
        this.chat = chat;
    }

}
