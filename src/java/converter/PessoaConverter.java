/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import dao.PessoaDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Pessoa;

/**
 *
 * @author RicardoHauy
 */

@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
        PessoaDAO dao = new PessoaDAO();
        Pessoa pessoa = null;
        try {
            pessoa = dao.getById(Integer.parseInt(key));
        } catch (Exception ex) {
            
        }
        return pessoa;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        Pessoa d = (Pessoa) o;
        return d.getCodpessoa().toString();
        
        
    }
    
}
