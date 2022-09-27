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
<c:set var="marcador" value="1" />
<c:if test="${login == null}">
   <jsp:forward page="/index.jsp">
        <jsp:param name="msg" value="Usuário deve se autenticar para acessar o sistema." />
        <jsp:param name="red" value="Alunos?action=visualizar&id=${aluno.id}" />
    </jsp:forward> 
</c:if>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <title>${aluno.nome}</title>
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
                <a href="Alunos" class="btn btn-default">Voltar</a>
                <a href="Alunos?action=delete&id=${aluno.id}" class="btn float-right btn-danger active" onclick="return confirm('Tem certeza que deseja excluir ${aluno.nome}?')">Excluir</a>
                <a href="Alunos?action=form-alterar&id=${aluno.id}" class="btn float-right btn-success active">Alterar</a>
                <c:if test="${aluno.status != 1}" >
                    <a href="Alunos?action=ativar&id=${aluno.id}" class="btn float-right btn-warning active">Ativar</a>
                </c:if>
                <h1>
                    <c:if test="${aluno.status != 1}" >
                        [DESATIVADO]<br>
                    </c:if>
                    ${aluno.nome}
                </h1>
                <br>
                <p><strong>Endereço: </strong>${aluno.endereco}</p>
                <p><strong>Telefone: </strong>${aluno.telefone}</p>
                <p><strong>Data de Nascimento: </strong>${fn:substring(aluno.dataNascimento, 8, 10)}/${fn:substring(aluno.dataNascimento, 5, 7)}/${fn:substring(aluno.dataNascimento, 0, 4)}</p>
                <p><strong>Aluno desde </strong> ${fn:substring(aluno.dataInicio, 8, 10)}/${fn:substring(aluno.dataInicio, 5, 7)}/${fn:substring(aluno.dataInicio, 0, 4)} <strong>até</strong> <c:if test="${aluno.dataFim != '2000-01-01'}" > ${fn:substring(aluno.dataFim, 8, 10)}/${fn:substring(aluno.dataFim, 5, 7)}/${fn:substring(aluno.dataFim, 0, 4)}</c:if><c:if test="${aluno.dataFim == '2000-01-01'}" > Atualmente </c:if></p>
                <p><strong>Veículo: </strong>${aluno.veiculo.numero} - ${aluno.veiculo.modelo}</p>
                    
                <br><br>
                
                <h2><strong>Contatos</strong></h2>
                <div class="tabela">
                    <table class="table">
                        <thead>
                            <tr>
                              <th>Nome</th>
                              <th>Telefone</th>
                              <th>Parentesco</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${aluno.contatos}" var="c">
                                <tr>
                                    <td>${c.nome}</td>
                                    <td>${c.telefone}</td>
                                    <td>${c.parentesco}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>    
                    
                <br><br>
                
                <h2><strong>Escola</strong></h2>
                <p><strong>Nome: </strong>${aluno.escola.nome}</p>
                <p><strong>Período: </strong>${aluno.periodo}</p>
                <c:if test="${aluno.turma != ''}" >
                    <p><strong>Turma: </strong>${aluno.turma}</p>
                </c:if>
                <c:if test="${aluno.horarioCasaIda != ''}" >
                    <p><strong>Saída (Casa): </strong>${aluno.horarioCasaIda}</p>
                </c:if>
                <c:if test="${aluno.horarioEscolaIda != ''}" >
                    <p><strong>Chegada (Escola): </strong>${aluno.horarioEscolaIda}</p>                
                </c:if>
                <c:if test="${aluno.horarioEscolaVolta != ''}" >
                    <p><strong>Saída (Escola): </strong>${aluno.horarioEscolaVolta}</p>                
                </c:if>    
                <c:if test="${aluno.horarioCasaVolta != ''}" >
                    <p><strong>Chegada (Casa): </strong>${aluno.horarioCasaVolta}</p>
                </c:if>
                
                
                <br><br>
                
                <h2><strong>Contrato e Pagamentos</strong></h2>
                
                <p><strong>Responsável: </strong>${aluno.nomeResponsavel}</p>
                <p><strong>CPF: </strong>${aluno.cpfResponsavel}</p>
                <p><strong>Mensalidade: </strong><fmt:formatNumber type="currency" value="${aluno.mensalidade}" /></p>
                <p><strong>Vencimento: </strong>${aluno.vencimento}</p>
                <br>
                <h2>Histórico</h2>
                <div class="tabela">
                    <table class="table">
                        <thead>
                            <tr>
                              <th></th>
                              <th>Valor</th>
                              <th>Data do Pagamento</th>
                              <th>Mês de Referência</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pagamentos}" var="p">
                                <tr>
                                    <td><span class="glyphicon glyphicon-ok" style="color: #30bd21;"></span></td>
                                    <td>${p.getValor_pagoFormated()}</td>
                                    <td>${fn:substring(p.data, 8, 10)}/${fn:substring(p.data, 5, 7)}/${fn:substring(p.data, 0, 4)}</td>
                                    <td>${p.mes_ref}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            
        </div>
    </body>
</html>
