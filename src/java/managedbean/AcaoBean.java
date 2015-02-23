/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.AcaoDAO;
import dao.DividaDAO;
import dao.HistoricoAcaoDAO;
import dao.PessoaAcaoDAO;
import dao.PessoaDAO;
import dao.SentencaDAO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Acao;
import modelo.Divida;
import modelo.HistoricoAcao;
import modelo.Pessoa;
import modelo.PessoaAcao;
import modelo.Sentenca;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class AcaoBean extends GenericBean {

    private Acao acao;

    private Sentenca sentenca;

  //  private List<Acao> listadetodasacoes;
    private HistoricoAcao histAcao;

    private List<Pessoa> listadetodaspessoas;

    private Pessoa cliente;

    private Pessoa reu;

    private Pessoa interessado;

    private List<Pessoa> listadeinteressados;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public AcaoBean() {

        acao = null;

    }

    public void novoCadastroAcao() { // Cria nova Ação para ser cadastrada

        acao = new Acao();
        cliente = null;
        reu = null;
        interessado = null;

        listadeinteressados = new ArrayList<Pessoa>();

    }

    public void novoCadastroAndamentoAcao() {

        histAcao = new HistoricoAcao();

    }

    public void verDescSentenca(Sentenca s) {//PAssa os dados da sentença para listar sua descrição

        this.sentenca = s;

    }

    public void verDetalhesAcao(Acao acao) {//Passa os dados da ação para ser listado os detalhes

        this.acao = acao;

        try {
            listadeinteressados = new ArrayList<Pessoa>();
            PessoaAcaoDAO daoPesAc = new PessoaAcaoDAO();
            List<PessoaAcao> listadepessoaacao;

            listadepessoaacao = daoPesAc.getPessoasAcao(acao);

            for (PessoaAcao p : listadepessoaacao) {
                if (p.getTipopessoa() == 1) {
                    cliente = p.getPessoa();
                } else if (p.getTipopessoa() == 2) {
                    reu = p.getPessoa();
                } else if (p.getTipopessoa() == 3) {
                    listadeinteressados.add(p.getPessoa());
                }
            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Exibir Detalhes!");
            System.out.println("Erro Ao Exibir Detalhes da Ação --> " + ex.getMessage());
        }

    }

    public void excluirAcao(Acao ac) {//Exclui a Ação passada por parâmetro

        try {
            AcaoDAO daoAcao = new AcaoDAO();
            //      daoAcao.getSession().clear();
            daoAcao.excluirAcao(ac);
            exibirMensagemAviso("Ação Excluída com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu uma Ação!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Exclusão!");
            System.out.println("Erro Ao Excluir Ação --> " + ex.getMessage());
        }

    }

    public List<Sentenca> listaSentencasAcao() {//Busca todas sentenças por acao para listá-las na Tela Que Exibe Detalhes da Sentenca na ação

        List<Sentenca> ls = null;

        try {

            SentencaDAO daosent = new SentencaDAO();
            ls = daosent.getAllByAcao(acao);

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca da Sentença da Ação");
            System.out.println("Erro na Busca da Sentença --> " + ex.getMessage());
        }

        return ls;

    }

    public boolean naoTemSentenca() {// Método utilizado para desabilitar/habilitar o botão ver sentença da acao em detalhes da acao

        List<Sentenca> ls = null;

        try {
            SentencaDAO daosent = new SentencaDAO();
            ls = daosent.getAllByAcao(acao);

            if ((ls == null) || (ls.isEmpty())) {

                return true;
            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Busca da Sentença da Ação");
            System.out.println("Erro na Busca da Sentença --> " + ex.getMessage());
        }

        return false;
    }

    public boolean desabilitarExcluirAcao(Acao ac) {

        try {

            Divida div = null;

            DividaDAO daodiv = new DividaDAO();

            div = daodiv.getByAcao(ac);

            if ((div == null)) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todas Dívidas da Ação!");
            System.out.println("Erro na Busca de Todas as Dívidas da Ação --> " + ex.getMessage());

        }
        return true;

    }

    public void adicionarInteressadoLista() {//Adiciona interessados no data table interessados do cadastro de acoes

        if (interessado == null) {

            exibirMensagemErro("Por Favor Selecione um Interessado Para Adicionar à Ação");

            return;
        }

        if (listadeinteressados.contains(interessado)) {

            exibirMensagemErro("Esta Pessoa Já está adicionada na Lista! Por Favor escolha outra");

            return;

        }

        listadeinteressados.add(interessado);
        exibirMensagemAviso("Inserido Com Sucesso!");

        interessado = new Pessoa();
    }

    public void excluirInteressadoLista(Pessoa i) {//Exclui interessados do data table interessados do cadastro de acoes

        try {
            listadeinteressados.remove(i);

            exibirMensagemAviso("Interessado Excluído da Lista!");

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Excluir Interessado da Lista");
            System.out.println("Erro Ao Excluir Interessado da Lista --> " + ex.getMessage());

        }

    }

    public boolean validarDados() {//Valida dados do cadastro para serem salvos

        if ((acao.getDataaentrar() != null && acao.getDatadist() != null) && (acao.getDataaentrar().getTime() > acao.getDatadist().getTime())) {

            exibirMensagemErro("Erro - A DATA a dar Entrada deve ser ANTERIOR à DATA da Distribuição da Ação!");

            return false;
        }

        if (acao.getValorcombinado() > acao.getValordaacao()) {

            exibirMensagemErro("Erro - O Valor Combinado a Receber não pode ser MAIOR que o Valor da Ação!");
            return false;

        }

        if ((acao.getDataaentrar() != null && acao.getDataproxalteracaoacao() != null) && (acao.getDataaentrar().getTime() > acao.getDataproxalteracaoacao().getTime())) {

            exibirMensagemErro("Erro - A DATA da Proxima Alteração na Ação deve ser POSTERIOR à DATA a Dar Entrada!");

            return false;
        }

        if ((acao.getDatadist() != null && acao.getDataproxalteracaoacao() != null) && (acao.getDatadist().getTime() > acao.getDataproxalteracaoacao().getTime())) {

            exibirMensagemErro("Erro - A DATA da Próxima Alteração deve ser POSTERIOR à data da Distribuição!");

            return false;

        }

        if (cliente.equals(reu)) {

            exibirMensagemErro("Erro - Cliente e Réu devem ser diferentes!");

            return false;
        }

        if ((listadeinteressados.contains(cliente)) || (listadeinteressados.contains(reu))) {

            exibirMensagemErro("Erro - Cliente ou Réu não podem ser Interessados\nREMOVA-O(S) DA LISTA!");

            return false;

        }

        return true;
    }

    public void excluirHistoricoAcao(HistoricoAcao hist) {

        try {
            HistoricoAcaoDAO daohist = new HistoricoAcaoDAO();
            daohist.excluir(hist);
            exibirMensagemAviso("Ocorrência Excluida com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu dados do Andamento da Ação!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Excluir do Histórico!");
            System.out.println("Erro Ao Excluir do Histórico --> " + ex.getMessage());
        }

    }

    public void alterarAcao(Acao acao) {// Mostra os dados da ação a ser alterada na tela de alteração

        this.acao = acao;

        listadeinteressados = new ArrayList<Pessoa>();
        List<PessoaAcao> listadepessoaacao;
        PessoaAcaoDAO daoPessoaAcao = new PessoaAcaoDAO();

        try {
            listadepessoaacao = daoPessoaAcao.getPessoasAcao(acao);

            for (PessoaAcao pesAc : listadepessoaacao) {

                if (pesAc.getTipopessoa() == 1) {
                    cliente = pesAc.getPessoa();
                } else if (pesAc.getTipopessoa() == 2) {
                    reu = pesAc.getPessoa();
                } else if (pesAc.getTipopessoa() == 3) {
                    listadeinteressados.add(pesAc.getPessoa());
                }
            }
        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Exibir Ação!");
            System.out.println("Erro ao Exibir Ação --> " + ex.getMessage());
        }

    }

    public Pessoa obterClienteListarNome(Acao ac) {//obtém o cliente da ação para listar o nome dele no data table de acoes

        Pessoa cli = null;
        PessoaAcaoDAO daoPessoaAcao = new PessoaAcaoDAO();
        try {
            List<PessoaAcao> lista = daoPessoaAcao.getPessoasAcao(ac);

            for (PessoaAcao p : lista) {

                if (p.getTipopessoa() == 1) {

                    cli = p.getPessoa();

                }

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Buscar Cliente!");
            System.out.println("Erro Ao Buscar Todas PessoaAcao para listar Cliente --> " + ex.getMessage());
        }

        return cli;

    }

    public void salvarAcao() {// Grava no banco / Altera Ação Gravada no Banco

        if (!validarDados()) {

            return;

        }

        if (acao.getCodacao() == null) {//inserir
            try {

                PessoaAcaoDAO daoPesAc = new PessoaAcaoDAO();

                PessoaAcao pessoaacao = new PessoaAcao();

                AcaoDAO daoAcao = new AcaoDAO();

                daoAcao.inserir(acao);

                Divida divida = new Divida();

                divida.setAcao(acao);

                divida.setDatadageracao(new Date());

                divida.setValortotal(acao.getValorcombinado());

                DividaDAO daoDiv = new DividaDAO();

                daoDiv.inserir(divida);

                //processando cliente
                pessoaacao.setPessoa(cliente);
                pessoaacao.setTipopessoa(1);
                pessoaacao.setAcao(acao);
                daoPesAc.inserir(pessoaacao);
                
                if(reu!=null){
                //processando o Reu
                pessoaacao = new PessoaAcao();
                pessoaacao.setPessoa(reu);
                pessoaacao.setTipopessoa(2);
                pessoaacao.setAcao(acao);
                daoPesAc.inserir(pessoaacao);
                }
//processando os interessados
                for (int i = 0; i < listadeinteressados.size(); i++) {
                    pessoaacao = new PessoaAcao();
                    pessoaacao.setPessoa(listadeinteressados.get(i));
                    pessoaacao.setTipopessoa(3);
                    pessoaacao.setAcao(acao);

                    daoPesAc.inserir(pessoaacao);
                }

                exibirMensagemAviso("Ação Cadastrada com Sucesso!");
                novoCadastroAcao();

            } catch (Exception ex) {

                //  novoCadastroAcao();
                exibirMensagemErro("Erro Ao Inserir Ação!");
                System.out.println("Erro no cadastro de acoes --> " + ex.getMessage());

            }

        } else {// Alterar

            try {

                PessoaAcaoDAO pesAcDAO = new PessoaAcaoDAO();

                pesAcDAO.deletarTodos(acao);

                PessoaAcao pessoaacao = new PessoaAcao();

                //processando cliente
                pessoaacao.setPessoa(cliente);
                pessoaacao.setTipopessoa(1);
                pessoaacao.setAcao(acao);

                pesAcDAO.inserir(pessoaacao);
      //          acao.getListadepessoas().add(pessoaacao);

                if(reu!=null){
                //processando o Reu
                pessoaacao = new PessoaAcao();

                pessoaacao.setPessoa(reu);
                pessoaacao.setTipopessoa(2);
                pessoaacao.setAcao(acao);

                pesAcDAO.inserir(pessoaacao);
                }
      //          acao.getListadepessoas().add(pessoaacao);
                //processando os interessados
                for (int i = 0; i < listadeinteressados.size(); i++) {

                    pessoaacao = new PessoaAcao();

                    pessoaacao.setPessoa(listadeinteressados.get(i));
                    pessoaacao.setTipopessoa(3);
                    pessoaacao.setAcao(acao);

                    pesAcDAO.inserir(pessoaacao);

                }

                AcaoDAO daoAcao = new AcaoDAO();

                daoAcao.alterar(acao);

                exibirMensagemAviso("Ação Alterada com Sucesso!");

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Ação!");
                System.out.println("Erro Ao Alterar Ação --> " + ex.getMessage());

            }

        }

        chat.adicionarMensagem(usuario.getNome() + " Inseriu ou Alterou Ação!");

    }

    public void salvarHistoricoAcao() {

        try {
            histAcao.setAcao(acao);
            HistoricoAcaoDAO daohist = new HistoricoAcaoDAO();
            daohist.inserir(histAcao);
            exibirMensagemAviso("Ocorrência Inserida com Sucesso!");
            novoCadastroAndamentoAcao();
            chat.adicionarMensagem(usuario.getNome() + " Inseriu dados do Andamento da Ação!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Salvar Ocorrencia!");
            System.out.println("Erro ao Salvar HistóricoAcao --> " + ex.getMessage());
        }
    }

    public Pessoa getInteressado() {
        return interessado;
    }

    public void setInteressado(Pessoa interessado) {
        this.interessado = interessado;
    }

    public Pessoa getCliente() {

        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public Pessoa getReu() {
        return reu;
    }

    public void setReu(Pessoa reu) {
        this.reu = reu;
    }

    public List<Pessoa> getListadetodaspessoas() {

        try {
            PessoaDAO daoPessoa = new PessoaDAO();
            listadetodaspessoas = daoPessoa.getAll("nome");
        } catch (Exception ex) {
            System.out.println("Erro na Busca de Todas as Pessoas!!!! -->" + ex.getMessage());
        }

        return listadetodaspessoas;
    }

    public void setListadetodaspessoas(List<Pessoa> listadetodaspessoas) {
        this.listadetodaspessoas = listadetodaspessoas;
    }

    public List<Pessoa> getListadeinteressados() {
        return listadeinteressados;
    }

    public void setListadeinteressados(List<Pessoa> listadeinteressados) {
        this.listadeinteressados = listadeinteressados;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public List<Acao> listaTodasAcoes() {

        try {
            AcaoDAO daoAcao = new AcaoDAO();
            return (List<Acao>) daoAcao.getAll("codacao");
        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Buscar Todas Ações!");
            System.out.println("Erro na Busca de Todas as Ações!!!! -->" + ex.getMessage());
        }

        return null;
    }

    public List<HistoricoAcao> listaTodosHistoricosAcoes() {

        try {
            AcaoDAO daoacao = new AcaoDAO();
            return (List<HistoricoAcao>) daoacao.getAllHistoricoAcao(acao);
        } catch (Exception ex) {
            exibirMensagemErro("Erro na busca do Histórico desta Ação!");
            System.out.println("Erro na Busca do HistAc --> " + ex.getMessage());
        }
        return null;
    }

    public Sentenca getSentenca() {
        return sentenca;
    }

    public void setSentenca(Sentenca sentenca) {
        this.sentenca = sentenca;
    }

    public HistoricoAcao getHistAcao() {
        return histAcao;
    }

    public void setHistAcao(HistoricoAcao histAcao) {
        this.histAcao = histAcao;
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
