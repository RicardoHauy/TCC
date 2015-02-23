/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.CascadeType;
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
public class Moraem implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codmoraem;
    
    @JoinColumn(name = "codpessoa", referencedColumnName = "codpessoa")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
    
    @JoinColumn(name = "codcidade", referencedColumnName = "codcidade")
    @ManyToOne(optional = false)
    private Cidade cidade;

    public Integer getCodmoraem() {
        return codmoraem;
    }

    public void setCodmoraem(Integer codmoraem) {
        this.codmoraem = codmoraem;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.codmoraem != null ? this.codmoraem.hashCode() : 0);
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
        final Moraem other = (Moraem) obj;
        if (this.codmoraem != other.codmoraem && (this.codmoraem == null || !this.codmoraem.equals(other.codmoraem))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Moraem{" + "codmoraem=" + codmoraem + '}';
    }

    
    
}
