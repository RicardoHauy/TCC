/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Acao;
import modelo.Divida;
import modelo.ItensDivida;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class DividaDAO extends GenericDao<Divida>{
    
    public List<Divida> getAll(String ordenardoPor) throws Exception {
        return (List<Divida>) getSession().createCriteria(Divida.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
     public Divida getByAcao(Acao p) throws Exception{
    
       return (Divida) getSession().createCriteria(Divida.class).add(Restrictions.eq("acao", p)).uniqueResult();
        
    } 
     
    public List<ItensDivida> getAllItensDivida(Divida d) throws Exception{
    
        return getSession().createCriteria(ItensDivida.class).add(Restrictions.eq("divida", d)).addOrder(Order.asc("dataocorrencia")).list();
    
    } 
    
}
