/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.AcaoDAO;
import dao.CidadeDAO;
import dao.MoraemDAO;
import dao.PessoaAcaoDAO;
import dao.PessoaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import lazy.PessoaLazy;
import modelo.Cidade;
import modelo.Moraem;
import modelo.Pessoa;
import modelo.PessoaAcao;
import modelo.Usuario;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class PessoaBean extends GenericBean {

    private Pessoa pessoa;

    private LazyDataModel<Pessoa> pessoas;

    private Cidade city;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public PessoaBean() {
        pessoa = null;
        pessoas = new PessoaLazy();
    }

    public void novoCadastro() {//Cria nova pessoa e nova Cidade para serem cadastradas

        pessoa = new Pessoa();
        city = new Cidade();
        pessoa.setUsuario(usuario); //Atribui o usuario que está logado para saber quem cadastrou a pessoa
        pessoa.setFisjur(1);//Inicializa como pessoa Física (1)

    }

    public void atualizarPessoa() {
        try {
            pessoa = new PessoaDAO().getById(pessoa.getCodpessoa());
            verDetalhes(pessoa);
            System.out.println("NOOOmmee da Pessoa = " + pessoa.getNome());
        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Buscar Pessoa");
            System.out.println("Erro ao Buscar Pessoa --> " + ex.getMessage());
        }

    }

    public Moraem ultimoEndereco() {// Busca o ultimo endereco que a pessoa morou

        if (pessoa == null || pessoa.getCodpessoa() == null) {

            return null;

        }

        try {
            MoraemDAO moraemDao = new MoraemDAO();
            return moraemDao.buscarUltimo(pessoa);
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Ultimo Moraem -->" + ex.getMessage());
            System.out.println("Erro Ao Buscar Ultimo Moraem -->" + ex.getMessage());
        }
        return null;

    }

    public List<Moraem> buscarTodasCidades() {

        try {
            MoraemDAO moraemDao = new MoraemDAO();
            return moraemDao.buscarTodos(pessoa);
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todos Moraem --> " + ex.getMessage());
            System.out.println("Erro Ao Buscar Todos Moraem --> " + ex.getMessage());
        }

        return null;

    }

    public void verDetalhes(Pessoa pessoa) {//Passa os valores para serem listados na tela de ver detalhes do botão do datatable pessoas

        this.pessoa = pessoa;

        try {
            Moraem moraem = ultimoEndereco();
            if (moraem != null) {
                city = moraem.getCidade();
            }
        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Ultimo Moraem --> " + ex.getMessage());
            System.out.println("Erro Ao Buscar Ultimo Moraem --> " + ex.getMessage());
        }

    }

    public List<PessoaAcao> listarAcoesPessoa(Pessoa p) {//lista acoes por pessoa para a tela do botão minhas acoes do ver detalhes pessoa

        List<PessoaAcao> lstpa = new ArrayList<PessoaAcao>();

        try {

            PessoaAcaoDAO daopa = new PessoaAcaoDAO();
            lstpa = daopa.getPessoasAcaoByPessoa(p);

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Ação");
            System.out.println("Erro na busca de PessoaAcao para litar acoes por pessoa --> " + ex.getMessage());
        }

        return lstpa;

    }

    public void excluirPessoa(Pessoa p) {

        try {

            PessoaDAO daoPessoa = new PessoaDAO();
            daoPessoa.excluirPessoa(p);
            exibirMensagemAviso("Excluído com Sucesso!");
            chat.adicionarMensagem(usuario.getNome() + " Excluiu uma Pessoa!");

        } catch (Exception ex) {
            exibirMensagemErro("Erro na Exclusão!");
            System.out.println("Erro ao Excluir Pessoa --> " + ex.getMessage());
        }

    }

    public void alterarPessoa(Pessoa pessoa) {//passa os dados para serem mostrados na tela onde se pode alterar

        this.pessoa = pessoa;

        try {
            Moraem moraem = ultimoEndereco();
            if (moraem != null) {
                city = moraem.getCidade();
            }
        } catch (Exception ex) {
            System.out.println("Erro Ao Buscar Moraem --> " + ex.getMessage());
            exibirMensagemErro("Erro Ao Buscar Moraem");
        }

    }

    public boolean desabilitarExcluir(Pessoa p) {//desabilita/habilita botão excluir do datatable pessoas

        try {
            AcaoDAO daoAcao = new AcaoDAO();

            List<PessoaAcao> listadepessoaacao = null;

            listadepessoaacao = daoAcao.getAllByPessoa(p);

            if ((listadepessoaacao == null) || (listadepessoaacao.isEmpty())) {

                return false;

            }

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Todas Ações da Pessoa!");
            System.out.println("Erro na Busca de Todas as Pessoas (desabilitar excluir pessoa) --> " + ex.getMessage());

        }
        return true;

    }

    public void salvarPessoa() {//Insere/altera pessoa e cidade da pessoa

        Cidade cidade;

        try {

            CidadeDAO cidadeDao = new CidadeDAO();

            cidade = cidadeDao.getByNomeAndEstado(city.getNomecidade(), city.getEstado());

            if (cidade == null) {

                cidadeDao.inserir(city);
                city = cidadeDao.getByNomeAndEstado(city.getNomecidade(), city.getEstado());

                cidadeDao.getSession().flush();

            } else {

                city = cidade;

            }

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Salvar Pessoa!");

            System.out.println("Erro Ao Buscar Cidade! --> " + ex.getMessage());

            return;
        }

        if (pessoa.getCodpessoa() == null) { //Inserir

            try {

                Moraem mora = new Moraem();

                mora.setCidade(city);

                mora.setPessoa(pessoa);

                PessoaDAO daoPessoa = new PessoaDAO();

                daoPessoa.inserir(pessoa);

                MoraemDAO daoMora = new MoraemDAO();

                daoMora.inserir(mora);

                exibirMensagemAviso("Pessoa Cadastrada Com Sucesso!");
                novoCadastro();

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Cadastrar Pessoa!");

                System.out.println("Erro Ao Cadastrar Pessoa! -> " + ex.getMessage());

            }

        } else {//---------------ALTERAR----------------------

            try {
                PessoaDAO daoPessoa = new PessoaDAO();
                Moraem moraEm = ultimoEndereco();

                daoPessoa.alterar(pessoa);

                if (!moraEm.getCidade().equals(city)) {

                    Moraem mora = new Moraem();
                    mora.setPessoa(pessoa);
                    mora.setCidade(city);
                    MoraemDAO daoMora = new MoraemDAO();

                    daoMora.inserir(mora);

                }
                exibirMensagemAviso("Dados Alterados com Sucesso!");

            } catch (Exception ex) {

                exibirMensagemErro("Erro Ao Alterar Dados");
                System.out.println("Erro Ao Alterar Dados! -> " + ex.getMessage());

            }
        }

        chat.adicionarMensagem(usuario.getNome() + " Inseriu ou Alterou Pessoa!");
    }

    public List<Pessoa> buscaTodasPessoas() {

        List<Pessoa> pes = new ArrayList<Pessoa>();
        PessoaDAO dao = new PessoaDAO();

        try {

            dao.getSession().flush();
            pes = dao.getAll("nome");
            return pes;

        } catch (Exception ex) {
            System.out.println("Erro ao Buscar todas as pessoas com get all ordenado por --> " + ex.getMessage());
            exibirMensagemErro("Erro Ao Listar Pessoas");
        }

        return null;

    }

    public LazyDataModel<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(LazyDataModel<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cidade getCity() {
        return city;
    }

    public void setCity(Cidade city) {
        this.city = city;
    }

    public NotificacaoBean getChat() {
        return chat;
    }

    public void setChat(NotificacaoBean chat) {
        this.chat = chat;
    }

}
