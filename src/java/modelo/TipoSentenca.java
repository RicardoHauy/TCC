/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author RicardoHauy
 */
@Entity
public class TipoSentenca implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigotiposentenca;
    
    @Column(name = "nome", length=70)
    private String nome;
    

    public Integer getCodigotiposentenca() {
        return codigotiposentenca;
    }

    public void setCodigotiposentenca(Integer codigotiposentenca) {
        this.codigotiposentenca = codigotiposentenca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + (this.codigotiposentenca != null ? this.codigotiposentenca.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TipoSentenca other = (TipoSentenca) obj;
        if (this.codigotiposentenca != other.codigotiposentenca && (this.codigotiposentenca == null || !this.codigotiposentenca.equals(other.codigotiposentenca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TipoSentenca{" + "codigotiposentenca=" + codigotiposentenca + '}';
    }

    
    
}
