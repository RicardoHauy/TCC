/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author RicardoHauy
 */

@Entity
public class ItensDivida implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer coditemdiv;
    
    @Column(name="descricao", length = 100)
    private String descricao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataocorrencia")
    private Date dataocorrencia;
    
    @Column(name="valor", scale = 100, precision = 2)
    private double valor;
    
    @JoinColumn(name = "coddivida", referencedColumnName = "coddivida")
    @ManyToOne(optional = false)
    private Divida divida;

    public Integer getCoditemdiv() {
        return coditemdiv;
    }

    public void setCoditemdiv(Integer coditemdiv) {
        this.coditemdiv = coditemdiv;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataocorrencia() {
        return dataocorrencia;
    }

    public void setDataocorrencia(Date dataocorrencia) {
        this.dataocorrencia = dataocorrencia;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (this.coditemdiv != null ? this.coditemdiv.hashCode() : 0);
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
        final ItensDivida other = (ItensDivida) obj;
        if (this.coditemdiv != other.coditemdiv && (this.coditemdiv == null || !this.coditemdiv.equals(other.coditemdiv))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ItensDivida{" + "coditemdiv=" + coditemdiv + '}';
    }
    
}
