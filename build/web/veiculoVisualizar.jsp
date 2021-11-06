<%-- 
    Document   : clientesVisualizar
    Created on : 12/09/2018, 09:08:29
    Author     : julio
--%>
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
<c:set var="marcador" value="4" />

<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Veiculos?action=visualizar&id=${veiculo.id}" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>${veiculo.numero} - ${veiculo.modelo}</title>
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
                <a href="Veiculos" class="btn btn-default">Voltar</a>
                <a href="Veiculos?action=delete&id_veiculo=${veiculo.id}" class="btn float-right btn-danger active" onclick="return confirm('Tem certeza que deseja excluir ${veiculo.modelo} - ${veiculo.numero}?')">Excluir</a>
                <a href="Veiculos?action=form-alterar&id_veiculo=${veiculo.id}" class="btn float-right btn-success active">Alterar</a>
                <h1>${veiculo.modelo} - ${veiculo.numero}</h1>
                <br>
                <p><strong>Cor: </strong>${veiculo.cor}</p>
                <p><strong>Capacidade: </strong>${veiculo.capacidade}</p>
                <p><strong>Placa: </strong>${veiculo.placa}</p>
                <p><strong>Licença: </strong>${veiculo.licenca}</p>
                
                
                <br><br>
                
                <h2><strong>Motorista</strong></h2>
                <p><strong>Nome: </strong>${veiculo.motorista.nome}</p>
                <p><strong>CNH: </strong>${veiculo.motorista.cnh}</p>
                <p><strong>Telefone: </strong>${veiculo.motorista.telefone}</p>
                <p><strong>Endereco: </strong>${veiculo.motorista.endereco}</p>
                
                <br><br>
                
                <h2><strong>Alunos</strong></h2>
                
                <p>Quantidade: ${alunos.size()}</p>
                <p>Total de mensalidades: <fmt:formatNumber type = "currency" value = "${totalMensalidades}" /></p>
                <br>
                <div class="tabela">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nome</th>
                                <th>Telefone</th>
                                <th>Escola</th>
                                <th>Período</th>
                                <th>Mensalidade</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${alunos}" var="a">
                                <tr>
                                    <td>${a.nome}</td>
                                    <td>${a.telefone}</td>
                                    <td>${a.escola.nome}</td>
                                    <td>${a.periodo}</td>
                                    <td>${a.getMensalidadeFormated()}</td>
                                    <td><a class="icones-table verde" href="Alunos?action=visualizar&id=${a.id}" title="Visualizar"><span class="far fa-eye"></span></a></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br><br>
                
                <h2><strong>Abastecimentos</strong></h2>
                
                <p>Quantidade: ${abastecimentos.size()}</p>
                <p>Kilometros já rodados: ${rodado}</p>
                <p>Média de consumo: <fmt:formatNumber type = "number" maxFractionDigits = "2" value = "${media}" />km/l</p>
                <br>
                <div class="tabela">
                    <table class="table">
                        <thead>
                            <tr>
                              <th>Valor</th>
                              <th>Litros</th>
                              <th>Data</th>
                              <th>Posto</th>
                              <th>Kilometragem</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${abastecimentos}" var="a">
                                <tr>
                                    <td><fmt:formatNumber value="${a.dispesa.valor}" type="currency" /></td>
                                    <td>${a.litros}</td>
                                    <td>${fn:substring(a.dispesa.data, 8, 10)}/${fn:substring(a.dispesa.data, 5, 7)}/${fn:substring(a.dispesa.data, 0, 4)}</td>
                                    <td>${a.posto}</td>
                                    <td>${a.kilometragem}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <br><br>
                
                <h2><strong>Manutenções</strong></h2>
                
                <br>
                <div class="tabela">
                    <table class="table">
                        <thead>
                            <tr>
                              <th>Valor</th>
                              <th>Data</th>
                              <th>Problema</th>
                              <th>Kilometragem</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${manutencoes}" var="m">
                                <tr>
                                    <td><fmt:formatNumber value="${m.dispesa.valor}" type="currency" /></td>
                                    <td>${fn:substring(m.dispesa.data, 8, 10)}/${fn:substring(m.dispesa.data, 5, 7)}/${fn:substring(m.dispesa.data, 0, 4)}</td>
                                    <td>${m.problema}</td>
                                    <td>${m.kilometragem}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
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