<nav class="navbar navbar-inverse" style="border-radius: 0">
    <div class="container-fluid">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span> 
        </button>

      </div>
      <div class="collapse navbar-collapse" id="myNavbar">
        <ul class="nav navbar-nav">
           <c:if test="${marcador == 1}">
               <li class="active"><a href="Alunos">Alunos</a></li>
               <li><a href="Entradas">Entradas</a></li>
               <li><a href="Saidas">Saídas</a></li>
               <li><a href="Veiculos">Veiculos</a></li>
               <li><a href="Motoristas">Motoristas</a></li>
               <li><a href="Relatorios">Relatórios</a></li>
           </c:if>
           <c:if test="${marcador == 2}">   
               <li><a href="Alunos">Alunos</a></li>
               <li class="active"><a href="Entradas">Entradas</a></li>
               <li><a href="Saidas">Saídas</a></li>
               <li><a href="Veiculos">Veiculos</a></li>
               <li><a href="Motoristas">Motoristas</a></li>
               <li><a href="Relatorios">Relatórios</a></li>
           </c:if>
           <c:if test="${marcador == 3}">
               <li><a href="Alunos">Alunos</a></li>
               <li><a href="Entradas">Entradas</a></li>
               <li class="active"><a href="Saidas">Saídas</a></li>
               <li><a href="Veiculos">Veiculos</a></li>
               <li><a href="Motoristas">Motoristas</a></li>
               <li><a href="Relatorios">Relatórios</a></li>
           </c:if>
           <c:if test="${marcador == 4}">
               <li><a href="Alunos">Alunos</a></li>
               <li><a href="Entradas">Entradas</a></li>
               <li><a href="Saidas">Saídas</a></li>
               <li class="active"><a href="Veiculos">Veiculos</a></li>
               <li><a href="Motoristas">Motoristas</a></li>
               <li><a href="Relatorios">Relatórios</a></li>
           </c:if>
           <c:if test="${marcador == 5}">
               <li><a href="Alunos">Alunos</a></li>
               <li><a href="Entradas">Entradas</a></li>
               <li><a href="Saidas">Saídas</a></li>
               <li><a href="Veiculos">Veiculos</a></li>
               <li class="active"><a href="Motoristas">Motoristas</a></li>
               <li><a href="Relatorios">Relatórios</a></li>
           </c:if>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href="Logout"><span class="glyphicon glyphicon-log-out"></span> Sair</a></li>
        </ul>
      </div>
    </div>
</nav>