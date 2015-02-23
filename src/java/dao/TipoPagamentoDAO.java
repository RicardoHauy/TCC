/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.TipoPagamento;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class TipoPagamentoDAO extends GenericDao<TipoPagamento> {
    
    public TipoPagamento getById(Integer id) throws Exception {
        return (TipoPagamento) getSession().get(TipoPagamento.class, id);
    }

    public List<TipoPagamento> getAll(String ordenardoPor) throws Exception {
        return (List<TipoPagamento>) getSession().createCriteria(TipoPagamento.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public TipoPagamento getByNome(TipoPagamento ts) throws Exception{
    
        return (TipoPagamento) getSession().createCriteria(TipoPagamento.class).add(Restrictions.eq("nome", ts.getNome())).uniqueResult();  
        
    }
    
}
