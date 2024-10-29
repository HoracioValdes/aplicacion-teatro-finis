<%-- 
    Document   : resultado
    Created on : 06-11-2023, 12:38:00
    Author     : horac
--%>

<%@page import="cl.modelo.Resultado"%>
<%@page import="cl.modelo.Ingreso"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico" />
        <title>Resultados Prueba de Admisión Escuela de Teatro</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="js/jquerys.js" type="text/javascript"></script>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&family=Raleway:wght@400;700&display=swap" rel="stylesheet">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="css/estilos.css">
        <script src="js/javascript.js" type="text/javascript"></script>
    </head>
    <%
        Ingreso ingreso = (Ingreso) session.getAttribute("ingreso");
        Resultado resultado = (Resultado) session.getAttribute("resultado");
    %>
    <body>
        <header>
            <div class="container">
                <div class="row" id="caja-header">
                    <div class="col-4 align-middle mt-2"><a href="index.jsp"><img id="logo" src="img/Logo-Facultad-de-Artes.png" alt="Logo Facultad de Artes"></a></div>
                    <div class="col-8 align-middle" id="caja-titulo"><h2 id="titulo-pagina" class="align-middle">Resultados Prueba de Admisión Escuela de Teatro</h2></div>
                </div>
            </div>    
        </header>
        <main>
            <!--<img src="img/banner.png" class="img-fluid rounded mx-auto d-block" alt="Banner 2023">-->
            <div class="container">
                <% if (ingreso != null) {%>
                <div class="row mt-5 mb-5" id="caja-contenido">
                    <div class="col-10 offset-md-1" id="caja-resultados">
                        <!--<h1 id="titulo-instrucciones">Resultados</h1>-->
                        <% if (resultado.getPuntaje() != 0) {%>
                        <h3>Estimada/o ${requestScope.resultado.nombre} ${requestScope.resultado.apellido_pat} ${requestScope.resultado.apellido_mat}</h3>
                        <h3 class="mt-3">Tu puntaje final es de:</h3><br>
                        <h3 class="mb-5"><b>${requestScope.resultado.puntaje} Puntos</b></h3>
                        <% } %>
                        <form action="cerrar.do">
                            <input type="submit" class="btn btn-primary" value="Salir" style="width: 100px;" id="boton-resultado">
                        </form>
                    </div>
                </div>
                <% } else { %>
                <div id="acceso-denegado" class="card mt-3 mb-5">
                    <div class="col-10 offset-md-1 pt-3 pb-5">
                        <h3>No has ingresado a la aplicación</h3>
                        <p>Deberás ingresar tu rut y el código proporcionado para ver tus resultados.</p>
                        <a href="codigo.jsp" class="btn btn-danger">Ir al acceso</a>
                    </div>
                </div>
                <% }%>
            </div>
        </main>
        <footer>
            <p id="direccion">Universidad Finis Terrae 2023 / Avenida Pedro de Valdivia 1509 - Providencia - Santiago</p> 
            <img id="gratuidad" src="img/gratuidad.png" alt="Adscrita a gratuidad">
            <img id="acreditada" src="img/logo-acreditación.png" alt="Universidad acreditada">  
        </footer>
    </body>
</html>
