/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author RicardoHauy
 */
@ManagedBean
@ViewScoped
public class RelatorioTodasAcoesBean extends GenericBean {

    private Integer mes;

    private Integer ano;

    /**
     * Creates a new instance of RelatorioTodasAcoesBean
     */
    public RelatorioTodasAcoesBean() {
    }

    @PostConstruct
    public void construct() {
    }

    private Connection getConnection() {

        Connection con = null;
        try {

            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:tcc/tcc@//localhost:1521/XE");

        } catch (Exception ex) {
            System.out.println("Erro na Conexão --> " + ex.getMessage());
        }

        return con;

    }

    protected void redirect(String page) {

        FacesContext ctx = FacesContext.getCurrentInstance();
        ExternalContext ec = ctx.getExternalContext();

        try {
            ec.redirect(ec.getRequestContextPath() + page);
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ex.getMessage()));
        }

    }

    public int getMaxDiasFevereiro(int year) {
        Calendar cal = (Calendar) Calendar.getInstance().clone();

        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 1); // Fevereiro  
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void imprimirRelatorioAcoesPorCliente(Integer cod) {

        try {

            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

            session.setAttribute("relatorio", "reldeacoesporcliente.jasper");
            session.setAttribute("codcli", cod);

            redirect("/RelatorioAcoesPorCliente1");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Imprimir Relatório de Ações por Cliente");
            System.out.println("Erro ao Imprimir Relatório de Ações por Cliente --> " + ex.getMessage());
        }

    }

    public void imprimirRelatorioFaturamentoMensal() {

        try {

            Connection connection = getConnection();

            HashMap map = new HashMap();

            DateFormat strDf = new SimpleDateFormat("dd/MM/yyyy");
            String datainiciostring = "01/01/1900";
            String datafimstring = "01/01/1901";

            if (mes == 1) {

                datainiciostring = "01/01/" + String.valueOf(ano);
                datafimstring = "31/01/" + String.valueOf(ano);

                System.out.println("data inicio --> " + datainiciostring);
                System.out.println("data fim --> " + datafimstring);

            } else if (mes == 2) {

                datainiciostring = "01/02/" + String.valueOf(ano);

                if (getMaxDiasFevereiro(ano) == 29) {

                    datafimstring = "29/01/" + String.valueOf(ano);

                } else {

                    datafimstring = "28/01/" + String.valueOf(ano);
                }

            } else if (mes == 3) {

                datainiciostring = "1/03/" + String.valueOf(ano);
                datafimstring = "31/03/" + String.valueOf(ano);

            } else if (mes == 4) {

                datainiciostring = "1/04/" + String.valueOf(ano);
                datafimstring = "30/04/" + String.valueOf(ano);

            } else if (mes == 5) {

                datainiciostring = "1/05/" + String.valueOf(ano);
                datafimstring = "31/05/" + String.valueOf(ano);

            } else if (mes == 6) {

                datainiciostring = "1/06/" + String.valueOf(ano);
                datafimstring = "30/06/" + String.valueOf(ano);

            } else if (mes == 7) {

                datainiciostring = "1/07/" + String.valueOf(ano);
                datafimstring = "31/07/" + String.valueOf(ano);

            } else if (mes == 8) {

                datainiciostring = "1/08/" + String.valueOf(ano);
                datafimstring = "31/08/" + String.valueOf(ano);

            } else if (mes == 9) {
                datainiciostring = "1/09/" + String.valueOf(ano);
                datafimstring = "30/09/" + String.valueOf(ano);

            } else if (mes == 10) {

                datainiciostring = "1/10/" + String.valueOf(ano);
                datafimstring = "31/10/" + String.valueOf(ano);

            } else if (mes == 11) {

                datainiciostring = "1/11/" + String.valueOf(ano);
                datafimstring = "30/11/" + String.valueOf(ano);

            } else if (mes == 12) {

                datainiciostring = "1/12/" + String.valueOf(ano);
                datafimstring = "31/12/" + String.valueOf(ano);

            }

            String sql = "select sum(valorparcela) as totalparc from pessoaacao pa, acao ac, divida div, parcela parc where pa.codacao=ac.codacao and ac.codacao=div.codacao and div.coddivida=parc.coddivida and pa.tipopessoa=1 and parc.dataquepagou between '" + datainiciostring + "' and '" + datafimstring + "'";

            Statement stm = connection.createStatement();

            ResultSet rs = stm.executeQuery(sql);

            rs.next();

            float totalparcelas = rs.getFloat("totalparc");

            String sql2 = "select sum(valor) as totaldesp from pessoaacao pa, acao ac, divida div, itensdivida id where pa.codacao=ac.codacao and ac.codacao=div.codacao and div.coddivida=id.coddivida and pa.tipopessoa=1 and id.dataocorrencia between '" + datainiciostring + "' and '" + datafimstring + "'";

            rs = stm.executeQuery(sql2);

            rs.next();

            float totaldespesas = rs.getFloat("totaldesp");

            float ganhoperda = totalparcelas - totaldespesas;

            Date datainicio = strDf.parse(datainiciostring);

            Date datafim = strDf.parse(datafimstring);

            System.out.println(" GANHOPERDA =  " + ganhoperda);

            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

            session.setAttribute("ganhoperda", ganhoperda);
            session.setAttribute("datainicio", datainicio);
            session.setAttribute("datafim", datafim);
            session.setAttribute("ano", ano);
            session.setAttribute("mes", mes);
            session.setAttribute("relatorio", "relfaturamentomensal.jasper");

            connection.close();
            redirect("/RelatorioFaturamentoMensal");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Imprimir Relatório de Ações por Cliente");
            System.out.println("Erro ao Imprimir Relatório de Ações por Cliente --> " + ex.getMessage());
        }

    }

    public void imprimirRelatorioProxOcorrencias() {

        try {

            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

            session.setAttribute("relatorio", "relproximasalteracoesacao.jasper");

            redirect("/RelatorioProxOcorrencias");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Imprimir Relatório de Próximas Ocorrências");
            System.out.println("Erro ao Imprimir Relatório de Próximas Ocorrências --> " + ex.getMessage());
        }

    }

    public void imprimirRelatorioSentencasProcedentes() {

        try {

            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

            session.setAttribute("relatorio", "relsentencasprocedentes.jasper");

            redirect("/RelatorioSentencasProcedentes");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Imprimir Relatório de Sentenças Procedentes");
            System.out.println("Erro ao Imprimir Relatório de Sentenças Procedentes --> " + ex.getMessage());
        }

    }

    public void imprimirRelatorio() throws Exception {  //Imprime o Relatório de Clientes Inadimplentes

        try {

            FacesContext ctx = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);

            session.setAttribute("relatorio", "relclinaopagou.jasper");

            redirect("/RelatorioTodasAcoesServlet");

        } catch (Exception ex) {
            exibirMensagemErro("Erro ao Imprimir Relatório de Todas as Ações");
            System.out.println("Erro ao Imprimir Relatório de Todas as Ações --> " + ex.getMessage());
        }
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

}
