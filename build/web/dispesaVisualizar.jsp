<%-- 
    Document   : clientesVisualizar
    Created on : 12/09/2018, 09:08:29
    Author     : julio
--%>
<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Dispesa"%>
<%@page import="beans.Pagamento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="3" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Saidas?action=visualizar&id=${dispesa.id}" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Dispesa</title>
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
                <a href="Saidas" class="btn btn-default">Voltar</a>
                <a href="Saidas?action=delete&id_dispesa=${dispesa.id}" class="btn float-right btn-danger active" onclick="return confirm('Tem certeza que deseja excluir esta dispesa?')">Excluir</a>

                <c:if test="${abastecimento != null}" >
                    <h1><strong>Abastecimento</strong></h1>
                </c:if>
                <c:if test="${manutencao != null}" >
                    <h1><strong>Manutenção</strong></h1>
                </c:if>
                <c:if test="${manutencao == null && abastecimento == null}" >
                    <h1><strong>Dispesa</strong></h1>
                </c:if>
                    
                <br><br>
                
                <c:if test="${abastecimento != null}" >
                    
                    <p><strong>Veiculo: </strong>${abastecimento.veiculo.modelo} - ${abastecimento.veiculo.numero}</p>
                </c:if>
                    
                    <p><strong>Valor: </strong><fmt:formatNumber type="currency" value="${dispesa.valor}" /></p>
                    
                <c:if test="${abastecimento != null}" >
                    
                    <p><strong>Litros: </strong>${abastecimento.litros}</p>
                    
                    <p><strong>Kilometragem: </strong>${abastecimento.kilometragem}</p>
                    
                    <p><strong>Posto: </strong>${abastecimento.posto}</p>
                </c:if>
                    
                    <p><strong>Data: </strong>${fn:substring(dispesa.data, 8, 10)}/${fn:substring(dispesa.data, 5, 7)}/${fn:substring(dispesa.data, 0, 4)}</p>
                    
                    <p><strong>Descrição: </strong>${dispesa.descricao}</p>
                
                <c:if test="${manutencao != null}" >
                    
                    <p><strong>Problema: </strong>${manutencao.problema}</p>
                    
                    <p><strong>Kilometragem: </strong>${manutencao.kilometragem}</p>
                    
                </c:if>
                
                
                
                
            </div>
            
        </div>
    </body>
</html>
