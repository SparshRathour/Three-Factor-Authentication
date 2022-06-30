import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(urlPatterns = {"/serv_start"})
public class serv_start extends HttpServlet {
 public static boolean checkUser(String email,String pass) 
 {
 boolean st1=false;
 try{
 String db_url="jdbc:derby://localhost:1527/AMIT";
 DriverManager.registerDriver(new 
org.apache.derby.jdbc.ClientDriver());
 Connection 
conn=DriverManager.getConnection(db_url,"AMIT","AMIT");
 String sql="SELECT * FROM REGISTER WHERE NAME='"+email+"' AND 
PASS='"+pass+"'";
 Statement st;
 st = conn.createStatement();
 ResultSet rs=null;
 rs=st.executeQuery(sql);
 st1=rs.next();
 rs.close();
 st.close();
 conn.close();
 }
 catch(SQLException ex){
 System.out.println(ex);
 }
 return st1; 
 }
 /**
 * Processes requests for both HTTP <code>GET</code> and 
<code>POST</code>
 * methods.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
 protected void processRequest(HttpServletRequest request, 
HttpServletResponse response)
 throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
 PrintWriter out = response.getWriter();
 
 String email = request.getParameter("username");
 String pass = request.getParameter("password");
 
 if(checkUser(email, pass))
 {
 out.println("verified successfully");
 }
 else
 {
 out.println("Username or Password incorrect");
 }
 }
 // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click 
on the + sign on the left to edit the code.">
 /**
 * Handles the HTTP <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
 @Override
 protected void doGet(HttpServletRequest request, HttpServletResponse 
response)
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
 protected void doPost(HttpServletRequest request, HttpServletResponse 
response)
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
