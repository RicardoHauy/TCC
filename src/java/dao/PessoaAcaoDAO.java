/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Acao;
import modelo.Pessoa;
import modelo.PessoaAcao;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class PessoaAcaoDAO extends GenericDao<PessoaAcao>{
    
    public void deletarTodos(Acao ac) throws Exception{
    
        List<PessoaAcao> pesAc = new ArrayList<PessoaAcao>();
        
        pesAc = (List<PessoaAcao>) getSession().createCriteria(PessoaAcao.class).add(Restrictions.eq("acao", ac)).list();
    
   //     getSession().clear();
        for(PessoaAcao p : pesAc){
            
             excluir(p);
            
        }
        
    }
    
    public List<PessoaAcao> getPessoasAcao(Acao ac) throws Exception{
    
        return getSession().createCriteria(PessoaAcao.class).add(Restrictions.eq("acao", ac)).list();
    
    }
    
    public List<PessoaAcao> getPessoasAcaoByPessoa(Pessoa p) throws Exception{
    
        return getSession().createCriteria(PessoaAcao.class).add(Restrictions.eq("pessoa", p)).list();
    
    }
    
}
