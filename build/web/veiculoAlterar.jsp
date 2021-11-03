<%-- 
    Document   : clientesAlterar
    Created on : 12/09/2018, 15:09:25
    Author     : julio
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Veiculo"%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="4" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Alunos" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>${veiculo.numero} - ${veiculo.modelo}<</title>
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
         
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
            
                <form class="form-horizontal" action="Veiculos?action=alterar&id_veiculo=${veiculo.id}" method="POST">
                    <fieldset>
                    <legend>Veículo</legend>
                    
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="modelo">Marca/Modelo:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control"  name="modelo" maxlength="45" value="${veiculo.modelo}" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="cor">Cor:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control"  name="cor" maxlength="15" value="${veiculo.cor}" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="numero">Número:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control"  name="numero" maxlength="5" value="${veiculo.numero}" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="placa">Placa:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control placa"  name="placa" maxlength="9" value="${veiculo.placa}" required>
                          </div>
                        </div>
                        <div class="form-group">
                          <label class="control-label col-sm-2" for="licenca">Nº da Licença:</label>
                          <div class="col-sm-10">
                              <input type="text" class="form-control" maxlength="10" name="licenca" value="${veiculo.licenca}" >
                          </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="capacidade">Capacidade de Passageiros:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control num" maxlength="3" name="capacidade" value="${veiculo.capacidade}" >
                          </div>
                        </div>
                    
                     <div class="form-group">
                            <label class="control-label col-sm-2" for="motorista">Motorista:</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="motorista" required>
                                  <c:forEach items="${motoristas}" var="m"> 
                                      <c:if test="${m.id == veiculo.motorista.id}">
                                          <option value="${m.id}" selected>${m.nome}</option>
                                      </c:if>
                                      <c:if test="${m.id != veiculo.motorista.id}">
                                        <option value="${m.id}">${m.nome}</option>
                                      </c:if>
                                  </c:forEach>
                                  </select>
                            </div>
                        </div>
                    </fieldset>
                    
                    <div class="form-group"> 
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Alterar</button>
                        <a href="Veiculos" class="btn btn-default">Cancelar</a>
                      </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
$(".placa").mask('SSS-YAYY', {'translation': {
    A: {pattern: /[A-Z0-9]/},
    S: {pattern: /[A-Z]/},
    Y: {pattern: /[0-9]/}
  }
});
$(".telefone").mask("(00) 00000-0000");
$(".num").mask("0#");
    
</script>
