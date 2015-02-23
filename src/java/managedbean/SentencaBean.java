/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.AcaoDAO;
import dao.PessoaAcaoDAO;
import dao.SentencaDAO;
import dao.TipoSentencaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Acao;
import modelo.Pessoa;
import modelo.PessoaAcao;
import modelo.Sentenca;
import modelo.TipoSentenca;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class SentencaBean extends GenericBean {

    private Sentenca sentenca;

    private List<Acao> listadetodasacoes;

    private List<TipoSentenca> listadetodostipos;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public SentencaBean() {

        sentenca = null;

    }

    public void novoCadastroSentenca() {

        sentenca = new Sentenca();

    }

    public void alterarSentenca(Sentenca s) {

        this.sentenca = s;

    }

    public void verDetalhesSentenca(Sentenca s) {

        this.sentenca = s;

    }

    public void excluirSentenca(Sentenca s) {

        try {
            SentencaDAO daoSent = new SentencaDAO();
            daoSent.getSession().clear();
            daoSent.excluir(s);

            exibirMensagemAviso("Sentença Excluída com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Sentença!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Excluir Sentença!");
            System.out.println("Erro Ao Excluir Sentença --> " + ex.getMessage());
        }

    }

    public Pessoa obterClienteAcao(Sentenca s) {

        Pessoa p = null;

        try {
            List<PessoaAcao> lista;

            PessoaAcaoDAO daoPessoaAcao = new PessoaAcaoDAO();
            lista = daoPessoaAcao.getPessoasAcao(s.getAcao());

            for (PessoaAcao pa : lista) {

                if (pa.getTipopessoa() == 1) {

                    p = pa.getPessoa();

                }

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Cliente!");
            System.out.println("Erro na busca de cliente por sentenca --> " + ex.getMessage());

        }

        return p;
    }

    public Pessoa obterReuAcao(Sentenca s) {

        Pessoa p = null;

        try {
            List<PessoaAcao> lista;

            PessoaAcaoDAO daoPessoaAcao = new PessoaAcaoDAO();
            lista = daoPessoaAcao.getPessoasAcao(s.getAcao());

            for (PessoaAcao pa : lista) {

                if (pa.getTipopessoa() == 2) {

                    p = pa.getPessoa();

                }

            }

        } catch (Exception ex) {
            Logger.getLogger(SentencaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return p;
    }

    public List<Sentenca> listaTodasSentencas() {

        List<Sentenca> listadesentencas = null;

        try {

            SentencaDAO daoSent = new SentencaDAO();
            listadesentencas = daoSent.getAll("datadasentenca");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Listar Sentenças!");
            System.out.println("Erro na Busca de Todas as Sentenças --> " + ex.getMessage());
        }

        return listadesentencas;

    }

    public void salvarSentenca() {

        if (sentenca.getCodsentenca() == null) {

            try {

                SentencaDAO daoSent = new SentencaDAO();
                daoSent.inserir(sentenca);
                exibirMensagemAviso("Sentença Inserida Com Sucesso na Ação --> " + sentenca.getAcao().getNumproc());
                novoCadastroSentenca();
                chat.adicionarMensagem(usuario.getNome() + " Adicionaou Sentença!");

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Inserir Sentença!");
                System.out.println("Erro Ao Inserir Sentenca --> " + ex.getMessage());

            }
        } else {

            try {

                SentencaDAO daoSent = new SentencaDAO();
                daoSent.alterar(sentenca);
                exibirMensagemAviso("Sentença Alterada Com Sucesso!");

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Sentença!");
                System.out.println("Erro Ao Alterar Sentenca --> " + ex.getMessage());

            }
        }
    }

    public Sentenca getSentenca() {
        return sentenca;
    }

    public void setSentenca(Sentenca sentenca) {
        this.sentenca = sentenca;
    }

    public List<Acao> getListadetodasacoes() {
        try {
            AcaoDAO daoAcao = new AcaoDAO();
            listadetodasacoes = daoAcao.getAll("codacao");
        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Todas as Acoes!");
            System.out.println("Erro na Busca de Todas as Acoes!!!! -->" + ex.getMessage());
        }

        return listadetodasacoes;
    }

    public void setListadetodasacoes(List<Acao> listadetodasacoes) {
        this.listadetodasacoes = listadetodasacoes;
    }

    public List<TipoSentenca> getListadetodostipos() {
        try {
            TipoSentencaDAO daoTipo = new TipoSentencaDAO();
            listadetodostipos = daoTipo.getAll("nome");
        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Todos os Tipos de Sentença!");
            System.out.println("Erro na Busca de Todos os Tipos de Sentença!!!! -->" + ex.getMessage());
        }

        return listadetodostipos;
    }

    public void setListadetodostipos(List<TipoSentenca> listadetodostipos) {
        this.listadetodostipos = listadetodostipos;
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
