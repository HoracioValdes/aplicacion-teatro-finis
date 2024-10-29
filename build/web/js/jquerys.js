/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready(function(){
    //alert("hola!");
    
    // Función para manejar el cambio de clase cuando se cambia de mobile a PC
    function cambiarClaseSegunAncho() {
        var $elemento = $('#caja-titulo');

        if (window.matchMedia('(max-width: 1000px)').matches) {
            // Vista mobile, cambia la clase a 'col-12'
            $elemento.removeClass('col-8').addClass('col-12');
        } else {
            // Vista de PC, cambia la clase a 'col-8'
            $elemento.removeClass('col-12').addClass('col-8');
        }
    }

    // Llama a la función cuando se carga la página
    cambiarClaseSegunAncho();

    // Maneja el evento resize para detectar cambios en el ancho de la pantalla
    $(window).on('resize', cambiarClaseSegunAncho);
});

