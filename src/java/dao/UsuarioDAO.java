/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import java.util.Map;
import modelo.Usuario;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class UsuarioDAO extends GenericDao<Usuario> {
    
    public Usuario getById(Integer id) throws Exception {
        return (Usuario) getSession().get(Usuario.class, id);
    }

    public List<Usuario> getAll(String ordenardoPor) throws Exception {
        return (List<Usuario>) getSession().createCriteria(Usuario.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public Usuario getByLoginAndSenha(String nome, String senha) throws Exception {

        if (nome == null || nome.trim().equals("")) {
            return null;
        }
        if (senha == null || senha.trim().equals("")) {
            return null;
        }

        return (Usuario) this.getSession()
                .createCriteria(Usuario.class)
                .add(Restrictions.eq("nome", nome)).add(Restrictions.eq("senha", senha)).uniqueResult();
    }
    
     public List<Usuario> getAll(int start, int qtd, String sortField, int ordem, Map<String, Object> filters) throws Exception {

        Criteria criteria = getSession().createCriteria(Usuario.class).setMaxResults(qtd).setFirstResult(start);

        for (String s : filters.keySet()) {
            criteria.add(Restrictions.ilike(s, "%" + filters.get(s) + "%"));
        }

      
                criteria.addOrder(Order.asc(sortField));
      
        

        return (List<Usuario>) criteria.list();
    }
    
    public Long getTotal() throws Exception {
        return (Long) getSession().createCriteria(Usuario.class).setProjection(Projections.rowCount()).uniqueResult();
    }
    
}
