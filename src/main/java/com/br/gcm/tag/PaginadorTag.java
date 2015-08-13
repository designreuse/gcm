package com.br.gcm.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Enumeration;

public class PaginadorTag extends SimpleTagSupport {

    private static Logger logger = LoggerFactory.getLogger(PaginadorTag.class);

    // icons
    private static final String FIRST_ICON = "fa fa-fast-backward";
    private static final String PRIOR_ICON = "fa fa-step-backward";
    private static final String NEXT_ICON  = "fa fa-step-forward";
    private static final String LAST_ICON  = "fa fa-fast-forward";

//    private static final String FIRST_ICON = "fa fa-angle-double-left";
//    private static final String PRIOR_ICON = "fa fa-angle-left";
//    private static final String NEXT_ICON  = "fa fa-angle-right";
//    private static final String LAST_ICON  = "fa fa-angle-double-right";

    private Pagina pagina;

    public void setPagina(Pagina pagina) {
        this.pagina = pagina;
    }

    @Override
    public void doTag() throws JspException, IOException {
        PageContext pageContext = (PageContext) getJspContext();
        JspWriter out = pageContext.getOut();
        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
        String contextPath = (String) request.getAttribute(WebUtils.FORWARD_REQUEST_URI_ATTRIBUTE);

        // concatena params existentes exceto page
        Enumeration en = pageContext.getRequest().getParameterNames();
        String req = ""; int cont = 0;
        while (en.hasMoreElements()) {
            String param = (String) en.nextElement();
            if (!param.equals("page")) {
                if (cont++ != 0) {
                    req += "&";
                }
                req += param + "=" + pageContext.getRequest().getParameter(param);
            }
        }

        req += (cont == 0) ? "?page=" : "&page=";
        req = contextPath + (!req.startsWith("?") ? "?" : "") + req;

        // números de páginas
        int firstPage  = 0;
        int priorPage  = pagina.getPage() <= 0 ? 0 : pagina.getPage() - 1;
        int actualPage = pagina.getPage();
        int lastPage   = pagina.getTotalPaginas() > 0 ? pagina.getTotalPaginas() - 1 : 0;
        int nextPage   = actualPage < lastPage ? actualPage + 1 : lastPage;

        // urls
        String firstPageUrl  = req + String.valueOf(firstPage);
        String priorPageUrl  = req + String.valueOf(priorPage);
        String actualPageUrl = req + String.valueOf(actualPage);
        String nextPageUrl   = req + String.valueOf(nextPage);
        String lastPageUrl   = req + String.valueOf(lastPage);

        // montagem
        StringBuilder sb = new StringBuilder();
        //sb.append("<div class=\"pagination pagination-sm\">");
        //sb.append("<ul>");

        /*
        <ul class="pagination">
            <li><a href="#"><i class='fa fa-fast-backward'></i></a></li>
            <li><a href="#"><i class='fa fa-step-backward'></i></a></li>
            <li><a href="#">Página 1 de 1 (4 Registro...)</a></li>
            <li><a href="#"><i class='fa fa-step-forward'></i></a></li>
            <li><a href="#"><i class='fa fa-fast-forward'></i></a></li>
        </ul>
        * */


        sb.append("<ul class=\"pagination pagination-sm\">");

        // primeira página
        if (actualPage == 0) {
            sb.append("<li class='disabled'><a href='#'><i class='").append(FIRST_ICON).append("'></i></a></li>");
            sb.append("<li class='disabled'><a href='#'><i class='").append(PRIOR_ICON).append("'></i></a></li>");
        } else {
            sb.append("<li><a href='").append(firstPageUrl).append("'><i class='").append(FIRST_ICON).append("'></i></a></li>");
            sb.append("<li><a href='").append(priorPageUrl).append("'><i class='").append(PRIOR_ICON).append("'></i></a></li>");
        }

        // meio
        sb.append("<li class='pagination'><a href='#'>Página ").append(actualPage + 1).append(" de ").append(lastPage + 1).append(" (").append(pagina.getTotal()).append(" Registros)</a></li>");

        // última página
        if (actualPage == lastPage) {
            sb.append("<li class='disabled'><a href='#'><i class='").append(NEXT_ICON).append("'></i></a></li>");
            sb.append("<li class='disabled'><a href='#'><i class='").append(LAST_ICON).append("'></i></a></li>");
        } else {
            sb.append("<li><a href='").append(nextPageUrl).append("'><i class='").append(NEXT_ICON).append("'></i></a></li>");
            sb.append("<li><a href='").append(lastPageUrl).append("'><i class='").append(LAST_ICON).append("'></i></a></li>");
        }

        sb.append("</ul>");
        //sb.append("</div>");

        out.println(sb.toString());
    }

}