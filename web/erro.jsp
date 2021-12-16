<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="beans.Aluno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>
<c:set var="marcador" value="7" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/75756ada57.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Algo deu errado</title>
        <style>
            .erro {
                    text-align: center;
                    font-size: 7em !important;
                    padding: 70px 0px;
            }
        </style>
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
         
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
            <a href="index.jsp" class="btn btn-default">Inicio</a>
            <h1 class="erro"><span><i class="fas fa-exclamation-triangle"></i></span> ERRO</h1>
            <p><strong>Mensagem: </strong>${msg}</p>
            <br><br>
            <p>Ao persistir o erro, favor entrar em contato com o administrador: <strong>${configuracao.emailAdm}</strong></p>
            </div>
            
        </div>
    </body>
</html>
