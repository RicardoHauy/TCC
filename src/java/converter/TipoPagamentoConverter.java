/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import dao.TipoPagamentoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TipoPagamento;

/**
 *
 * @author RicardoHauy
 */
@FacesConverter(forClass = TipoPagamento.class)
public class TipoPagamentoConverter implements Converter {
   
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
    
        TipoPagamentoDAO dao = new TipoPagamentoDAO();
        TipoPagamento tipoPagamento = null;
        
        try {
            tipoPagamento = dao.getById(Integer.parseInt(key));
        } catch (Exception ex) {

        }
        return tipoPagamento;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        TipoPagamento d = (TipoPagamento) o;
        return d.getCodigo().toString();

    }
}
