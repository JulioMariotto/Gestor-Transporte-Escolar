<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.NumberFormat"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="beans.Aluno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="beans.LoginBean"%>
<!DOCTYPE html>

<c:set var="login" value="${sessionScope.usuario}" />
<c:set var="marcador" value="6" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Relatorios" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>Relatórios</title>
        <style>
            
            .jumbotron {
                float: left;
            }
            .col-md-12 {
                width: 100%;
                padding-bottom: 30px;
            }
            
            text {
                fill: black;
            }
            
        </style>
            
    </head>
    <body>
        
         <%@ include file="nav.jsp" %>
        
        <div class="container" style="padding-top: 1%">
            <div class="jumbotron">
                <div class="col-md-12">
                    <form class="form-inline" action="Relatorios" method="post">
                      <div class="form-group">
                        <label for="tipo">Tipo:</label>
                        <select class="form-control" name="tipo" onchange="mudaCalendario(this.value)">
                              <option value="alunos">Alunos</option>
                              <option value="entradas">Entradas</option>
                              <option value="saidas">Saídas</option>
                          </select>
                      </div>
                      <div class="form-group" id="mes_ref">
                        <input type="month" value="<fmt:formatDate value="${data}" pattern="yyyy-MM" />" class="form-control" name="mes_ref">
                      </div>
                      <div class="form-group" id="data_inicio">
                        <label for="data_inicio">De:</label>
                        <input type="date" class="form-control" value="<fmt:formatDate value="${data}" pattern="yyyy-MM-dd" />" name="data_inicio">
                      </div>
                        <div class="form-group" id="data_fim">
                        <label for="data_fim">Até:</label>
                        <input type="date" class="form-control" value="<fmt:formatDate value="${data}" pattern="yyyy-MM-dd" />" name="data_fim">
                      </div>
                      <button type="submit" class="btn btn-default">Gerar Relatório</button>
                    </form>
                </div>
                <div class="col-md-12">
                    <div class="col-md-12">
                        <div id="grafico_balanco" style="width: 1000px; height: 200px;"></div>
                    </div>
                    <div class="col-md-12">
                        <div id="grafico_alunos" style="width: 1000px; height: 200px;"></div>
                    </div>
                    <div class="col-md-12">
                        <div id="grafico_km" style="width: 1000px; height: 200px;"></div>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>
<script>
      $("#data_inicio").hide();
      $("#data_fim").hide();
      
    function mudaCalendario(value) {
          
          switch(value){
              case "alunos":
                  $("#mes_ref").show();
                  $("#data_inicio").hide();
                  $("#data_fim").hide();
                  break;
              case "entradas":
                  $("#mes_ref").hide();
                  $("#data_inicio").show();
                  $("#data_fim").show();
                  break;
              case "saidas":
                  $("#mes_ref").hide();
                  $("#data_inicio").show();
                  $("#data_fim").show();
                  break;
              default:
                  $("#mes_ref").hide();
                  $("#data_inicio").hide();
                  $("#data_fim").hide();
          }
      }
      
      
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawChart2);
      google.charts.setOnLoadCallback(drawChart3);

      function drawChart() {
          
         // var meses = ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'];
        var total_entradas = ${entradas[0]};
        var total_saidas = ${saidas[0]};
          
        var data = google.visualization.arrayToDataTable([
          ['${ano}', 'Entradas', 'Saidas'],
          ['Jan', ${entradas[1]}, ${saidas[1]}],
          ['Fev', ${entradas[2]}, ${saidas[2]}],
          ['Mar', ${entradas[3]}, ${saidas[3]}],
          ['Abr', ${entradas[4]}, ${saidas[4]}],
          ['Mai', ${entradas[5]}, ${saidas[5]}],
          ['Jun', ${entradas[6]}, ${saidas[6]}],
          ['Jul', ${entradas[7]}, ${saidas[7]}],
          ['Ago', ${entradas[8]}, ${saidas[8]}],
          ['Set', ${entradas[9]}, ${saidas[9]}],
          ['Out', ${entradas[10]}, ${saidas[10]}],
          ['Nov', ${entradas[11]}, ${saidas[11]}],
          ['Dez', ${entradas[12]}, ${saidas[12]}]
        ]);

        var options = {
          chart: {
            title: 'Balanço Financeiro',
            subtitle: 'Entradas : ' + total_entradas.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + '    -    Saídas: ' + total_saidas.toLocaleString("pt-br",{style: "currency", currency: "BRL"}) + ''
          },
          series: {
            0: { color: '#4caf50' }, // Bind series 0 to an axis named 'distance'.
            1: { color: '#f44336' } // Bind series 1 to an axis named 'brightness'.
          },
          titleTextStyle : {   color: 'black',
                            fontSize: 20
          },
          legend : {    textStyle : {      color: 'black',
                                        fontSize: 15
                        }
          }
        };
        
        var formatter = new google.visualization.NumberFormat(
            { prefix: 'R$',
             groupingSymbol: ',' });
         
        formatter.format(data, 1);
        formatter.format(data, 2);

        var chart = new google.charts.Bar(document.getElementById('grafico_balanco'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      function drawChart2() {
          
        var data = google.visualization.arrayToDataTable([
          ['${ano}', 'Novos', 'Cancelados'],
          ['Jan', ${novos[1]}, ${cancelados[1]}],
          ['Fev', ${novos[2]}, ${cancelados[2]}],
          ['Mar', ${novos[3]}, ${cancelados[3]}],
          ['Abr', ${novos[4]}, ${cancelados[4]}],
          ['Mai', ${novos[5]}, ${cancelados[5]}],
          ['Jun', ${novos[6]}, ${cancelados[6]}],
          ['Jul', ${novos[7]}, ${cancelados[7]}],
          ['Ago', ${novos[8]}, ${cancelados[8]}],
          ['Set', ${novos[9]}, ${cancelados[9]}],
          ['Out', ${novos[10]}, ${cancelados[10]}],
          ['Nov', ${novos[11]}, ${cancelados[11]}],
          ['Dez', ${novos[12]}, ${cancelados[12]}]
        ]);

        var options = {
          chart: {
            title: 'Entrada/Saída de Alunos',
            subtitle: 'Novos: ${novos[0]}    -    Cancelados: ${cancelados[0]}'
          },
          series: {
            0: { color: '#3f51b5' }, // Bind series 0 to an axis named 'distance'.
            1: { color: '#cddc39' } // Bind series 1 to an axis named 'brightness'.
          },
          titleTextStyle : {   color: 'black',
                            fontSize: 20
          },
          legend : {    textStyle : {      color: 'black',
                                        fontSize: 15
                        }
          }
        };
        
        
        var chart = new google.charts.Bar(document.getElementById('grafico_alunos'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      function drawChart3() {
          
        var data = google.visualization.arrayToDataTable([
          ['2021', 'Km'],
          ['Jan', ${km[1]}],
          ['Fev', ${km[2]}],
          ['Mar', ${km[3]}],
          ['Abr', ${km[4]}],
          ['Mai', ${km[5]}],
          ['Jun', ${km[6]}],
          ['Jul', ${km[7]}],
          ['Ago', ${km[8]}],
          ['Set', ${km[9]}],
          ['Out', ${km[10]}],
          ['Nov', ${km[11]}],
          ['Dez', ${km[12]}]
        ]);

        var options = {
          chart: {
            title: 'Kilometros Rodados',
            subtitle: 'Total em ${ano} : ${km[0]} km'
          },
          series: {
            0: { color: '#8a6d3b' } // Bind series 1 to an axis named 'brightness'.
          },
          titleTextStyle : {   color: 'black',
                            fontSize: 20
          },
          legend : {    textStyle : {      color: 'black',
                                        fontSize: 15
                        }
          }
        };
        
        
        var chart = new google.charts.Bar(document.getElementById('grafico_km'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      
</script>