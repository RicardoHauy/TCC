/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.TipoSentenca;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class TipoSentencaDAO extends GenericDao<TipoSentenca>{
    
    public TipoSentenca getById(Integer id) throws Exception {
        return (TipoSentenca) getSession().get(TipoSentenca.class, id);
    }

    public List<TipoSentenca> getAll(String ordenardoPor) throws Exception {
        return (List<TipoSentenca>) getSession().createCriteria(TipoSentenca.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public TipoSentenca getByNome(TipoSentenca ts) throws Exception{
    
        return (TipoSentenca) getSession().createCriteria(TipoSentenca.class).add(Restrictions.eq("nome", ts.getNome())).uniqueResult();  
        
    }
    
    
}
