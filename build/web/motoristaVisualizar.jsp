<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Aluno"%>
<%@page import="beans.Pagamento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="5" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Motoristas?action=visualizar&id=${motorista.id}" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>${motorista.nome}</title>
        <style>
            .float-right {
                float: right;
                margin-right: 5px;
            }
            .tabela {
                max-height: 340px;
                overflow: auto;
            }
            .h2 {
                font-size: 26;
            }
        </style>
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
                <a href="Motoristas" class="btn btn-default">Voltar</a>
                <a href="Motoristas?action=delete&id_motorista=${motorista.id}" class="btn float-right btn-danger active" onclick="return confirm('Tem certeza que deseja excluir ${veiculo.modelo} - ${veiculo.numero}?')">Excluir</a>
                <a href="Motoristas?action=form-alterar&id_motorista=${motorista.id}" class="btn float-right btn-success active">Alterar</a>
                <h1>Motorista</h1>
                <br>
                <p><strong>Nome: </strong>${motorista.nome}</p>
                <p><strong>CNH: </strong>${motorista.cnh}</p>
                <p><strong>Endereço: </strong>${motorista.endereco}</p>
                <p><strong>Telefone: </strong>${motorista.telefone}</p>
                <p><strong>Veículo: </strong>${veiculo.numero} - ${veiculo.modelo}</p>
                
                
                
            </div>
            
        </div>
    </body>
</html>
<script>
    
    function confirmaDelete(txt) {
    
      if (confirm("Tem certeza que deseja excluir " + txt + "?")) {
          
      }       
    }
</script>