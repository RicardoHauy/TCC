/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import dao.TipoAcaoDAO;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.TipoAcao;

/**
 *
 * @author RicardoHauy
 */
@FacesConverter(forClass = TipoAcao.class)
public class TipoAcaoConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String key) {
        TipoAcaoDAO dao = new TipoAcaoDAO();
        TipoAcao tipoAcao = null;
        try {
            tipoAcao = dao.getById(Integer.parseInt(key));
        } catch (Exception ex) {

        }
        return tipoAcao;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        TipoAcao d = (TipoAcao) o;
        return d.getCodigo().toString();

    }

}
