/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import dao.TipoSentencaDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TipoSentenca;

/**
 *
 * @author RicardoHauy
 */
@FacesConverter(forClass = TipoSentenca.class)
public class TipoSentencaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
        TipoSentencaDAO dao = new TipoSentencaDAO();
        TipoSentenca tipoSentenca = null;
        try {
            tipoSentenca = dao.getById(Integer.parseInt(key));
        } catch (Exception ex) {

        }
        return tipoSentenca;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        TipoSentenca d = (TipoSentenca) o;
        return d.getCodigotiposentenca().toString();

    }
}
