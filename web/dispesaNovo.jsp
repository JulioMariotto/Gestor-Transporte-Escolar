<%-- 
    Document   : dispesaNovo
    Created on : 11/01/2021, 18:05:32
    Author     : julio
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="beans.Dispesa"%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="3" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Saidas" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Registrar Dispesa</title>
        <style>
            textArea {
                width: 100%;
                height: 70px;
                resize: none;
            }
        </style>
    </head>
    <body>
       
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
            
                <form class="form-horizontal" action="Saidas?action=novo" method="POST">
                    
                    <fieldset>
                        <legend>Registrar Dispesa</legend>
                        
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="tipo">Tipo:</label>
                          <div class="col-sm-10">
                              <select class="form-control" id="opcao" name="tipo" onchange="mudaTipoDispesa(this.value)" required>
                                <option value="abastecimento">Abastecimento</option>
                                <option value="manutencao">Manutenção</option>
                                <option value="outras">Outras</option>
                            </select>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="valor">Valor:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control money" name="valor" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="data">Data:</label>
                          <div class="col-sm-10">
                              <input type="date" name="data" class="form-control" required value="<fmt:formatDate value="${data}"pattern="yyyy-MM-dd" />">
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="descricao">Descrição:</label>
                          <div class="col-sm-10">
                              <textarea name="descricao" maxlength="120" required></textarea>
                          </div>
                        </div>
                        <div class="form-group manutencao abastecimento">
                          <label class="control-label col-sm-2" for="veiculos">Veiculo:</label>
                          <div class="col-sm-10">
                              <select class="form-control" id="sel_veiculo" name="id_veiculo" required>
                                <c:forEach items="${veiculos}" var="v">           
                                  <option value="${v.id}">${v.modelo} - ${v.numero}</option>
                                </c:forEach>
                              </select>
                          </div>
                        </div>
                        <div class="form-group manutencao abastecimento">
                          <label class="control-label col-sm-2" for="km">Kilometragem:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control numero" name="kilometragem">
                          </div>
                        </div>
                        <div class="form-group abastecimento">
                          <label class="control-label col-sm-2" for="litros">Litros:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control litros" name="litros" maxlength="5">
                          </div>
                        </div>
                        <div class="form-group abastecimento">
                          <label class="control-label col-sm-2" for="posto">Posto:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" name="posto" maxlength="30">
                          </div>
                        </div>
                        <div class="form-group manutencao">
                          <label class="control-label col-sm-2" for="problema">Problema:</label>
                          <div class="col-sm-10">
                              <textarea  name="problema" maxlength="120"></textarea>
                          </div>
                        </div>
                          
                    </fieldset>
                          
                    <div class="form-group"> 
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Registrar</button>
                        <a href="Saidas" class="btn btn-default">Cancelar</a>
                      </div>
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
    $(".numero").mask("00000000000");
    $(".money").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
    $(".litros").mask("000.00", {reverse: true});
    
    function mudaTipoDispesa(value){
        $(".manutencao, .abastecimento").hide();
        $("." + value).show();                
    }
    
    mudaTipoDispesa("abastecimento");
</script>