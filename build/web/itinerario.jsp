<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Aluno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="1" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Alunos" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Itinerario</title>
        <style>
            
            .jumbotron {
                float: left;
                height: 240px;
            }
            .col-md-12 {
                width: 100%;
                padding-bottom: 30px;
            }
        
            
        </style>
    </head>
    <body>
        
         <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
                <div class="col-md-12">
                    <h2>ITINERÁRIO</h2>
                    <form class="form-inline" action="Alunos?action=itinerario" method="post">
                      <div class="form-group">
                            <label for="veiculo">Selecione o veículo:</label>
                            <select class="form-control" name="veiculo" required>
                                <c:forEach items="${veiculos}" var="x"> 
                                    <option value="${x.id}">${x.modelo}</option>
                                </c:forEach>
                            </select>
                        </div>
                      
                      <button type="submit" class="btn btn-default">Gerar Itinerário</button>
                    </form>
                </div>
                <div class="col-md-12">
                    <div class="col-md-12">
                        <div id="grafico_balanco" style="width: 1000px; height: 200px;"></div>
                    </div>
                    <div class="col-md-12">
                        <div id="grafico_alunos" style="width: 1000px; height: 200px;"></div>
                    </div>
                    <div class="col-md-12">
                        <div id="grafico_km" style="width: 1000px; height: 200px;"></div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>