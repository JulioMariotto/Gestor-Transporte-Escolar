<%-- 
    Document   : clientesVisualizar
    Created on : 12/09/2018, 09:08:29
    Author     : julio
--%>

<%@page import="beans.Aluno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Algo deu errado</title>
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
                  <li><a href="Relatorios">Relatórios</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
              </div>
            </div>
        </nav>
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
            <a href="index.jsp" class="btn btn-default">Inicio</a>
            <h1>Houve um Erro!!</h1>
            <p><strong>Mensagem: </strong>${msg}</p>
            <br><br>
            <p>Ao persistir o erro, favor entrar em contato com o administrador: <strong>${configuracao.emailAdm}</strong></p>
            </div>
            
        </div>
    </body>
</html>
