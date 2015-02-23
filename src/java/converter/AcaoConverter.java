/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import dao.AcaoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Acao;

/**
 *
 * @author RicardoHauy
 */
@FacesConverter(forClass = Acao.class)
public class AcaoConverter implements Converter {
    
     @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
        AcaoDAO dao = new AcaoDAO();
        Acao acao = null;
        try {
            acao= dao.getById(Integer.parseInt(key));
        } catch (Exception ex) {
            
        }
        return acao;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        Acao d = (Acao) o;
        return d.getCodacao().toString();
        
        
    }
    
    
}
