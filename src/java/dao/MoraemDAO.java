/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import modelo.Moraem;
import modelo.Pessoa;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class MoraemDAO extends GenericDao<Moraem> {

    public Moraem buscarUltimo(Pessoa pessoa) throws Exception {

        List<Moraem> moras;

        moras = (List<Moraem>) this.getSession().createCriteria(Moraem.class).add(Restrictions.eq("pessoa", pessoa)).addOrder(Order.asc("codmoraem")).list();

        if (moras != null) {

            return moras.get(moras.size() - 1);
            
        }
        
        return null;
        
    }

    public List<Moraem> buscarTodos(Pessoa pessoa) throws Exception {

        List<Moraem> moras;

        moras = (List<Moraem>) this.getSession().createCriteria(Moraem.class).add(Restrictions.eq("pessoa", pessoa)).list();

        return moras;

    }
    
    public List<Moraem> buscarTodosPorPessoaOrdenado(Pessoa p) throws Exception{
    
        List<Moraem> moras;

        moras = (List<Moraem>) this.getSession().createCriteria(Moraem.class).add(Restrictions.eq("pessoa", p)).addOrder(Order.asc("codmoraem")).list();

        return moras;
    
    }
    

}
