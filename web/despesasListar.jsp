<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Despesa"%>
<%@page import="beans.Veiculo"%>
<%@page import="beans.Manutencao"%>
<%@page import="beans.Abastecimento"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="3" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Despesas" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://code.jquery.com/jquery-1.8.2.js"></script>
        <script src="https://kit.fontawesome.com/75756ada57.js" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Despesas</title>
        <style>
            .spinner{
                width: 80%;text-align:center;
                margin-top: 50px;
                position: fixed
            }
            .tabela {
                /*max-height: 540px;*/
                padding-bottom: 100px;
                overflow: auto;
            }
            .icones-table {
                float: right;
                margin-left: 12px;
                font-size: 20px;
            }
            .verde, .verde:active, .verde:hover, .verde:focus {
                color: #009688;
            }
            .vermelho, .vermelho:active, .vermelho:hover, .vermelho:focus {
                color: #f44336;
            }
            .especial-p {
                float: left;
                width: 100%;
                /*text-align: center;*/
            }
            form {
                padding-left: 0;
                padding-top: 10px;
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
            
            <h2 id="titulo">Despesas</h2>
            <p class="especial-p">Total: <strong id="valor_total"><fmt:formatNumber value="${total}" type="currency" /></strong></p>
            <form action="Despesas?action=filtro" method="POST">
                <div class="container">
                    <a href="Saidas?action=form-novo"><button type="button" class="btn btn-success btn-left">Registrar Despesa</button></a>
                    <button type="button" class="btn btn-danger  btn-right" onclick="listarDespesas('ano'  )">Despesas deste Ano</button>
                    <button type="button" class="btn btn-warning btn-right" onclick="listarDespesas('mes'  )">Despesas deste Mês</button>
                    <button type="button" class="btn btn-primary btn-right" onclick="listarDespesas('todos')">Todas as Despesas</button>                    
                    <a href="http://192.168.1.3:8080/Gestor%20Escolar/pdf/despesas.pdf" target="_blank" title="Imprimir Formulário"><button type="button" class="btn btn-info btn-right">Formulário de Despesas</button></a>
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
                    <div class="col-sm-2">
                        <select id="opcao" name="tipo" class="form-control">
                            <option value="todas">Todas</option>
                            <option value="abastecimento">Abastecimento</option>
                            <option value="manutencao">Manutenção</option>
                            <option value="outras">Outras</option>
                        </select>
                    </div>                    
                    <a class="btn btn-filto btn-default" onclick="listarDespesas('datas')">
                        <span class="glyphicon glyphicon-search"></span> Filtrar 
                    </a>                   
                </div>
                
                
            </form>
            <div class="tabela">
            <table class="table">
              <thead>
                <tr>
                  <th>Valor</th>
                  <th>Data</th>
                  <th>Descrição</th>
                  <th></th>
                </tr>
              </thead>
              <tbody id="lista">
                <c:forEach items="${despesas}" var="d">                         
                <tr>
                  <td><fmt:formatNumber type="currency" value="${d.valor}" /></td>
                  <td>${fn:substring(d.data, 8, 10)}/${fn:substring(d.data, 5, 7)}/${fn:substring(d.data, 0, 4)}</td>
                  <td>${d.descricao}</td>
                  <td>
                      <a class="icones-table verde" href="Saidas?action=visualizar&id_despesa=${d.id}" title="Visualizar"><span class="far fa-eye"></span></a>
                      <a class="icones-table vermelho" href="Saidas?action=delete&id_despesa=${d.id}" title="Remover" onclick="return confirm('Tem certeza que deseja excluir esta despesa?')"><span class="far fa-trash-alt"></span></a>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
            </div>
        </div>
        
    </body>
</html>

<script>
    
    function listarDespesas(value){
        
        $("#lista").empty();
        $("#valor_total").html('<i class="fas fa-circle-notch fa-spin fa-fw"></i><span class="sr-only"></span>');        
        $("#lista").prepend('<div class="spinner"><i class="fas fa-circle-notch fa-spin fa-3x fa-fw"></i><span class="sr-only"></span></div>');
        
        var tipo = $("#opcao").val();
        var texto = "";
        var data = new Date();
        var ano = data.getFullYear();
        var mes = data.getMonth() + 1;
        var ultimo_dia_mes = new Date(ano, mes, 0).getDate();
        var inicio, fim;
        if((mes) < 10)
            mes = "0" + mes;
        
        switch(tipo){
            case "abastecimento":
                texto = " (Abastecimentos)";
                break;
            case "manutencao":
                texto = " (Manutenção)";
                break;
            case "outras":
                texto = " (Outras Despesas)";
                break;
            default:
                texto = "";
                break;
        }
        
        switch(value){ 
            
            case "mes":
                inicio = ano + "-" + mes + "-01";
                fim = ano + "-" + mes + "-31";
                $("#titulo").text("Despesas entre 01/" + mes + "/" + ano + " e " + ultimo_dia_mes + "/" + mes + "/" + ano + texto);
                break;
            case "ano":
                $("#titulo").text("Despesas de " + ano + texto);
                inicio = ano + "-01-01";
                fim = ano + "-12-31";
                
                break;
            case "todos":
                $("#titulo").text("Despesas" + texto);
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
                        $("#titulo").text("Despesas" + texto);
                    }
                    else{
                        $("#titulo").text("Despesas até " + fim.substring(8, 10) + "/" + fim.substring(5, 7) + "/" + fim.substring(0, 4) + texto);
                    }
                }
                else{
                    if(fim === ""){
                        fim = ano + "-" + mes + "-" + data.getDate();
                    }
                    $("#titulo").text("Despesas entre " + inicio.substring(8, 10) + "/" + inicio.substring(5, 7) + "/" + inicio.substring(0, 4) + " e " + fim.substring(8, 10) + "/" + fim.substring(5, 7) + "/" + fim.substring(0, 4) + texto);
                }
                break;
            default:
                inicio = "2000-01-01";
                fim = ano + "-" + mes + "-" + data.getDate();
                $("#titulo").text("Despesas");
                break;
        }
        
               
        var formData = {
            'inicio'              : inicio,
            'fim'                 : fim,
            'tipo'                : tipo
        };

        var request = $.ajax({
            type        : 'POST',
            url         : 'ListaDespesasAJAX',
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
                element += '<td>' + resposta[i].valor.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '</td>';
                element += '<td>' + resposta[i].data.substring(8, 10) + '/' + resposta[i].data.substring(5, 7) + '/' + resposta[i].data.substring(0, 4) +'</td>';
                element += '<td>' + resposta[i].descricao +'</td>';
                element += '<td>\n\
                                    <a class="icones-table verde" href="Saidas?action=visualizar&id_despesa=' + resposta[i].id + '" title="Visualizar"><span class="far fa-eye"></span></a>\n\
                                    <a class="icones-table vermelho" href="Saidas?action=delete&id_despesa=' + resposta[i].id + '" title="Remover"  onclick="return confirm(\'Tem certeza que deseja excluir esta despesa?\')"><span class="far fa-trash-alt"></span></a>\n\
                            </td>';
                element += '</tr>';
                total = total + resposta[i].valor;
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

