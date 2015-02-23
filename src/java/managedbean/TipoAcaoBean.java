/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.AcaoDAO;
import dao.TipoAcaoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Acao;
import modelo.TipoAcao;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class TipoAcaoBean extends GenericBean {

    private TipoAcao tipoAcao;

    @ManagedProperty(value = "#{loginBean}")
    private LoginBean loginBean;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean notBean;

    public TipoAcaoBean() {

        tipoAcao = null;

    }

    public void novoCadastroTipoAcao() {

        tipoAcao = new TipoAcao();

    }

    public void alterarTipo(TipoAcao tipo) {

        this.tipoAcao = tipo;

    }

    public void excluirTipo(TipoAcao tipo) {

        try {

            TipoAcaoDAO daoTipoAcao = new TipoAcaoDAO();

            daoTipoAcao.getSession().clear();
            daoTipoAcao.excluir(tipo);

            exibirMensagemAviso("Exclusão Realizada Com Sucesso!");
            notBean.adicionarMensagem(loginBean.getUsuario().getNome() + " Excluiu Tipo de Ação!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Excluir Tipo!");
            System.out.println("Erro ao Excluir TipoAção --> " + ex.getMessage());

        }

    }

    public void salvarTipoAcao() {

        if (tipoAcao.getCodigo() == null) {

            try {

                TipoAcaoDAO daoTipoAcao = new TipoAcaoDAO();

                TipoAcao ta;

                ta = daoTipoAcao.getByNome(tipoAcao);

                if (ta == null) {

                    daoTipoAcao.inserir(tipoAcao);
                    exibirMensagemAviso("Tipo Cadastrado Com Sucesso!");
                    novoCadastroTipoAcao();

                } else {

                    exibirMensagemErro("Este Tipo de Ação Já Está Cadastrado!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Cadastrar Tipo!");
                System.out.println("Erro ao Cadastrar Tipo --> " + ex.getMessage());

            }
        } else {

            try {

                TipoAcaoDAO daoTipoAcao = new TipoAcaoDAO();

                TipoAcao ta;

                ta = daoTipoAcao.getByNome(tipoAcao);

                if (ta == null) {

                    daoTipoAcao.alterar(tipoAcao);
                    exibirMensagemAviso("Dado(s) Alterados com Sucesso!");
                } else {

                    exibirMensagemErro("Já Existe Um Tipo com esse NOME!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Dados!");
                System.out.println("Erro Ao Alterar Dados (TIPOACAO)! --> " + ex.getMessage());

            }
        }
        notBean.adicionarMensagem(loginBean.getUsuario().getNome() + " Inseriu ou Alterou Tipo de Ação!");

    }

    public List<TipoAcao> listaTodosTipos() {
        try {

            TipoAcaoDAO daoTipoAcao = new TipoAcaoDAO();

            return (List<TipoAcao>) daoTipoAcao.getAll("nome");
        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Todos TIPOS!");
        }

        return null;

    }

    public boolean desabilitarExcluir(TipoAcao ta) {

        try {
            AcaoDAO daoAcao = new AcaoDAO();

            List<Acao> listadeacoes = null;

            listadeacoes = daoAcao.getAllbyTipo(ta);

            if ((listadeacoes == null) || (listadeacoes.isEmpty())) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todas Ações do Tipo= " + ta);
            System.out.println("Erro na Busca de Todas Ações (desabilitar excluir TipoAcao) --> " + ex.getMessage());

        }
        return true;

    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public TipoAcao getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(TipoAcao tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public NotificacaoBean getNotBean() {
        return notBean;
    }

    public void setNotBean(NotificacaoBean notBean) {
        this.notBean = notBean;
    }

}
