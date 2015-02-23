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
public class HistoricoAcao implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codhistacao;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "datahist")
    private Date datahist;
    
    @Column(name="descricao", length = 200)
    private String descricao;

    @JoinColumn(name = "codacao", referencedColumnName = "codacao")
    @ManyToOne(optional = false)
    private Acao acao;
    
    public Integer getCodhistacao() {
        return codhistacao;
    }

    public void setCodhistacao(Integer codhistacao) {
        this.codhistacao = codhistacao;
    }

    public Date getDatahist() {
        return datahist;
    }

    public void setDatahist(Date datahist) {
        this.datahist = datahist;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

   

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.codhistacao != null ? this.codhistacao.hashCode() : 0);
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
        final HistoricoAcao other = (HistoricoAcao) obj;
        if (this.codhistacao != other.codhistacao && (this.codhistacao == null || !this.codhistacao.equals(other.codhistacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HistoricoAcao{" + "codhistacao=" + codhistacao + '}';
    }
    
    
    
}
