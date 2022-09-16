<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="beans.Aluno"%>
<%@page import="beans.LoginBean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="1" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Motoristas" />
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
        <title>Registrar Escola</title>
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
                
                <form class="form-horizontal" action="Alunos?action=nova-escola" method="POST">
                    <fieldset>
                        <legend>Escola</legend>
                        
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="nome">Nome:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" maxlength="50" name="nome" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="cnh">Endereço:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" maxlength="65" name="endereco" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="telefone">Telefone:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control telefone" name="telefone" >
                            </div>
                        </div>
                    </fieldset>
                
                    
                    <div class="form-group"> 
                      <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default">Adicionar</button>
                        <a href="Motoristas" class="btn btn-default">Cancelar</a>
                      </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
$(".telefone").mask("(00) 0000-0000");
$(".num").mask("0#");
    
</script>

