<%-- 
    Document   : codigo
    Created on : 06-11-2023, 10:54:25
    Author     : horac
--%>

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
        <script>
            function limpiar_rut(rut) {
                var rutLimpio = rut;
                //Definimos los caracteres a eliminar
                var eliminar = ".-";
                //Los eliminamos
                for (var i = 0; i < eliminar.length; i++) {
                    rutLimpio = rutLimpio.replace(new RegExp("\\" + eliminar[i], 'gi'), '');
                }
                //Pasamos al campo el valor limpio
                document.getElementById("campoRut").value = rutLimpio.toUpperCase();
            }

            function formato_rut(rut) {
                var sRut1 = rut; //Contador de posición
                var nPos = 0; //Guarda el rut invertido con los puntos y el guión agregado
                var sInvertido = ""; //Guarda el resultado final del rut como debe ser
                var sRut = "";
                for (var i = sRut1.length - 1; i >= 0; i--) {
                    sInvertido += sRut1.charAt(i);
                    if (i == sRut1.length - 1) {
                        sInvertido += "-";
                    } else if (nPos == 3) {
                        sInvertido += ".";
                        nPos = 0;
                    }
                    nPos++;
                }
                for (var j = sInvertido.length - 1; j >= 0; j--) {
                    if (sInvertido.charAt(sInvertido.length - 1) != ".") {
                        sRut += sInvertido.charAt(j);
                    } else if (j != sInvertido.length - 1) {
                        sRut += sInvertido.charAt(j);
                    }
                }
                //Pasamos al campo el valor formateado
                document.getElementById("campoRut").value = sRut.toUpperCase();
            }
        </script>
    </head>
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
                <div class="row mt-5 mb-5" id="caja-contenido">
                    <div class="col-7" id="caja-formulario">
                        <h1 id="titulo-instrucciones">Acceso a resultados</h1>
                        <p>Ingresa tu rut y el código que te enviamos a tu correo para acceder a los resultados.</p>
                        <form method="post" action="acceso.do" id="form-dos">
                            <div class="mb-3">
                                <label for="InputRut" class="form-label">Rut</label>
                                <input type="text" class="form-control" name="rut" id="campoRut" maxlength="12" aria-describedby="ayudaRut" onblur="limpiar_rut(this.value);formato_rut(this.value);">
                                <div id="ayudaRut" class="form-text">Ingresa tu rut sin puntos ni guión.</div>
                            </div>
                            <div class="mb-3">
                                <label for="InputCodigo" class="form-label">Código</label>
                                <input type="text" class="form-control" name="codigo" maxlength="15" id="campoCodigo">
                            </div>
                            <div class="g-recaptcha" data-sitekey="6LcKPfEoAAAAAIZBh02MG41FKpXf640hd0xEgNoP"></div>
                            <button type="submit" id="boton-ingreso" name="action" class="btn btn-primary mt-3" onclick="return probandoDos();">Enviar</button>
                        </form>
                    </div>
                    <div class="col-4 offset-md-1" id="caja-botones">
                        
                        <% if (request.getAttribute("msg") != null) { %>
                            <div class="card">
                                <div class="card-body">
                                    <p id="mensaje_codigo" class="card-text">${requestScope.msg}</p>
                                </div>
                            </div>
                        <%}%>
                        
                        <p class="mt-4"><b>Vuelve a la página de inicio.</b></p>
                        <form action="index.jsp" method="POST">  
                            <button type="submit" class="btn btn-primary mb-3">Volver al inicio</button>
                        </form>
                    </div>
                </div>
            </div>
        </main>
        <footer>
            <p id="direccion">Universidad Finis Terrae 2023 / Avenida Pedro de Valdivia 1509 - Providencia - Santiago</p> 
            <img id="gratuidad" src="img/gratuidad.png" alt="Adscrita a gratuidad">
            <img id="acreditada" src="img/logo-acreditación.png" alt="Universidad acreditada">  
        </footer>
        <script src="https://www.google.com/recaptcha/api.js" async defer></script>
        <script>
            window.onload = function () {
                let isValid = false;
                const form = document.getElementById("form-dos");

                form.addEventListener("submit", function (event) {
                    event.preventDefault();
                    const response = grecaptcha.getResponse();

                    if (response) {
                        form.submit();
                    } else {
                        alert('[ERROR] Debes confirmar que no eres un robot en el recaptcha');
                    }
                });
            }
        </script>
        <script>
            function probandoDos() {

                var campoRut = document.getElementById("campoRut").value;
                var campoCodigo = document.getElementById("campoCodigo").value;
                if (campoRut == null || campoRut.length == 0 || /^\s+$/.test(campoRut)) {
                    alert('[ERROR] El campo que indica el rut no puede estar vacío');
                    return false;
                } else if (campoRut.length < 11) {
                    alert('[ERROR] El campo que indica el rut no puede tener menos de 11 caracteres');
                    return false;
                } else if (campoRut.length > 12) {
                    alert('[ERROR] El campo que indica el rut no puede tener más de 12 caracteres');
                    return false;
                } else if (Valida_Rut(campoRut) == false) {
                    alert('[ERROR] El rut ingresado no es válido');
                    return false;
                } else if (campoCodigo == null || campoCodigo.length == 0 || /^\s+$/.test(campoCodigo)) {
                    alert('[ERROR] El campo que indica el código de acceso no puede estar vacío');
                    return false;
                }
                return true;
            }

            function valrut(campoRut) {

                alert("Validando rut!");
                alert(campoRut);
                alert(Fn.validaRut(11111111 - 1) ? 'Valido' : 'inválido');
                var Fn = {
                    validaRut: function (Rut) {
                        if (!/^[0-9]+-[0-9kK]{1}$/.test(Rut))
                            return false;
                        var tmp = rutCompleto.split('-');
                        var digv = tmp[1];
                        var rut = tmp[0];
                        if (digv == 'K')
                            digv = 'k';
                        return (Fn.dv(rut) == digv);
                    },
                    dv: function (T) {
                        var M = 0,
                                S = 1;
                        for (; T; T = Math.floor(T / 10))
                            S = (S + T % 10 * (9 - M++ % 6)) % 11;
                        return S ? S - 1 : 'k';
                    }
                }
            }

            function Valida_Rut(Objeto) {
                var rutlimpio = Objeto;

                rutlimpio = rutlimpio.replace(".", "");
                rutlimpio = rutlimpio.replace("-", "");

                {

                    var tmpstr = "";

                    var intlargo = rutlimpio;

                    if (intlargo.length > 0)

                    {

                        crut = rutlimpio;

                        largo = crut.length;

                        if (largo < 2)

                        {

                            alert('rut inválido');

                            return false;

                        }

                        for (i = 0; i < crut.length; i++)
                            if (crut.charAt(i) != ' ' && crut.charAt(i) != '.' && crut.charAt(i) != '-')

                            {

                                tmpstr = tmpstr + crut.charAt(i);

                            }

                        rut = tmpstr;

                        crut = tmpstr;

                        largo = crut.length;



                        if (largo > 2)
                            rut = crut.substring(0, largo - 1);

                        else
                            rut = crut.charAt(0);



                        dv = crut.charAt(largo - 1);



                        if (rut == null || dv == null)
                            return 0;



                        var dvr = '0';

                        suma = 0;

                        mul = 2;



                        for (i = rut.length - 1; i >= 0; i--)

                        {

                            suma = suma + rut.charAt(i) * mul;

                            if (mul == 7)
                                mul = 2;

                            else
                                mul++;

                        }



                        res = suma % 11;

                        if (res == 1)
                            dvr = 'k';

                        else if (res == 0)
                            dvr = '0';

                        else

                        {

                            dvi = 11 - res;

                            dvr = dvi + "";

                        }



                        if (dvr != dv.toLowerCase())

                        {

                            // alert('El Rut Ingreso es Invalido');

                            return false;

                        }

                        // alert('El Rut Ingresado es Correcto!');

                        return true;

                    }

                }
            }
        </script>
    </body>
</html>
