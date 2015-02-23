/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;

import modelo.TipoAcao;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class TipoAcaoDAO extends GenericDao<TipoAcao> {
    
    public TipoAcao getById(Integer id) throws Exception {
        return (TipoAcao) getSession().get(TipoAcao.class, id);
    }

    public List<TipoAcao> getAll(String ordenardoPor) throws Exception {
        return (List<TipoAcao>) getSession().createCriteria(TipoAcao.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public TipoAcao getByNome(TipoAcao ta) throws Exception{
    
        return (TipoAcao) getSession().createCriteria(TipoAcao.class).add(Restrictions.eq("nome", ta.getNome())).uniqueResult();  
        
    }   
    
}
