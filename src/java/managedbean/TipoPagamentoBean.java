/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.ParcelaDAO;
import dao.TipoPagamentoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Parcela;
import modelo.TipoPagamento;
import modelo.Usuario;
import oracle.net.aso.p;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class TipoPagamentoBean extends GenericBean {

    private TipoPagamento tipoPagamento;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public TipoPagamentoBean() {

        tipoPagamento = null;

    }

    public void novoCadastroTipoPagamento() {

        tipoPagamento = new TipoPagamento();

    }

    public void alterarTipo(TipoPagamento tipo) {

        this.tipoPagamento = tipo;

    }

    public void excluirTipo(TipoPagamento tipo) {

        try {

            TipoPagamentoDAO daoTipoPagamento = new TipoPagamentoDAO();

            //      daoTipoPagamento.getSession().clear();
            daoTipoPagamento.excluir(tipo);

            exibirMensagemAviso("Exclusão Realizada Com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Tipo de Pagamento!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Excluir Tipo!");
            System.out.println("Erro ao Excluir TipoPagamento --> " + ex.getMessage());

        }

    }

    public boolean desabilitarExcluir(TipoPagamento tipo) {

        try {
            ParcelaDAO daoparc = new ParcelaDAO();

            List<Parcela> listadeparc = null;

            listadeparc = daoparc.getAllByTipo(tipo);

            if ((listadeparc == null) || (listadeparc.isEmpty())) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todas Parcelas do Tipo!");
            System.out.println("Erro na Busca de Todas as Parcelas do Tipo --> " + ex.getMessage());

        }
        return true;

    }

    public void salvarTipoPagamento() {

        if (tipoPagamento.getCodigo() == null) {

            try {

                TipoPagamentoDAO daoTipoPagamento = new TipoPagamentoDAO();

                TipoPagamento tp;

                tp = daoTipoPagamento.getByNome(tipoPagamento);

                if (tp == null) {

                    daoTipoPagamento.inserir(tipoPagamento);
                    exibirMensagemAviso("Tipo Cadastrado Com Sucesso!");
                    novoCadastroTipoPagamento();

                } else {

                    exibirMensagemErro("Este Tipo de Pagamento Já Está Cadastrado!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Cadastrar Tipo!");
                System.out.println("Erro ao Cadastrar Tipo --> " + ex.getMessage());

            }
        } else {

            try {

                TipoPagamentoDAO daoTipoPagamento = new TipoPagamentoDAO();

                TipoPagamento tp;

                tp = daoTipoPagamento.getByNome(tipoPagamento);

                if (tp == null) {

                    daoTipoPagamento.alterar(tipoPagamento);
                    exibirMensagemAviso("Dado(s) Alterados com Sucesso!");
                } else {

                    exibirMensagemErro("Já Existe Um Tipo com esse NOME!");

                }

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Dados!");
                System.out.println("Erro Ao Alterar Dados (TIPOPagamento)! --> " + ex.getMessage());

            }
        }

        chat.adicionarMensagem(usuario.getNome() + " Inseriu ou Alterou Tipo de Pagamento!");

    }

    public List<TipoPagamento> listaTodosTipos() {
        try {

            TipoPagamentoDAO daoTipoPagamento = new TipoPagamentoDAO();

            return (List<TipoPagamento>) daoTipoPagamento.getAll("nome");
        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Todos TIPOS!");
        }

        return null;

    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
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
