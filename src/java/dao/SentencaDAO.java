/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.List;
import modelo.Acao;
import modelo.Sentenca;
import modelo.TipoSentenca;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class SentencaDAO extends GenericDao<Sentenca> {
    
     public List<Sentenca> getAll(String ordenardoPor) throws Exception {
        return (List<Sentenca>) getSession().createCriteria(Sentenca.class).addOrder(Order.asc(ordenardoPor)).list();
     }
     
     public List<Sentenca> getAllbyTipo(TipoSentenca ts)throws Exception{
     
         return (List<Sentenca>) getSession().createCriteria(Sentenca.class).add(Restrictions.eq("tiposentenca", ts)).list();
         
     }
     
     public void deletarTodos(Acao ac) throws Exception{
    
        List<Sentenca> sents = new ArrayList<Sentenca>();
        
        sents = (List<Sentenca>) getSession().createCriteria(Sentenca.class).add(Restrictions.eq("acao", ac)).list();
    
       // getSession().clear();
        for(Sentenca p : sents){
            
             excluir(p);
            
        }
        
    }
     
    public List<Sentenca> getAllByAcao(Acao a) throws Exception{
    
        return (List<Sentenca>) getSession().createCriteria(Sentenca.class).add(Restrictions.eq("acao", a)).list();
    
    } 
    
}
