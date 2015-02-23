/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.SentencaDAO;
import dao.TipoSentencaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Sentenca;
import modelo.TipoSentenca;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class TipoSentencaBean extends GenericBean {

    private TipoSentenca tipoSentenca;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public TipoSentencaBean() {

        tipoSentenca = null;

    }

    public void novoCadastroTipoSentenca() {

        tipoSentenca = new TipoSentenca();

    }

    public void alterarTipo(TipoSentenca tipo) {

        this.tipoSentenca = tipo;

    }

    public void excluirTipo(TipoSentenca tipo) {

        try {

            TipoSentencaDAO daoTipoSentenca = new TipoSentencaDAO();

            //daoTipoSentenca.getSession().clear();
            daoTipoSentenca.excluir(tipo);

            exibirMensagemAviso("Exclusão Realizada Com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Tipo de Sentença!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Excluir Tipo!");
            System.out.println("Erro ao Excluir TipoSentenca --> " + ex.getMessage());

        }

    }

    public void salvarTipoSentenca() {

        if (tipoSentenca.getCodigotiposentenca() == null) {

            try {

                TipoSentencaDAO daoTipoSentenca = new TipoSentencaDAO();

                TipoSentenca ts;

                ts = daoTipoSentenca.getByNome(tipoSentenca);

                if (ts == null) {

                    daoTipoSentenca.inserir(tipoSentenca);
                    exibirMensagemAviso("Tipo Cadastrado Com Sucesso!");
                    novoCadastroTipoSentenca();

                } else {

                    exibirMensagemErro("Este Tipo de Sentença Já Está Cadastrado!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Cadastrar Tipo!");
                System.out.println("Erro ao Cadastrar Tipo --> " + ex.getMessage());

            }
        } else {

            try {

                TipoSentencaDAO daoTipoSentenca = new TipoSentencaDAO();

                TipoSentenca ts;

                ts = daoTipoSentenca.getByNome(tipoSentenca);

                if (ts == null) {

                    daoTipoSentenca.alterar(tipoSentenca);
                    exibirMensagemAviso("Dado(s) Alterados com Sucesso!");
                } else {

                    exibirMensagemErro("Já Existe Um Tipo com esse NOME!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Dados!");
                System.out.println("Erro Ao Alterar Dados (TIPOSENTENÇA)! --> " + ex.getMessage());

            }
        }
        chat.adicionarMensagem(usuario.getNome() + " Inseriu ou alterou Tipo Sentença!");

    }

    public List<TipoSentenca> listaTodosTipos() {
        try {

            TipoSentencaDAO daoTipoSentenca = new TipoSentencaDAO();

            return (List<TipoSentenca>) daoTipoSentenca.getAll("nome");
        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Todos TIPOS!");
        }

        return null;

    }

    public boolean desabilitarExcluir(TipoSentenca ts) {

        try {
            SentencaDAO daoSent = new SentencaDAO();

            List<Sentenca> listadesentencas = null;

            listadesentencas = daoSent.getAllbyTipo(ts);

            if ((listadesentencas == null) || (listadesentencas.isEmpty())) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todas Sentenças do Tipo= " + ts);
            System.out.println("Erro na Busca de Todas Sentenças (desabilitar excluir TipoSentenca) --> " + ex.getMessage());

        }
        return true;

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

    public TipoSentenca getTipoSentenca() {
        return tipoSentenca;
    }

    public void setTipoSentenca(TipoSentenca tipoSentenca) {
        this.tipoSentenca = tipoSentenca;
    }
}
