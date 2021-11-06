<%-- 
    Document   : pagamentosListar
    Created on : 17/09/2018, 15:59:57
    Author     : julio
--%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.text.NumberFormat"%>
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
<c:set var="marcador" value="2" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Entradas" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="https://kit.fontawesome.com/75756ada57.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        
        <title>Entradas</title>
        <style>
            .spinner{
                width: 80%;text-align:center;
                margin-top: 50px;
                position: fixed
            }
            .tabela {
                /*max-height: 370px;
                overflow: auto;*/
            }
            .icones-table {
                float: left;
                width: 33.3%;
                font-size: 20px;
            }
            .azul {
                color: #2196f3;
            }
            .verde {
                color: #4caf50;
            }
            .vermelho, .vermelho:active, .vermelho:hover, .vermelho:focus {
                color: #f44336;
            }
            form {
                padding-left: 0;
                padding-top: 10px;
            }
            .btn-left {
                float: left;
                margin-right: 1%;
            }
            .btn-right {
                float: right;
                margin-left: 1%;
            }
            .col-sm-label {
                width: 2%;
                padding-top: 6px;
                float: left;
            }
            .filtros {
                padding: 15px 0;
               
            }
            .btn-filtro {
                
            }
        </style>
            
    </head>
    <body>
        
        <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            
            <h2 id="titulo">Pagamentos</h2>
            <p>Total: <strong id="valor_total"><fmt:formatNumber type="currency" value="${total}" /></strong></p>
            <form action="Entradas?action=filtro" method="POST">
                <div class="container">
                    <a href="Entradas?action=form-novo"><button type="button" class="btn btn-success  btn-left">Registrar Pagamento </button></a>
                    <button type="button" class="btn btn-danger  btn-right" onclick="listarPagamentos('ano'  )">Total deste Ano     </button>
                    <button type="button" class="btn btn-warning btn-right" onclick="listarPagamentos('mes'  )">Pagamentos deste Mês</button>
                    <button type="button" class="btn btn-primary btn-right" onclick="listarPagamentos('todos')">Todos os Pagamentos </button>
                </div>
                <div class="form-group filtros">
                    <label class="control-label col-sm-label">De: </label>
                    <div class="col-sm-2">
                        <input type="date" class="form-control" name="data_inicio" id="inicio">
                    </div>
                    <label class="col-sm-label">Até: </label>
                    <div class="col-sm-2">
                        <input type="date" class="form-control" name="data_fim" id="fim">
                    </div>
                    
                    <a class="btn btn-filto btn-default" onclick="listarPagamentos('datas')">
                        <span class="glyphicon glyphicon-search"></span> Filtrar 
                    </a>
                    
                </div>
                
            </form>
            <div class="tabela">    
            <table class="table">
              <thead>
                <tr>
                  <th>Aluno</th>
                  <th>Valor Pago</th>
                  <th>Mês de Referência</th>
                  <th>Data do Pagamento</th>
                  
                </tr>
              </thead>
              <tbody id="lista">
                  <c:forEach items="${pagamentos}" var="p">           
                            <tr>
                              <td>${p.aluno.nome}</td>
                              <td><fmt:formatNumber type="currency" value="${p.valor_pago}" /></td>
                              <td>${p.mes_ref}</td>
                              <td>${fn:substring(p.data, 8, 10)}/${fn:substring(p.data, 5, 7)}/${fn:substring(p.data, 0, 4)}</td>
                              <td><a class="icones-table vermelho" href="Entradas?action=delete&id=${p.id}" title="Remover" onclick="return confirm('Tem certeza que deseja excluir este pagamento?')"><span class="far fa-trash-alt"></span></a></td>
                            </tr>
                </c:forEach>                         
              </tbody>
            </table>
            </div>
        </div>
        
    </body>
</html>

<script>
    
    function listarPagamentos(value){
        
        $("#lista").empty();
        $("#valor_total").html('<i class="fas fa-circle-notch fa-spin fa-fw"></i><span class="sr-only"></span>');        
        $("#lista").prepend('<div class="spinner"><i class="fas fa-circle-notch fa-spin fa-3x fa-fw"></i><span class="sr-only"></span></div>');
        
        var data = new Date();
        var ano = data.getFullYear();
        var mes = data.getMonth() + 1;
        var ultimo_dia_mes = new Date(ano, mes, 0).getDate();
        var inicio, fim;
        if((mes) < 10)
            mes = "0" + mes;
        switch(value){ 
            
            case "mes":
                inicio = ano + "-" + mes + "-01";
                fim = ano + "-" + mes + "-31";
                $("#titulo").text("Pagamentos entre 01/" + mes + "/" + ano + " e " + ultimo_dia_mes + "/" + mes + "/" + ano);
                break;
            case "ano":
                $("#titulo").text("Pagamentos de " + ano);
                inicio = ano + "-01-01";
                fim = ano + "-12-31";
                break;
            case "todos":
                $("#titulo").text("Pagamentos");
                inicio = "2000-01-01";
                fim = ano + "-" + mes + "-" + data.getDate();
                break;
            case "datas":
                inicio = $("#inicio").val();
                fim = $("#fim").val();
                if(inicio === ""){
                    inicio = "2000-01-01";
                    if(fim === ""){
                        fim = ano + "-" + mes + "-" + data.getDate();
                        $("#titulo").text("Pagamentos");
                    }
                    else{
                        $("#titulo").text("Pagamentos até " + fim.substring(8, 10) + "/" + fim.substring(5, 7) + "/" + fim.substring(0, 4));
                    }
                }
                else{
                    if(fim === ""){
                        fim = ano + "-" + mes + "-" + data.getDate();
                    }
                    $("#titulo").text("Pagamentos entre " + inicio.substring(8, 10) + "/" + inicio.substring(5, 7) + "/" + inicio.substring(0, 4) + " e " + fim.substring(8, 10) + "/" + fim.substring(5, 7) + "/" + fim.substring(0, 4));
                }
                break;
            default:
                inicio = "2000-01-01";
                fim = ano + "-" + mes + "-" + data.getDate();
                $("#titulo").text("Pagamentos");
                break;
        }
               
        var formData = {
            'inicio'              : inicio,
            'fim'                 : fim
        };

        var request = $.ajax({
            type        : 'POST',
            url         : 'ListaPagamentosAJAX',
            data        : formData,
            dataType    : 'json',
            encode      : true
        });
                        
        request.done(function(resposta){

            var resposta = resposta;
            var total = 0;
            var element = "";
            for(i in resposta){
                element += '<tr>';
                element += '<td>' + resposta[i].aluno.nome +'</td>';
                element += '<td>' + resposta[i].valor_pago.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '</td>';
                element += '<td>' + resposta[i].mes_ref + '</td>';
                element += '<td>' + resposta[i].data.substring(8, 10) + '/' + resposta[i].data.substring(5, 7) + '/' + resposta[i].data.substring(0, 4) +'</td>';
                element += '<td><a class="icones-table vermelho" href="Entradas?action=delete&id=' + resposta[i].id + '" title="Remover"  onclick="return confirm(\'Tem certeza que deseja excluir este pagamento?\')"><span class="far fa-trash-alt"></span></a></td>';
                element += '</tr>';
                total = total + resposta[i].valor_pago;
            }
            $("#lista").empty();
            $("#valor_total").text(total.toLocaleString("pt-br",{style: "currency", currency: "BRL"}));
            $("#lista").prepend(element);   

        });

        request.fail(function(jqXHR, textStatus) {
            console.log("Request failed: " + textStatus);
        });

        
    }
    
</script>

