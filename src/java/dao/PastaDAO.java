/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Cidade;
import modelo.Pasta;
import modelo.Pessoa;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class PastaDAO extends GenericDao<Pasta>{
    
     public Pasta getById(Integer id) throws Exception {
         return (Pasta) getSession().get(Pasta.class, id);
     }

     public List<Pasta> getAll(String ordenardoPor) throws Exception {
         return (List<Pasta>) getSession().createCriteria(Pasta.class).addOrder(Order.asc(ordenardoPor)).list();
     }
    
    public Pasta getByNumeroAndArmario(Integer num, Integer arm) throws Exception {

        if ((num == null)||(num==0)||(num.equals(null))) {
            return null;
        }
        if ((arm == 0)||(arm==null)||(arm.equals(null))) {
            return null;
        }

        return (Pasta) this.getSession()
                .createCriteria(Pasta.class)
                .add(Restrictions.eq("numeropasta", num)).add(Restrictions.eq("idarmario", arm)).uniqueResult();
    }
    
    public List<Pasta> getByPessoa(Pessoa p) throws Exception{
    
        return getSession().createCriteria(Pasta.class).add(Restrictions.eq("pessoa", p)).addOrder(Order.asc("codpasta")).list();
    
    }
    
    
}
