/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Acao;
import modelo.HistoricoAcao;
import modelo.Pessoa;
import modelo.PessoaAcao;
import modelo.TipoAcao;
import oracle.net.aso.p;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class AcaoDAO extends GenericDao<Acao>{
    
    
    public Acao getById(Integer id) throws Exception {
        return (Acao) getSession().get(Acao.class, id);
    }

    public List<Acao> getAll(String ordenardoPor) throws Exception {
        return (List<Acao>) getSession().createCriteria(Acao.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
     public Acao getByNumproc(String numproc) throws Exception {
        return (Acao) getSession().get(Acao.class, numproc);
    }
     
    public List<PessoaAcao> getAllByPessoa(Pessoa p) throws Exception{
    
       return (List<PessoaAcao>) getSession().createCriteria(PessoaAcao.class).add(Restrictions.eq("pessoa", p)).list();
        
    } 
    
    public List<Acao> getAllbyTipo(TipoAcao ta) throws Exception{
    
        return (List<Acao>) getSession().createCriteria(Acao.class).add(Restrictions.eq("tipoacao", ta)).list();
        
    }
   
    public void excluirAcao(Acao a) throws Exception{

        PessoaAcaoDAO daoPesAc = new PessoaAcaoDAO();
        
        SentencaDAO daoSent = new SentencaDAO();
        
        
        
        daoPesAc.deletarTodos(a);
        
        daoSent.deletarTodos(a);

 
        //getSession().clear();
        excluir(a);
    }
    
   public List<HistoricoAcao> getAllHistoricoAcao(Acao ac) throws Exception{
   
       return getSession().createCriteria(HistoricoAcao.class).add(Restrictions.eq("acao", ac)).addOrder(Order.asc("datahist")).list();
   
   }
}
