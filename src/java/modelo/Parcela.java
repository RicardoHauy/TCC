/*
 * To change this template, choose Tools | Templates
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
public class Parcela implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codigo;
    
    @Column(name="valorparcela", scale = 100, precision = 2)
    private double valorparcela;
   
    @Column(name="numparcela", scale = 50)
    private Integer numparcela;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataapagar")
    private Date dataapagar;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataquepagou")
    private Date dataquepagou;
    
    @JoinColumn(name = "coddivida", referencedColumnName = "coddivida")
    @ManyToOne(optional = false)
    private Divida divida;
    
    @JoinColumn(name = "codtipopagamento", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoPagamento tipopagamento;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public double getValorparcela() {
        return valorparcela;
    }

    public void setValorparcela(double valorparcela) {
        this.valorparcela = valorparcela;
    }

    public Integer getNumparcela() {
        return numparcela;
    }

    public void setNumparcela(Integer numparcela) {
        this.numparcela = numparcela;
    }

    public Date getDataapagar() {
        return dataapagar;
    }

    public void setDataapagar(Date dataapagar) {
        this.dataapagar = dataapagar;
    }

    public Date getDataquepagou() {
        return dataquepagou;
    }

    public void setDataquepagou(Date dataquepagou) {
        this.dataquepagou = dataquepagou;
    }

    public Divida getDivida() {
        return divida;
    }

    public void setDivida(Divida divida) {
        this.divida = divida;
    }

    public TipoPagamento getTipopagamento() {
        return tipopagamento;
    }

    public void setTipopagamento(TipoPagamento tipopagamento) {
        this.tipopagamento = tipopagamento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.codigo != null ? this.codigo.hashCode() : 0);
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
        final Parcela other = (Parcela) obj;
        if (this.codigo != other.codigo && (this.codigo == null || !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Parcela{" + "codigo=" + codigo + '}';
    }

}
