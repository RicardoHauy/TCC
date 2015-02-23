/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author RicardoHauy
 */
@Entity
public class Pasta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codpasta;
    
    @Column(name = "numeropasta")
    private Integer numeropasta;
    
    @Column(name = "idarmario")
    private Integer idarmario;
    
    @Column(name = "idgaveta")
    private Integer idgaveta;
    
    @JoinColumn(name = "codpessoa", referencedColumnName = "codpessoa")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public Integer getCodpasta() {
        return codpasta;
    }

    public void setCodpasta(Integer codpasta) {
        this.codpasta = codpasta;
    }

    public Integer getNumeropasta() {
        return numeropasta;
    }

    public void setNumeropasta(Integer numeropasta) {
        this.numeropasta = numeropasta;
    }

    public Integer getIdarmario() {
        return idarmario;
    }

    public void setIdarmario(Integer idarmario) {
        this.idarmario = idarmario;
    }

    public Integer getIdgaveta() {
        return idgaveta;
    }

    public void setIdgaveta(Integer idgaveta) {
        this.idgaveta = idgaveta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.codpasta != null ? this.codpasta.hashCode() : 0);
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
        final Pasta other = (Pasta) obj;
        if (this.codpasta != other.codpasta && (this.codpasta == null || !this.codpasta.equals(other.codpasta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pasta{" + "codpasta=" + codpasta + '}';
    }

   
    

      
}
