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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author RicardoHauy
 */
@Entity
public class Divida implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer coddivida;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "datadageracao")
    private Date datadageracao;
    
    @Column(name="valortotal", scale = 100, precision = 2)
    private double valortotal;

    @Column(name="qtdeparcelas", scale = 50)
    private Integer qtdeparcelas;

    @JoinColumn(name = "codacao", referencedColumnName = "codacao")
    @OneToOne(optional = false)
    private Acao acao;

    public void adicionarValorTotal(double v){
    
        valortotal+=v;
    
    }
    
    public void diminuirValorTotal(double v){
    
        valortotal-=v;
    
    }
    
    
    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }
    
    public Integer getQtdeparcelas() {
        return qtdeparcelas;
    }

    public void setQtdeparcelas(Integer qtdeparcelas) {
        this.qtdeparcelas = qtdeparcelas;
    }
       
    public Integer getCoddivida() {
        return coddivida;
    }

    public void setCoddivida(Integer coddivida) {
        this.coddivida = coddivida;
    }

    public Date getDatadageracao() {
        return datadageracao;
    }

    public void setDatadageracao(Date datadageracao) {
        this.datadageracao = datadageracao;
    }

    public double getValortotal() {
        return valortotal;
    }

    public void setValortotal(double valortotal) {
        this.valortotal = valortotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.coddivida != null ? this.coddivida.hashCode() : 0);
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
        final Divida other = (Divida) obj;
        if (this.coddivida != other.coddivida && (this.coddivida == null || !this.coddivida.equals(other.coddivida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Divida{" + "coddivida=" + coddivida + '}';
    }
 
}
