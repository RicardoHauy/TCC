/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lazy;

import dao.PessoaDAO;
import java.util.List;
import java.util.Map;
import modelo.Pessoa;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author RicardoHauy
 */
public class PessoaLazy extends LazyDataModel<Pessoa>{
    
    public PessoaLazy(){}
    
       @Override
    public List<Pessoa> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        try {
            System.out.println("lazy 1");
            PessoaDAO pessoaDao = new PessoaDAO();           
            System.out.println("lazy 2");
            setRowCount(pessoaDao.getTotal().intValue());
            System.out.println("lazy 3");
            
            return pessoaDao.getAll(startingAt, maxPerPage,filters);
            
        } catch (Exception e) {
            System.out.println("ERRO NO LAZY -->" + e.getMessage());
        }
        return null;
    }

    @Override
    public Object getRowKey(Pessoa object) {
        return object.getCodpessoa();
    }
    
}
