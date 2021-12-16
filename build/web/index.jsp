<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:if test="${login != null}">
   <jsp:forward page="/Alunos" />
</c:if>
 
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            .imagem {
                width: 100%;
                height: auto;
                padding: 10px;
            }
            
            .titulo {
                text-align: center;
                padding-top: 5%;
            }
            
        </style>
    </head>
    <body style="font-family: sans-serif; font-size: 18px">
        <c:if test="${(!empty msg)}" >
            <div class="alert alert-danger alert-dismissible text-center" style="position: absolute; top: 0; width: 100%;">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            ${msg}
        </div>
        </c:if>
        <h2 class="titulo">SISTEMA DE GESTÃO PARA TRANSPORTADORES ESCOLARES</h2>
        <div style="text-align: center; width: 30%; margin-left: 35%">
            
            <img src="http://localhost:46455/Gestor%20Escolar/Images/logo.png" alt="SISTEMA DE GESTÃO PARA TRANSPORTADORES ESCOLARES" class="imagem"/>
            <form action="Login" method="Post">
                <div class="form-group">
                    <label><b>Login:</b></label>
                    <input type="text" name="login" class="form-control">
                </div>
                <div class="form-group">
                    <label><b>Senha</b></label>
                    <input type="password" name="senha" class="form-control">    
                </div>
                <input type="hidden" name="red" value="${red}">
                <input type="submit" value="Entrar" class="btn btn-default">
            </form>
        </div>
        <div class="col-md-12" style="position: fixed; bottom: 0; margin: 0; padding: 0; text-align: center">
                    <p id="rodape_contato"></p>
        </div>
    </body>
</html>
<script>
    $("#rodape_contato").text(new Date().getFullYear());
</script>
