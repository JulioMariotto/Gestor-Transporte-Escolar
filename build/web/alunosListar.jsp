<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
        <script src="https://kit.fontawesome.com/75756ada57.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Alunos</title>
        <style>
            
            .spinner{
                width: 80%;text-align:center;
                margin-top: 50px;
                position: fixed
            }
            .tabela {
                max-height: 340px;
                float: left;
                overflow: auto;
                width: 100%;
                
            }
            .btn:focus {
                outline: none;
                outline-offset: inherit;
            }
            tbody {
                
            }
            .info{
                
            }
            .float-right{
                float: right;
                margin-left: 5px;
            }
            .filtro {
                
            }
            .input-filtro, .input-filtro:focus {
                width: 100%;
                outline: none;
                outline-offset: inherit;
            }
            .icones-table {
                float: left;
                width: 33.3%;
                font-size: 20px;
            }
            .pago {
                padding: 0px 5px;
                color: #1cdf1c;
            }
            .aberto {
                padding: 0px 5px;
                color: #ff0000;
            }
            .verde, .verde:active, .verde:hover, .verde:focus {
                color: #009688;
            }
            .especial-p {
                float: left;
                width: 25%;
                /*text-align: center;*/
            }
        </style>
            
    </head>
    <body>
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            
            <div class="header">
                <div class="col-md-12">
                    <h2>Alunos</h2>
                    <p class="especial-p">Total: <strong>${fn:length(alunos)} Alunos</strong></p>
                    <p class="especial-p">Total à receber:<strong><fmt:formatNumber value="${totalReceber}" type="currency" /></strong></p>
                    <p class="especial-p">Já recebido:<strong><fmt:formatNumber value="${totalRecebidoMes}" type="currency" /></strong></p>
                    <p class="especial-p">Ainda à receber:<strong><fmt:formatNumber value="${totalReceber - totalRecebidoMes}" type="currency" /></strong></p>
                </div>
                <div class="botoes col-md-12">
                    <div class="col-md-4">
                        <a href="Alunos?action=form-novo" title="Registrar Aluno"><button type="button" class="btn btn-success">Novo Aluno</button></a>
                        <a href="Alunos?action=form-itinerario" title="Ver Itinerário"><button type="button" class="btn btn-danger">Itinerário</button></a>
                    </div>
                    <div class="col-md-4">
                        <div class="filtro"><input type="text" placeholder="Digite aqui para buscar um Aluno" class="input-filtro" onkeyup="filtraAlunos(this.value)"></div>
                    </div>
                    <div class="col-md-4">
                        <button type="button" class="btn btn-primary float-right" title="Ver Alunos Ativos" id="btn-ativos" onclick="listaAlunos('ativos')">Alunos Ativos</button>
                        <button type="button" class="btn btn-default float-right" title="Ver Todos os Alunos" id="btn-todos" onclick="listaAlunos('todos')">Todos os Alunos</button>
                    </div>
                </div>
                
            </div>
            <div class="tabela">
                <table class="table">
                  <thead>
                      <tr>
                      <th>Nome</th>
                      <th>Telefone</th>
                      <th>Escola</th>
                      <th>Período</th>
                      <th>Veículo</th>
                      <th>Mensalidade</th>
                      <!--<th>Ação</th>-->
                    </tr>
                  </thead>
                  <tbody id="lista">
                    <c:forEach items="${alunos}" var="a">                         
                    <tr>
                      <td>${a.nome}</td>
                      <td>${a.telefone}</td>
                      <td>${a.escola.nome}</td>
                      <td>${a.periodo}</td>
                      <td>${a.veiculo.numero}</td>
                      <td 
                          <c:if test="${a.status == 2}" >
                              title="Mensalidade do mês paga"
                          </c:if>
                          <c:if test="${a.status == 3}" >
                              title="Mensalidade do em aberto"
                          </c:if>
                              >${a.getMensalidadeFormated()}
                          
                          <c:if test="${a.status == 2}" >
                              <i class="fas fa-check pago"></i>
                          </c:if>
                          <c:if test="${a.status == 3}" >
                              <i class="fas fa-times aberto"></i>
                          </c:if>
                      </td>
                      <td><a class="icones-table verde" href="Alunos?action=visualizar&id=${a.id}" title="Visualizar"><span class="far fa-eye"></span></a></td>
                    </tr>
                    </c:forEach>
                  </tbody>
                </table>
            </div>
        </div>
    </body>
</html>

<script>
  
  function listaAlunos(value){
        
        $("#lista").empty();
                
        $("#lista").prepend('<div class="spinner"><i class="fas fa-circle-notch fa-spin fa-3x fa-fw"></i><span class="sr-only"></span></div>');
        
        if(value === "todos"){
            $("#btn-todos").removeClass("btn-default");
            $("#btn-todos").addClass("btn-primary");
            $("#btn-ativos").removeClass("btn-primary");
            $("#btn-ativos").addClass("btn-default");
        }
        else{
            $("#btn-todos").addClass("btn-default");
            $("#btn-todos").removeClass("btn-primary");
            $("#btn-ativos").addClass("btn-primary");
            $("#btn-ativos").removeClass("btn-default");
            
        }
        
        
        var formData = {
            'filtro'              : value
        };

        var request = $.ajax({
            type        : 'POST',
            url         : 'ListaAlunosAJAX',
            data        : formData,
            dataType    : 'json',
            encode      : true
        });
                        
        request.done(function(resposta){

            var resposta = resposta;
            var element = "";
            for(i in resposta){
                
                
                element += '<tr>';
                element += '<td>'+ resposta[i].nome +'</td>';
                element += '<td>' + resposta[i].telefone + '</td>';
                element += '<td>' + resposta[i].escola.nome + '</td>';
                element += '<td>' + resposta[i].periodo + '</td>';
                element += '<td>' + resposta[i].vencimento + '</td>';
                if(resposta[i].status === 2){
                    element += '<td  title="Mensalidade do mês paga">' + resposta[i].mensalidade.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '<i class="fas fa-check pago"></i></td>';
                }
                else if(resposta[i].status === 3){
                    element += '<td  title="Mensalidade do mês em aberto">' + resposta[i].mensalidade.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '<i class="fas fa-times aberto"></i></td>';
                }
                else{
                    element += '<td>' + resposta[i].mensalidade.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '</td>';
                }
                element += '<td><a class="icones-table verde" href="Alunos?action=visualizar&id=' + resposta[i].id + '" title="Visualizar"><span class="far fa-eye"></span></a></td>';
                element += '</tr>';  
            }
            $("#lista").empty();
            $("#lista").prepend(element);   

        });

        request.fail(function(jqXHR, textStatus) {
            console.log("Request failed: " + textStatus);
            });


    }
    
    function filtraAlunos(value){
        
        $("#lista").empty();
                
        $("#lista").prepend('<div class="spinner"><i class="fas fa-circle-notch fa-spin fa-3x fa-fw"></i><span class="sr-only"></span></div>');
        
        
        var formData = {
            'filtro'              : value
        };

        var request = $.ajax({
            type        : 'POST', 
            url         : 'FiltraAlunosAJAX',
            data        : formData, 
            dataType    : 'json',
            encode      : true
        });
        
        request.done(function(resposta){

            var resposta = resposta;
            var element = "";
            for(i in resposta){
                
                
                element += '<tr>';
                element += '<td>'+ resposta[i].nome +'</td>';
                element += '<td>' + resposta[i].telefone + '</td>';
                element += '<td>' + resposta[i].escola.nome + '</td>';
                element += '<td>' + resposta[i].periodo + '</td>';
                element += '<td>' + resposta[i].vencimento + '</td>';
                element += '<td>' + resposta[i].mensalidade.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '</td>';
                element += '<td><a class="icones-table verde" href="Alunos?action=visualizar&id=' + resposta[i].id + '" title="Visualizar"><span class="far fa-eye"></span></a></td>';
                element += '</tr>';  
            }
            $("#lista").empty();
            $("#lista").prepend(element);   

        });

        request.fail(function(jqXHR, textStatus) {
            console.log("Request failed: " + textStatus);
            });


    }
  
</script>

