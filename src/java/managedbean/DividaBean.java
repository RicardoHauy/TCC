/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.DividaDAO;
import dao.ItensDividaDAO;
import dao.ParcelaDAO;
import dao.PessoaAcaoDAO;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Divida;
import modelo.ItensDivida;
import modelo.Parcela;
import modelo.Pessoa;
import modelo.PessoaAcao;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class DividaBean extends GenericBean {

    private Pessoa cliente;

    private double valoradd;

    private double valordim;

    private Divida divida;

    private ItensDivida itensDivida;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public DividaBean() {

        divida = null;

    }

    public void novoCadastroDivida() {// Cria nova dívida para ser cadastrada

        divida = new Divida();

    }

    public void novoCadastroDespesa() {

        itensDivida = new ItensDivida();

    }

    public void salvarDespesa() {

        itensDivida.setDivida(divida);

        try {

            ItensDividaDAO daoid = new ItensDividaDAO();
            DividaDAO daodiv = new DividaDAO();
            daoid.inserir(itensDivida);

            BigDecimal valorExato = new BigDecimal(itensDivida.getValor()).setScale(2, RoundingMode.HALF_UP);

            divida.adicionarValorTotal(valorExato.doubleValue());
            daodiv.alterar(divida);
            exibirMensagemAviso("Despesa Inserida com Sucesso!");
            novoCadastroDespesa();
            chat.adicionarMensagem(usuario.getNome() + " Adicionou uma Despesa!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro ao Inserir Despesa");
            System.out.println("Erro ao Inserir Despesa --> " + ex.getMessage());

        }

    }

    public List<ItensDivida> listaTodosItensDespesa(Divida d) {

        List<ItensDivida> litens = null;

        try {
            DividaDAO daodiv = new DividaDAO();
            litens = daodiv.getAllItensDivida(d);

        } catch (Exception ex) {
            exibirMensagemErro("Erro na busca dos Itens Despesa!");
            System.out.println("Erro ao Buscar ItensDívida desta divida --> " + ex.getMessage());
        }

        return litens;

    }

    public void excluirDespesa(ItensDivida item) {

        try {

            ItensDividaDAO daoid = new ItensDividaDAO();
            daoid.excluir(item);

            BigDecimal valorExato = new BigDecimal(item.getValor()).setScale(2, RoundingMode.HALF_UP);

            divida.diminuirValorTotal(valorExato.doubleValue());
            DividaDAO dao = new DividaDAO();
            dao.alterar(divida);
            exibirMensagemAviso("Item Excluido com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Item da Despesa!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro ao Excluir Item");
            System.out.println("Erro ao Excluir Item --> " + ex.getMessage());
        }

    }

    public void verDetalhesDivida(Divida d) { // Passa os valores para serem listados os detalhes da dívida

        this.divida = d;

        List<PessoaAcao> lstpesac = new ArrayList<PessoaAcao>();

        try {
            PessoaAcaoDAO daopesac = new PessoaAcaoDAO();
            lstpesac = daopesac.getPessoasAcao(d.getAcao());

            for (PessoaAcao pa : lstpesac) {

                if (pa.getTipopessoa() == 1) {

                    cliente = pa.getPessoa();

                }
            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Buscar Cliente!");
            System.out.println("Erro Ao Buscar Cliente para Listar --> " + ex.getMessage());
        }

    }

    public void excluirDivida(Divida d) {

        DividaDAO daodiv = new DividaDAO();
        try {
            daodiv.excluir(d);
            exibirMensagemAviso("Dívida Excluída com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu Dívida!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Excluir Dívida");
            System.out.println("Erro ao Excluir Dívida --> " + ex.getMessage());
        }

    }

    public boolean desabilitarExcluirDivida(Divida d) {

        try {
            ParcelaDAO daoparc = new ParcelaDAO();

            List<Parcela> parcs = daoparc.getAllByDivida(d);

            if (parcs == null) {

                return false;

            }

            DividaDAO daodiv = new DividaDAO();

            List<ItensDivida> itdivs = daodiv.getAllItensDivida(d);

            if (itdivs == null) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Desabilitar Botão Excluir");
            System.out.println("Erro na Busca --> " + ex.getMessage());
        }

        return true;

    }

    public List<Divida> listaTodasDividas() {//Busca todas dívidas ordenadas por código para listá-las no datatable principal

        try {
            DividaDAO daoDivida = new DividaDAO();
            return (List<Divida>) daoDivida.getAll("coddivida");
        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Listar Todas Dívidas!");
            System.out.println("Erro na Busca de Todas as Dívidas!!!! -->" + ex.getMessage());
        }

        return null;
    }

    public ItensDivida getItensDivida() {
        return itensDivida;
    }

    public void setItensDivida(ItensDivida itensDivida) {
        this.itensDivida = itensDivida;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
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

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public double getValoradd() {
        return valoradd;
    }

    public void setValoradd(double valoradd) {
        this.valoradd = valoradd;
    }

    public double getValordim() {
        return valordim;
    }

    public void setValordim(double valordim) {
        this.valordim = valordim;
    }

}
