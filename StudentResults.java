
//Import required java libraries
import java.io.IOException;
import java.io.PrintWriter;

//Loading required libraries

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//Extend HttpServlet class
public class StudentResults extends HttpServlet {

// Method to handle GET method request.
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@SuppressWarnings("unused")
public void doGet(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {
   
   // Set response content type
	
   response.setContentType("text/html");

   PrintWriter out = response.getWriter();
   String title = "Student Results";
   String Result=null;
  // /*
      try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(""+e);
		}
         //Class.forName("oracle.jdbc.driver.OracleDriver");
         try {
         // Open a connection
         // Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Connection conn=DriverManager.getConnection(
                 "jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
         String sql = "SELECT result FROM students where ROLLNO=? and SEM=?";
         PreparedStatement  stmt = conn.prepareStatement(sql);
         stmt.setString(1,  request.getParameter("sid"));
        		 
         stmt.setString(2, request.getParameter("sem"));
        		 
                     // Execute SQL query
         ResultSet rs = stmt.executeQuery();
         System.out.print(rs);
		 while(rs.next()){
            //Retrieve by column name
			 
			 Result = rs.getString(1);
		 }
		    rs.close();
         stmt.close();
         conn.close();
      } catch(SQLException se) {
         //Handle errors for JDBC
         se.printStackTrace();
      } catch(Exception e) {
         //Handle errors for Class.forName
         e.printStackTrace();
      }
  //   */
    
   String docType =
      "<!doctype html public \"-//w3c//dtd html 4.0 " +
      "transitional//en\">\n";
     if (Result!=null)
     {
   out.println(docType +
      "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#000fff\">\n" +
            "<h1 align = \"center\">" + title + "</h1>\n" +
            "<ul>\n" +
               "  <li><b>SID</b>: "
               + request.getParameter("sid") + "\n" +
               "  <li><b>Semester</b>: "
               + request.getParameter("sem") + "\n" +
               "  <li><b>Result</b>: "+
               Result + "\n" +
            "</ul>\n" +
         "</body>"+
      "</html>"
   );
     }
     else {out.println(docType +
    	      "<html>\n" +
    	         "<head><title>" + title + "</title></head>\n" +
    	         "<body bgcolor = \"#000fff\">\n" +
    	            "<h1 align = \"center\">" + title + "</h1>\n" +
    	            "<H1>Result Not Found or Invalid Details given"+
    	         "</body>"+
    	      "</html>"
    	   );}
}

// Method to handle POST method request.
public void doPost(HttpServletRequest request, HttpServletResponse response)
   throws ServletException, IOException {

   doGet(request, response);
}
}