/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lazy;

import dao.UsuarioDAO;
import java.util.List;
import java.util.Map;
import modelo.Usuario;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author RicardoHauy
 */
public class UsuarioLazy extends LazyDataModel<Usuario> {
    
    public UsuarioLazy(){
    }
    
     @Override
    public List<Usuario> load(int startingAt, int maxPerPage, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Usuario> lista = null;

        int ordem = 1;
        if (sortOrder.equals(SortOrder.UNSORTED)) {
            ordem = 1;
        } else if (sortOrder.equals(SortOrder.ASCENDING)) {
            ordem = 1;
        } else if (sortOrder.equals(SortOrder.DESCENDING)) {
            ordem = 2;
        }

        try {
            UsuarioDAO usuarioDao = new UsuarioDAO();
            lista = usuarioDao.getAll(startingAt, maxPerPage, sortField, ordem, filters);
            setRowCount(usuarioDao.getTotal().intValue());
        } catch (Exception e) {
            System.out.println("ERRO NO LAZY -->" + e.getMessage());
        }
        return lista;
    }

    @Override
    public Object getRowKey(Usuario object) {
        return object.getCodigo();
    }
    
}
