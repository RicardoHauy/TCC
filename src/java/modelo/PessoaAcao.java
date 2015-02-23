/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
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
public class PessoaAcao implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codpessoaacao;
    
    @Column(name = "tipopessoa", scale = 2)
    private Integer tipopessoa;
    
    @JoinColumn(name = "codpessoa", referencedColumnName = "codpessoa")
    @ManyToOne(optional = false)
    private Pessoa pessoa;
   
    @JoinColumn(name = "codacao", referencedColumnName = "codacao")
    @ManyToOne(optional = false)
    private Acao acao;

    public PessoaAcao() {
    }

    public Integer getCodpessoaacao() {
        return codpessoaacao;
    }

    public void setCodpessoaacao(Integer codpessoaacao) {
        this.codpessoaacao = codpessoaacao;
    }

    public Integer getTipopessoa() {
        return tipopessoa;
    }

    public void setTipopessoa(Integer tipopessoa) {
        this.tipopessoa = tipopessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Acao getAcao() {
        return acao;
    }

    public void setAcao(Acao acao) {
        this.acao = acao;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + (this.codpessoaacao != null ? this.codpessoaacao.hashCode() : 0);
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
        final PessoaAcao other = (PessoaAcao) obj;
        if (this.codpessoaacao != other.codpessoaacao && (this.codpessoaacao == null || !this.codpessoaacao.equals(other.codpessoaacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PessoaAcao{" + "codpessoaacao=" + codpessoaacao + '}';
    }

    
        
}
