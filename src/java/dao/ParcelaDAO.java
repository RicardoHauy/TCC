/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Divida;
import modelo.Parcela;
import modelo.TipoPagamento;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class ParcelaDAO extends GenericDao<Parcela> {
    public Parcela getById(Integer id) throws Exception {
        return (Parcela) getSession().get(Parcela.class, id);
    }

    public List<Parcela> getAll(String ordenardoPor) throws Exception {
        return (List<Parcela>) getSession().createCriteria(Parcela.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public List<Parcela> getAllByDivida(Divida p) throws Exception{
    
       return (List<Parcela>) getSession().createCriteria(Parcela.class).add(Restrictions.eq("divida", p)).addOrder(Order.asc("dataapagar")).list();
        
    } 
    
      
    public List<Parcela> getAllByTipo(TipoPagamento tipo) throws Exception{
        
         return (List<Parcela>) getSession().createCriteria(Parcela.class).add(Restrictions.eq("tipopagamento", tipo)).list();
       
        
    }
    
    public List<Parcela> calcValorTotalParcelado(Divida div) throws Exception{
       
       return (List<Parcela>) getSession().createCriteria(Parcela.class).add(Restrictions.eq("divida", div)).list();
    
    }
}
