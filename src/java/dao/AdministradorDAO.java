/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import modelo.Administrador;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class AdministradorDAO extends GenericDao<Administrador> {
    
    public Administrador getById(Integer id) throws Exception {
        return (Administrador) getSession().get(Administrador.class, id);
    }

    public List<Administrador> getAll(String ordenardoPor) throws Exception {
        return (List<Administrador>) getSession().createCriteria(Administrador.class).addOrder(Order.asc(ordenardoPor)).list();
    }
    
    public Administrador getByLoginAndSenha(String nome, String senha) throws Exception {

        if (nome == null || nome.trim().equals("")) {
            return null;
        }
        if (senha == null || senha.trim().equals("")) {
            return null;
        }

        return (Administrador) this.getSession()
                .createCriteria(Administrador.class)
                .add(Restrictions.eq("nome", nome)).add(Restrictions.eq("senha", senha)).uniqueResult();
    }
    
    
}
