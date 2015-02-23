/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.io.Serializable;
import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import util.HibernateUtil;

/**
 *
 * @author RicardoHauy
 * @param <T>
 */
public abstract class GenericDao<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
 //   private SessionFactory sf;
    private Session sessao;

   
     /*
     public GenericDao() {
     this.sf = HibernateUtil.getSessionFactory();
     sessao = this.sf.openSession();
     }*/
     
    
    public GenericDao() {
        sessao = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    public void setSession(Session session) {
        this.sessao = session;
    }

    public Session getSession() {
        return this.sessao;
    }

    public void inserir(T obj) throws Exception {
        this.sessao.save(obj);
    }

    public void alterar(T obj) throws Exception {
        this.sessao.merge(obj);
    }

    public void excluir(T obj) throws Exception {
        
        this.sessao.flush();
        this.sessao.clear();
        this.sessao.delete(obj);
    }
    
}
