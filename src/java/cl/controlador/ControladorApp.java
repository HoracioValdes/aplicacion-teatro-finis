/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package cl.controlador;

import cl.dao.DaoOperaciones;
import cl.modelo.Identificacion;
import cl.modelo.Ingreso;
import cl.modelo.Resultado;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author horac
 */
@WebServlet(name = "ControladorApp", urlPatterns = {"/insercion.do", "/acceso.do", "/cerrar.do"})
public class ControladorApp extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response   
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Corrección de caracteres
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        //Se identifica la petición realizada.
        String userPath = request.getServletPath();
        
        if (userPath.equals("/insercion.do")) {
            
            //Variables de mensajes
            String msg = "";
            Boolean error = false;
            
            DaoOperaciones dao = new DaoOperaciones();
            
            //Se recuperan los parámetros desde la petición.
            String rut = request.getParameter("rut");
            
            //Limpieza de rut
            rut = rut.replace(".", "");
            rut = rut.toUpperCase();
            
            //Consulta de la existencia de Rut en la base de datos
            String rutEx = dao.consultaRegistro(rut);
            
            if (rutEx.equals(rut)) {
                
                //Consulta de inserción previa de código
                //Se obtiene el codigo
                String codigo = dao.consultaCodigo(rut);
                
                if (codigo.length() == 0) {
                    
                    //Generar el código
                    int longitud = 12;
                    String cadena = cadenaAleatoria(longitud);

                    //Obtenemos el id para concatenar a la cadena de código
                    int id = dao.consultaId(rut);

                    String cadenaId = String.valueOf(id);

                    codigo = cadena + cadenaId;
                    
                    //Obtener correo de base de datos, para enviar un correo con el código
                    String correo = dao.consultaCorreoRegistrado(rut);
                    
                    //Obtener nombre y apellidos del usuario
                    Identificacion identificacion = new Identificacion();
                    identificacion = dao.obtenerNombre(rut);
                    
                    //Enviar un correo con el código
                    String destinatario = correo; //A quien le quieres escribir.
                    String asunto = "Código para acceder a resultados de los procesos de admisión de la Escuela de Teatro";
                    String cuerpo = "Hola, "+identificacion.getNombre()+ " " +identificacion.getApellido_pat()+ " " +identificacion.getApellido_mat()+ "\n\nEl código para acceder a tus resultados, es el siguiente: " + codigo + "\n\nGracias por haber participado en el Proceso de Admisión a la Escuela de Teatro de la Universidad Finis Terrae. Recuerda que puedes conocer todas las carreras en https://admision.uft.cl";

                    if (enviarConGMail(destinatario, asunto, cuerpo)) {
                        //Insertamos el código en la base de datos
                        dao.insertarCodigo(rut, codigo);
                        
                        msg = "Hemos enviado un código de acceso a tu correo. Te llegará durante las próximas horas.";
                    } else {
                        error = true;
                        msg = "Se ha presentado un error en el envío del correo";
                    }
                    
                } else {
                    //Obtener correo de base de datos, para enviar un correo con el código
                    String correo = dao.consultaCorreoRegistrado(rut);
                    
                    //Obtener nombre y apellidos del usuario
                    Identificacion identificacion = new Identificacion();
                    identificacion = dao.obtenerNombre(rut);
                    
                    //Reenviar un correo con el código
                    //Enviar un correo con el código
                    String destinatario = correo; //A quien le quieres escribir.
                    String asunto = "Reenvío de código para acceder a resultados de los procesos de admisión de la Escuela de Teatro";
                    String cuerpo = "Hola, "+identificacion.getNombre()+ " " +identificacion.getApellido_pat()+ " " +identificacion.getApellido_mat()+ "\n\nEl código para acceder a tus resultados, es el siguiente: " + codigo + "\n\nGracias por haber participado en el Proceso de Admisión a la Escuela de Teatro de la Universidad Finis Terrae. Recuerda que puedes conocer todas las carreras en https://admision.uft.cl";
                    
                    if (enviarConGMail(destinatario, asunto, cuerpo)) {
                        //Insertamos el código en la base de datos
                        dao.insertarCodigo(rut, codigo);
                        
                        msg = "Ya se ha generado un código de acceso previamente. Lo hemos reenviado a tu correo electrónico. Te llegará durante las próximas horas.";
                    } else {
                        error = true;
                        msg = "Se ha presentado un error en el envío del correo";
                    }
                }
                
            } else {
                error = true;
                msg = "El rut ingresado no está registrado en la base de datos";              
            }
            
            if(error) {
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("codigo.jsp").forward(request, response);
            } 
            
        } else if (userPath.equals("/acceso.do")) {
            //Variables de mensajes
            String msg = "";
            Boolean error = false;
            Ingreso ingresoValido = new Ingreso();
            ingresoValido.setValido(false);

            // Variables de operaciones en BBDD
            DaoOperaciones dao = new DaoOperaciones();
            Resultado resultado = new Resultado();

            //Se recuperan los parámetros desde la petición.
            String rut = request.getParameter("rut");
            String codigo = request.getParameter("codigo");

            //Limpieza de rut
            rut = rut.replace(".", "");
            rut = rut.toUpperCase();

            //Consultamos el puntaje
            resultado = dao.obtenerResultado(rut, codigo);

            if (resultado != null && resultado.getPuntaje() == 0 && resultado.getNombre() == null && resultado.getApellido_pat() == null && resultado.getApellido_mat() == null) {
                error = true;
            }

            if (!error) {
                ingresoValido.setValido(true);
                request.getSession().setAttribute("ingreso", ingresoValido);
                request.getSession().setAttribute("resultado", resultado);
                request.setAttribute("resultado", resultado);
                request.getRequestDispatcher("resultado.jsp").forward(request, response);
            } else {
                msg = "Los datos de acceso no son válidos";
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("codigo.jsp").forward(request, response);
            }
        } else if (userPath.equals("/cerrar.do")) {
            // Se cierra la sesión
            HttpSession sesion = request.getSession();
            sesion.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
    
    public static int numeroAleatorioEnRango(int minimo, int maximo) {
        // nextInt regresa en rango pero con límite superior exclusivo, por eso sumamos 1
        return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
    }

    public static String cadenaAleatoria(int longitud) {
        // El banco de caracteres
        String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        // La cadena en donde iremos agregando un carácter aleatorio
        String cadena = "";
        for (int x = 0; x < longitud; x++) {
            int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
            char caracterAleatorio = banco.charAt(indiceAleatorio);
            cadena += caracterAleatorio;
        }
        return cadena;
    }
    
    private static boolean enviarConGMail(String destinatario, String asunto, String cuerpo) {

        //Variable de confirmación
        boolean envio = false;

        // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
        String remitente = "desarrollo-web@uft.cl";  //Para la dirección nomcuenta@gmail.com

        // Segunda versión
        Properties props = System.getProperties();

        // Segunda versión
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");

        // Segunda versión
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("desarrollo-web@uft.cl", "gcrm bkts aijr bbxp");
            }
        });
        //Cambiar a false en paso a producción
        session.setDebug(false);
        try {

            //Segunda versión
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header.
            message.setFrom(new InternetAddress(remitente));
            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            // Set Subject: header field
            message.setSubject(asunto);
            // Now set the actual message
            message.setText(cuerpo);
            System.out.println("sending...");
            // Send message
            Transport.send(message);
            envio = true;
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        return envio;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
