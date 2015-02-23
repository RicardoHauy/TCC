/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;

import modelo.Cidade;
import modelo.Moraem;
import modelo.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class CidadeDAO extends GenericDao<Cidade> {
    
    public Cidade getById(Integer id) throws Exception {
        return (Cidade) getSession().get(Cidade.class, id);
    }

    public List<Cidade> getAll(String ordenardoPor) throws Exception {
        return (List<Cidade>) getSession().createCriteria(Cidade.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public Cidade getByNomeAndEstado(String nome, String estado) throws Exception {

        if (nome == null || nome.trim().equals("")) {
            return null;
        }
        if (estado == null || estado.trim().equals("")) {
            return null;
        }

        return (Cidade) this.getSession()
                .createCriteria(Cidade.class)
                .add(Restrictions.eq("nomecidade", nome)).add(Restrictions.eq("estado", estado)).uniqueResult();
    }
    
    public List<Cidade> getByPessoa(Pessoa pessoa) throws Exception{
    
        
        Moraem moraem = (Moraem) this.getSession().createCriteria(Moraem.class).add(Restrictions.eq("pessoa", pessoa)).uniqueResult();
        
        
        return (List<Cidade>) this.getSession().createCriteria(Cidade.class).add(Restrictions.eq("moraem", moraem)).list();
    
    }
    
    
}
