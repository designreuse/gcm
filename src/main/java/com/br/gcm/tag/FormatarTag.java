package com.br.gcm.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class FormatarTag extends SimpleTagSupport {

    private String tipo;
    private String cnpjcpf;

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCnpjcpf(String cnpjcpf) {
        this.cnpjcpf = cnpjcpf;
    }

    @Override
    public void doTag() throws JspException, IOException {

        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();

        if (tipo.toUpperCase().equals("CNPJ")) {
            out.println(formatarCnpj(cnpjcpf));
        } else {
            out.println(formatarCpf(cnpjcpf));
        }

    }

    private String formatar(String valor, String mascara) {

        String dado = "";
        // remove caracteres nao numericos
        for ( int i = 0; i < valor.length(); i++ )  {
            char c = valor.charAt(i);
            if ( Character.isDigit( c ) ) { dado += c; }
        }

        int indMascara = mascara.length();
        int indCampo = dado.length();

        for ( ; indCampo > 0 && indMascara > 0; ) {
            if ( mascara.charAt( --indMascara ) == '#' ) { indCampo--; }
        }

        String saida = "";
        for ( ; indMascara < mascara.length(); indMascara++ ) {
            saida += ( ( mascara.charAt( indMascara ) == '#' ) ? dado.charAt( indCampo++ ) : mascara.charAt( indMascara ) );
        }
        return saida;
    }

    private String formatarCpf(String cpf) {
        while( cpf.length() < 11 ) {
            cpf = "0" + cpf;
        }
        return formatar( cpf, "###.###.###-##" );
    }

    private String formatarCnpj(String cnpj) {
        while( cnpj.length() < 14 ) {
            cnpj = "0" + cnpj;
        }
        return formatar( cnpj,"##.###.###/####-##" );
    }

}