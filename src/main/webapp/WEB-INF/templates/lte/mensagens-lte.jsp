<!-- Modal de Pesquisa de Pessoa -->
<div id="MensagemErro" class="modal modal-danger fade" aria-hidden="true" style="width: auto">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Erro</h3>
            </div>
            <div class="modal-body">
                <p>${mensagem.mensagem}</p>
            </div>
            <div class="modal-footer">
                <button class="btn btn-outline pull-right" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal de Pesquisa de Pessoa -->

<div id="MensagemSucesso" class="modal modal-success fade" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Sucesso</h3>
            </div>
            <div class="modal-body">
                <p>${mensagem.mensagem}</p>
            </div>
            <div class="modal-footer" align="center">
                <button class="btn btn-outline pull-right" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </div>
</div>


<script>
    $(window).load(function() {

        if (${mensagem != null}){

            if (${mensagem.tipo == 0}){
                $("#MensagemSucesso").modal('show');
            }
            if (${mensagem.tipo == 1}){
                alert("${mensagem.mensagem}");
                $("#MensagemErro").modal('show');
            }
        }
    });
</script>