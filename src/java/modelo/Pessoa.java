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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author RicardoHauy
 */
@Entity
public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer codpessoa;
    
    @Column(name = "nome", length=60)
    private String nome;
    
    @Column(name = "email", length=50)
    private String email;
    
    @Column(name = "numrg", length=20)
    private String numrg;
    
    @Column(name = "numcpf", length=20)
    private String numcpf;
    
    @Column(name = "celular", length = 20)
    private String celular;
    
    @Column(name = "telefone", length = 20)
    private String telefone;
    
    @Column(name = "numcnpj", length = 20)
    private String numcnpj;
    
    @Column(name = "observacoes", length = 800)
    private String observacoes;
    
    @Column(name = "rua", length = 60)
    private String rua;
    
    @Column(name = "numcasa", length = 10)
    private String numcasa;
    
    @Column(name = "bairro", length = 60)
    private String bairro;
    
    @Column(name = "cep")
    private String cep;
    
    @Column(name = "complemento", length = 20)
    private String complemento;
    
    @Column(name = "fisjur")
    private Integer fisjur;
  
    @JoinColumn(name = "codusuario", referencedColumnName = "codigo")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Pasta> listadepastas;
    
  //  @OneToMany(mappedBy = "pessoa")
  //  private List<PessoaAcao> listadeacoes;
    
    
    public Pessoa() {
     
        
    }

   // public List<PessoaAcao> getListadeacoes() {
   //     return listadeacoes;
   // }

   // public void setListadeacoes(List<PessoaAcao> listadeacoes) {
   //     this.listadeacoes = listadeacoes;
   // }

    public List<Pasta> getListadepastas() {
        return listadepastas;
    }

    public void setListadepastas(List<Pasta> listadepastas) {
        this.listadepastas = listadepastas;
    }
  
    
    public Integer getFisjur() {
        return fisjur;
    }

    public void setFisjur(Integer fisjur) {
        this.fisjur = fisjur;
    }
    
    
    public Integer getCodpessoa() {
        return codpessoa;
    }

    public void setCodpessoa(Integer codpessoa) {
        this.codpessoa = codpessoa;
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
  
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumrg() {
        return numrg;
    }

    public void setNumrg(String numrg) {
        this.numrg = numrg;
    }

    public String getNumcpf() {
        return numcpf;
    }

    public void setNumcpf(String numcpf) {
        this.numcpf = numcpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNumcnpj() {
        return numcnpj;
    }

    public void setNumcnpj(String numcnpj) {
        this.numcnpj = numcnpj;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumcasa() {
        return numcasa;
    }

    public void setNumcasa(String numcasa) {
        this.numcasa = numcasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.codpessoa != null ? this.codpessoa.hashCode() : 0);
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
        final Pessoa other = (Pessoa) obj;
        if (this.codpessoa != other.codpessoa && (this.codpessoa == null || !this.codpessoa.equals(other.codpessoa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pessoa{" + "codpessoa=" + codpessoa + '}';
    }
    
    
    
}
