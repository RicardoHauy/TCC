/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import dao.CidadeDAO;
import dao.MoraemDAO;
import dao.PessoaDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import modelo.Cidade;
import modelo.Moraem;
import modelo.Pessoa;
import modelo.Usuario;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class CidadeBean extends GenericBean {

    private Pessoa pessoa;
    private Moraem mora;

    private Cidade cidade;

    @ManagedProperty(value = "#{loginBean.usuario}")
    private Usuario usuario;

    @ManagedProperty(value = "#{notificacaoBean}")
    private NotificacaoBean chat;

    public CidadeBean() {

    }

    public void alterarCidade(Pessoa p, Moraem m) {//Passa os dados para serem listados na tela de alteração (não Utilizado)

        pessoa = p;

        mora = m;

    }

    public void excluirMoraemDaPessoa(Pessoa p, Moraem me) {//Exclui amarração de pessoa com cidade

        try {

            List<Moraem> lista;

            MoraemDAO daoMora = new MoraemDAO();

            lista = daoMora.buscarTodosPorPessoaOrdenado(p);

            if (lista.size() == 1) {

                exibirMensagemErro("Impossível Excluír --> É Proibido excluir a Única Cidade!");

            } else {
                daoMora.getSession().clear();
                daoMora.excluir(me);

                exibirMensagemAviso("Excluído Com Sucesso!");
                chat.adicionarMensagem(usuario.getNome() + " Excluiu Cidade!");
            }

        } catch (Exception ex) {

            exibirMensagemErro("Erro Ao Excluir Cidade");
            System.out.println("Erro ao excluir Moraem da pessoa (cidadeBean - ExcluirMoraem)--> " + ex.getMessage());

        }
    }

    public void novoCadastroCidades(Pessoa pessoa) {// Cria nova cidade para ser cadastrada (não Utilizado)

        this.pessoa = pessoa;

        mora = new Moraem();

        mora.setCidade(new Cidade());

        mora.setPessoa(pessoa);

    }

    public void salvarCidades() {//Salva cidade a partir de tela de cadastro  (Não utilizado)

        Cidade cidade;
        try {

            CidadeDAO cidadeDao = new CidadeDAO();

            cidadeDao = new CidadeDAO();

            cidade = cidadeDao.getByNomeAndEstado(mora.getCidade().getNomecidade(), mora.getCidade().getEstado());

            if (cidade != null) {

                mora.setCidade(cidade);

            } else {

                cidadeDao.inserir(mora.getCidade());

            }

            //      pessoa.getMoraem().add(mora);
            PessoaDAO daoPessoa = new PessoaDAO();

            daoPessoa.alterar(pessoa);

            exibirMensagemAviso("Cidade Inserida com Sucesso!");

            novoCadastroCidades(pessoa);

        } catch (Exception ex) {
            exibirMensagemErro("Erro Ao Buscar Cidade! -> " + ex.getMessage());
        }

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

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Moraem getMora() {
        return mora;
    }

    public void setMora(Moraem mora) {
        this.mora = mora;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
