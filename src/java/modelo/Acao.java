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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author RicardoHauy
 */

@Entity
public class Acao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codacao;
    
    @Column(name = "numproc", unique = true, length=70)
    private String numproc;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "datadist")
    private Date datadist;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataaentrar")
    private Date dataaentrar;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "dataproxalteracaoacao")
    private Date dataproxalteracaoacao;
    
    @Column(name="valordaacao", scale = 100, precision = 2)
    private double valordaacao;
    
    @Column(name="valorcombinado", scale = 100, precision = 2)
    private double valorcombinado;
    
    @Column(name="descricao", length = 200)
    private String descricao;
    
    @Column(name="observacoes", length = 800)
    private String observacoes;
    
    @JoinColumn(name = "codtipoacao", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private TipoAcao tipoacao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorcombinado() {
        return valorcombinado;
    }

    public void setValorcombinado(double valorcombinado) {
        this.valorcombinado = valorcombinado;
    }
    
    
    
  //  private Divida divida;
    
//    @OneToMany(mappedBy = "acao", cascade = CascadeType.ALL)
//    private List<PessoaAcao> listadepessoas;

    //  private List<Sentenca> sentencas;
    public Integer getCodacao() {
        return codacao;
    }

    public void setCodacao(Integer codacao) {
        this.codacao = codacao;
    }

    public TipoAcao getTipoacao() {
        return tipoacao;
    }

    public void setTipoacao(TipoAcao tipoacao) {
        this.tipoacao = tipoacao;
    }

    public String getNumproc() {
        return numproc;
    }

    public void setNumproc(String numproc) {
        this.numproc = numproc;
    }

    public Date getDatadist() {
        return datadist;
    }

    public void setDatadist(Date datadist) {
        this.datadist = datadist;
    }

    public Date getDataaentrar() {
        return dataaentrar;
    }

    public void setDataaentrar(Date dataaentrar) {
        this.dataaentrar = dataaentrar;
    }

    public Date getDataproxalteracaoacao() {
        return dataproxalteracaoacao;
    }

    public void setDataproxalteracaoacao(Date dataproxalteracaoacao) {
        this.dataproxalteracaoacao = dataproxalteracaoacao;
    }

    public double getValordaacao() {
        return valordaacao;
    }

    public void setValordaacao(double valordaacao) {
        this.valordaacao = valordaacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.codacao != null ? this.codacao.hashCode() : 0);
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
        final Acao other = (Acao) obj;
        if (this.codacao != other.codacao && (this.codacao == null || !this.codacao.equals(other.codacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Acao{" + "codacao=" + codacao + '}';
    }

      
}
