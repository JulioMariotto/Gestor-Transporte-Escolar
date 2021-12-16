<%@page import="beans.Veiculo"%>
<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Abastecimento"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="beans.Aluno"%>
<%@page import="daos.AlunoDAO"%>
<%@page import="beans.Pagamento"%>
<%@page import="java.util.List"%>
<%@page import="beans.LoginBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="5" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Motoristas" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://kit.fontawesome.com/75756ada57.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Motoristas</title>
        <style>
            .tabela {
                /*max-height: 370px;
                overflow: auto;*/
            }
            .icones-table {
                float: left;
                margin-left: 12px;
                font-size: 20px;
            }
            .azul {
                color: #2196f3;
            }
            .verde, .verde:active, .verde:hover, .verde:focus {
                color: #009688;
            }
            .vermelho, .vermelho:active, .vermelho:hover, .vermelho:focus {
                color: #f44336;
            }
        </style>
            
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            
            <h2>Motoristas</h2>
            <a href="Motoristas?action=form-novo"><button type="button" class="btn btn-success">Registrar Motorista</button></a>
            <br><br>
            <div class="tabela">
            <table class="table" style="max-height: 200px; overflow: auto">
              <thead>
                <tr>
                  <th>Nome</th>
                  <th>CNH</th>
                  <th>Endereço</th>
                  <th>Telefone</th>
                 </tr>
              </thead>
              <tbody>
                  <c:forEach items="${motoristas}" var="m">
                    <tr>
                      <td>${m.nome}</td>
                      <td>${m.cnh}</td>
                      <td>${m.endereco}</td>
                      <td>${m.telefone}</td>
                      <td>
                          <a class="icones-table vermelho" href="Motoristas?action=delete&id_motorista=${m.id}" title="Remover" onclick="return confirm('Tem certeza que deseja excluir o motorista ${m.nome}?')"><span class="far fa-trash-alt"></span></a>
                          <a class="icones-table verde" href="Motoristas?action=visualizar&id=${m.id}" title="Visualizar"><span class="far fa-eye"></span></a>
                      </td>
                    </tr>
                  </c:forEach>
              </tbody>
            </table>
            </div>
        </div>
        
    </body>
</html>

