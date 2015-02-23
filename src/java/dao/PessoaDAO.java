/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Moraem;
import modelo.Pessoa;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author RicardoHauy
 */
public class PessoaDAO extends GenericDao<Pessoa> {

    public Pessoa getById(Integer id) throws Exception {
        return (Pessoa) getSession().get(Pessoa.class, id);
    }

    public List<Pessoa> getAll(String ordenardoPor) throws Exception {
        

        return getSession().createCriteria(Pessoa.class).addOrder(Order.asc(ordenardoPor)).list();
        
    }

    public Pessoa getByCPForCNPJ(Pessoa pessoa) {

        Pessoa pes = null;
        
        if (pessoa.getNumcpf() != null) {

            return (Pessoa) this.getSession()
                    .createCriteria(Pessoa.class)
                    .add(Restrictions.eq("numcpf", pessoa.getNumcpf())).uniqueResult();
        } else if (pessoa.getNumcnpj() != null) {
            return (Pessoa) this.getSession()
                    .createCriteria(Pessoa.class)
                    .add(Restrictions.eq("numcnpj", pessoa.getNumcnpj())).uniqueResult();
        }
        
        return pes;

    }

    public List<Pessoa> getAll(int start, int qtd, Map<String, Object> filters) throws Exception {

        Criteria criteria = getSession().createCriteria(Pessoa.class).setMaxResults(qtd).setFirstResult(start);

        for (String s : filters.keySet()) {
            criteria.add(Restrictions.ilike(s, "%" + filters.get(s) + "%"));
        }

        return (List<Pessoa>) criteria.addOrder(Order.asc("nome")).list();
    }
    
    public Long getTotal() throws Exception {
        return (Long) getSession().createCriteria(Pessoa.class).setProjection(Projections.rowCount()).uniqueResult();
    }
    
    
    public void excluirPessoa(Pessoa p) throws Exception{
        
      
        List<Moraem> listademoraem;
        MoraemDAO daoMora = new MoraemDAO();
        listademoraem = daoMora.buscarTodos(p);
      //    daoMora.getSession().flush();
        daoMora.getSession().clear();
        for(Moraem m : listademoraem){
            daoMora.excluir(m);
        }
        excluir(p);
        
    }
    
}
