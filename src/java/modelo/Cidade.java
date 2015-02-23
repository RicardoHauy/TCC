/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author RicardoHauy
 */
@Entity
public class Cidade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codcidade;
    
    @Column(name = "nome", length=60)
    private String nomecidade;
    
    @Column(name = "estado", length=3)
    private String estado;
    
        public Integer getCodcidade() {
        return codcidade;
    }

    public void setCodcidade(Integer codcidade) {
        this.codcidade = codcidade;
    }

    public String getNomecidade() {
        return nomecidade;
    }

    public void setNomecidade(String nomecidade) {
                     
        this.nomecidade = nomecidade.toUpperCase();
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado.toUpperCase();
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (this.codcidade != null ? this.codcidade.hashCode() : 0);
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
        final Cidade other = (Cidade) obj;
        if (this.codcidade != other.codcidade && (this.codcidade == null || !this.codcidade.equals(other.codcidade))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Cidade{" + "codcidade=" + codcidade + '}';
    }

    
    
}
