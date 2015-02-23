/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package converter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import oracle.jrockit.jfr.parser.ParseException;

/**
 *
 * @author RicardoHauy
 */
@FacesConverter("converter.MoneyConverter")
public class MoneyConverter implements Converter {
 
 final private Locale locale = new Locale("pt", "BR");
 final private DecimalFormat decimalFormat = new DecimalFormat("##0,00", new DecimalFormatSymbols(locale));
 
 public BigDecimal getAsObject(FacesContext fc, UIComponent component, String value) {
 
 try {
 
   decimalFormat.setParseBigDecimal(true);
 
   return (BigDecimal) decimalFormat.parse(value);
 } catch (Exception e) {
  throw new ConverterException("Error", e);
 }
 
}
 
public String getAsString(FacesContext fc, UIComponent component, Object value) {
  DecimalFormat df = new DecimalFormat("###,###,##0.00");
  return df.format(value);
 }
}
