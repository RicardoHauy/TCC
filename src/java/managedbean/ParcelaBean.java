/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.ParcelaDAO;
import dao.TipoPagamentoDAO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Divida;
import modelo.Parcela;
import modelo.TipoPagamento;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class ParcelaBean extends GenericBean {

    private Parcela parcela;

    private Divida divida;

    private double valorMaximoParcela;

    private List<TipoPagamento> listadetodostipospagamento;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public ParcelaBean() {

        parcela = null;

    }

    public boolean desabilitarPagamento(Parcela par) {//Desabilita/habilita o botão Pagar do data table de parcelas

        if (par.getDataquepagou() != null) {

            return true;

        }

        return false;

    }

    public boolean desabilitarCriarParcela(Divida d) {//desabilita/habilita o botão criar parcela da página de parcelamento

        double somadosvaloresdasparcelas = 0;
        try {

            ParcelaDAO daoparc = new ParcelaDAO();
            List<Parcela> lista = new ArrayList<Parcela>();

            lista = daoparc.getAllByDivida(d);

            for (Parcela p : lista) {

                somadosvaloresdasparcelas += p.getValorparcela();

            }

            if (somadosvaloresdasparcelas >= d.getValortotal()) {

                return true;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Parcelas por Dívida!");
            System.out.println("Erro na Busca de Parcelas Por dívida --> " + ex.getMessage());
        }

        return false;

    }

    public void novoCadastroParcela(Divida d) {//cria nova parcela para ser cadastrada

        double valortotparcelas = 0;

        this.divida = d;
        parcela = new Parcela();
        try {

            ParcelaDAO daoparc = new ParcelaDAO();
            List<Parcela> lista = new ArrayList<Parcela>();

            lista = daoparc.getAllByDivida(d);

            for (Parcela p : lista) {

                valortotparcelas += p.getValorparcela();

            }

            parcela.setValorparcela(d.getValortotal() - valortotparcelas);

            valorMaximoParcela = parcela.getValorparcela();

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca de Parcelas por Dívida!");
            System.out.println("Erro na Busca de Parcelas Por dívida --> " + ex.getMessage());
        }
    }

    public double calcularValorTotalParcelado(Divida div) {

        double vlrTotalParcelado = 0;
        try {

            ParcelaDAO daoparc = new ParcelaDAO();
            List<Parcela> parcelas;

            parcelas = daoparc.calcValorTotalParcelado(div);

            for (Parcela p : parcelas) {

                vlrTotalParcelado = vlrTotalParcelado + p.getValorparcela();

            }
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Calcular Valor Total Parcelado!");
            System.out.println("Erro Ao Calcular Valor Total Parcelado --> " + ex.getMessage());
        }

        return vlrTotalParcelado;

    }

    public void pagar(Parcela p) {

        this.parcela = p;

    }

    public void excluirParcela(Parcela p) {

        try {

            ParcelaDAO daoparc = new ParcelaDAO();
            daoparc.excluir(p);
            exibirMensagemAviso("Parcela Excluída Com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Parcela!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Excluir Parcela!");
            System.out.println("Erro Ao Excluir Parcela! --> " + ex.getMessage());
        }

    }

    public void inserirPagamento() {

        ParcelaDAO daoparc = new ParcelaDAO();
        try {
            daoparc.alterar(parcela);
            exibirMensagemAviso("Parcela Paga Com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Pagou Parcela!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Pagar Parcela");
            System.out.println("Erro ao Pagar Parcela --> " + ex.getMessage());

        }

    }

    public void salvarParcela() {

        try {

            BigDecimal valorcorreto = new BigDecimal(valorMaximoParcela);
            valorcorreto = valorcorreto.setScale(2, BigDecimal.ROUND_HALF_UP);

            if (parcela.getValorparcela() > valorcorreto.doubleValue()) {

                exibirMensagemErro("Erro - O Valor Máximo da Parcela deve ser igual a R$ " + valorcorreto.doubleValue());
                return;

            }

            parcela.setDivida(divida);
            ParcelaDAO daoparc = new ParcelaDAO();
            daoparc.inserir(parcela);
            exibirMensagemAviso("Parcela Criada Com Sucesso!");

            novoCadastroParcela(divida);
            chat.adicionarMensagem(usuario.getNome() + " Adicionou Parcela!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Salvar Parcela!");
            System.out.println("Erro ao Salvar Parcela --> " + ex.getMessage());

        }

    }

    public double calcularValorParcelasPagas(Divida div) {

        double vlrParcelasPagas = 0;

        try {

            ParcelaDAO daoparc = new ParcelaDAO();
            List<Parcela> parcelas;

            parcelas = daoparc.calcValorTotalParcelado(div);

            for (Parcela p : parcelas) {

                if (p.getDataquepagou() != null) {
                    vlrParcelasPagas = vlrParcelasPagas + p.getValorparcela();
                }

            }
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Calcular Valor de Parcelas Pagas!");
            System.out.println("Erro Ao Calcular Valor de Parcelas Pagas --> " + ex.getMessage());
        }

        return vlrParcelasPagas;

    }

    public List<Parcela> listaTodasParcelasDivida(Divida d) {

        this.divida = d;
        try {
            ParcelaDAO daoparc = new ParcelaDAO();
            return daoparc.getAllByDivida(divida);
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Parcelas No Banco");
            System.out.println("Erro ao Buscar Todas Parcelas --> " + ex.getMessage());
        }

        return null;
    }

    public Parcela getParcela() {
        return parcela;
    }

    public void setParcela(Parcela parcela) {
        this.parcela = parcela;
    }

    public List<TipoPagamento> getListadetodostipospagamento() {

        try {
            TipoPagamentoDAO tpd = new TipoPagamentoDAO();
            listadetodostipospagamento = tpd.getAll("nome");
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao listar Todos Tipos de Pagamento");
            System.out.println("Erro Ao buscar Tipos de Pagamento --> " + ex.getMessage());
        }

        return listadetodostipospagamento;
    }

    public void setListadetodostipospagamento(List<TipoPagamento> listadetodostipospagamento) {
        this.listadetodostipospagamento = listadetodostipospagamento;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    public double getValorMaximoParcela() {
        return valorMaximoParcela;
    }

    public void setValorMaximoParcela(double valorMaximoParcela) {
        this.valorMaximoParcela = valorMaximoParcela;
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
