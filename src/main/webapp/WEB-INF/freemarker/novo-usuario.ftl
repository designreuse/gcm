

<!-- img src="http://sync.vialogicaservicos.com.br/downloads/logo-vls.png" alt="Via Logica" title="Logo Via Logica Sistemas" height="50" width="200"/ -->
<hr style="border: 0px; height: 0px;  border-top: 1px solid rgba(0, 0, 0, 0.1); border-bottom: 1px solid rgba(255, 255, 255, 0.3); ">
<p style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; color:#000000;">
    Olá <strong>${user.nome}</strong>, <br/>
    seu cadastro foi realizado com sucesso no nosso sistema,<br/>
    abaixo segue seus dados de acesso.<br/>
    Acesse nosso o link: <a href="http://www.gcm.com.br" target="_blank">http://www.gcm.com.br</a> <br/>
    para entrar no sistema.
</p>

<hr style="border: 0px; height: 0px;  border-top: 1px solid rgba(0, 0, 0, 0.1); border-bottom: 1px solid rgba(255, 255, 255, 0.3); ">

<span style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;" >Dados do seu Cadastro:</span>
<hr style="border: 0px; height: 0px;  border-top: 1px solid rgba(0, 0, 0, 0.1); border-bottom: 1px solid rgba(255, 255, 255, 0.3); ">
<table  border="0" cellpadding="1" cellspacing="1" style="font-family: Arial, Helvetica, sans-serif;">
    <tr>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">Nome:</td>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-style: italic;">${user.nome}</td>
    </tr>
    <tr>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">Login:</td>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-style: italic;">${user.login}</td>
    </tr>
    <tr>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-weight: bold;">Senha:</td>
        <td style="font-family: Arial, Helvetica, sans-serif; font-size: 12px; font-style: italic;">${user.senha}</td>
    </tr>

</table>
<hr style="border: 0px; height: 0px;  border-top: 1px solid rgba(0, 0, 0, 0.1); border-bottom: 1px solid rgba(255, 255, 255, 0.3); ">