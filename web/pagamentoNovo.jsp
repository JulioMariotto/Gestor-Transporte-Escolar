<%-- 
    Document   : clientesNovo
    Created on : 12/09/2018, 17:15:21
    Author     : julio
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="beans.Aluno"%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="2" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Entradas?action=form-novo" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-maskmoney/3.0.2/jquery.maskMoney.min.js" type="text/javascript"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <title>Registrar Pagamento</title>
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
            
                <form class="form-horizontal" action="Entradas?action=novo" method="POST">
                    
                    <fieldset>
                        <legend>Registrar Pagamento</legend>
                    
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="aluno">Aluno:</label>
                            <div class="col-sm-10">
                                <select class="form-control" name="aluno" required>
                                  <c:forEach items="${alunos}" var="x"> 
                                  <option value="${x.getId()}">${x.getNome()}</option>
                                  </c:forEach>
                                  </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="mes">Mês de Referencia:</label>
                            <div class="col-sm-10">
                                <input type="month" value="<fmt:formatDate value="${data}" pattern="yyyy-MM" />" class="form-control" name="mes" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="data">Data do Pagamento:</label>
                            <div class="col-sm-10">
                                <input type="date" name="data" class="form-control" required value="<fmt:formatDate value="${data}"pattern="yyyy-MM-dd" />">
                            </div>
                          </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="valor">Valor:</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control money" min="0" name="valor" required>
                            </div>
                        </div>
                    </fieldset>
                <div class="form-group"> 
                  <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Registrar</button>
                    <a href="Entradas" class="btn btn-default">Cancelar</a>
                  </div>
                </div>
            </form>
            </div>
        </div>
    </body>
</html>
<script type="text/javascript">
    $(".money").maskMoney({prefix:'R$ ', allowNegative: true, thousands:'.', decimal:',', affixesStay: false});
</script>