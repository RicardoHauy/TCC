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
public class Sentenca implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codsentenca;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "datasentenca")
    private Date datadasentenca;
    
    @Column(name = "descricao", length=200)
    private String descricao;
    
    @JoinColumn(name = "codacao", referencedColumnName = "codacao")
    @ManyToOne(optional = false)
    private Acao acao;
    
    @JoinColumn(name = "codigotiposentenca", referencedColumnName = "codigotiposentenca")
    @ManyToOne(optional = false)
    private TipoSentenca tiposentenca;

    public Integer getCodsentenca() {
        return codsentenca;
    }

    public void setCodsentenca(Integer codsentenca) {
        this.codsentenca = codsentenca;
    }

    public Date getDatadasentenca() {
        return datadasentenca;
    }

    public void setDatadasentenca(Date datadasentenca) {
        this.datadasentenca = datadasentenca;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    public TipoSentenca getTiposentenca() {
        return tiposentenca;
    }

    public void setTiposentenca(TipoSentenca tiposentenca) {
        this.tiposentenca = tiposentenca;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.codsentenca != null ? this.codsentenca.hashCode() : 0);
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
        final Sentenca other = (Sentenca) obj;
        if (this.codsentenca != other.codsentenca && (this.codsentenca == null || !this.codsentenca.equals(other.codsentenca))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Sentenca{" + "codsentenca=" + codsentenca + '}';
    }

    
    
}
