package com.br.gcm.tag;

import net.tanesha.recaptcha.ReCaptcha;
import net.tanesha.recaptcha.ReCaptchaFactory;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Properties;

public class RecaptchaTag extends SimpleTagSupport {

    private String publicKey;
    private String privateKey;
    private String themeName;

    @Override
    public void doTag() throws JspException, IOException {
        ReCaptcha captcha = ReCaptchaFactory.newReCaptcha(publicKey, privateKey, false);
        Properties props = new Properties();
        if (StringUtils.isNotEmpty(themeName)) {
            props.put("theme", themeName);
        }
        String html = captcha.createRecaptchaHtml(null, props);
        getJspContext().getOut().write(html);
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

}