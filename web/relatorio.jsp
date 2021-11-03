<%-- 
    Document   : clientesListar
    Created on : 11/09/2018, 16:49:37
    Author     : julio
--%>

<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Aluno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Relatorios" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Relatórios</title>
        <style>
            
        </style>
            
    </head>
    <body>
        <nav class="navbar navbar-inverse" style="border-radius: 0">
            <div class="container-fluid">
              <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span> 
                </button>
                
              </div>
              <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                  <li><a href="Alunos">Alunos</a></li>
                  <li><a href="Pagamentos">Pagamentos</a></li>
                  <li><a href="Dispesas">Dispesas</a></li>
                  <li><a href="Veiculos">Veiculos</a></li>
                  <li class="active"><a href="Relatorios">Relatórios</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
              </div>
            </div>
        </nav>
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
                <form class="form-inline" action="Relatorios">
                  <div class="form-group">
                    <label for="tipo">Tipo:</label>
                      <select class="form-control" name="tipo">
                          <option value="alunos">Alunos</option>
                          <option value="pagamentos">Pagamentos</option>
                          <option value="abastecimento">Abastecimentos</option>
                        <option value="rodagem">Rodagem</option>
                      </select>
                  </div>
                  <div class="form-group">
                    <label for="data_inicio">De:</label>
                    <input type="date" class="form-control" name="data_inicio">
                  </div>
                    <div class="form-group">
                    <label for="data_fim">Até:</label>
                    <input type="date" class="form-control" name="data_fim">
                  </div>
                  <button type="submit" class="btn btn-default">Gerar</button>
                </form>
            </div>
        </div>
        
    </body>
</html>
