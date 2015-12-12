//This program takes snapshot of any Url provided through front-end, by using 'GrabzIt' API and stores it in local database. 


import it.grabz.grabzit.GrabzItClient;
//import it.grabz.grabzit.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class Detectify extends HttpServlet { 
	
	private static final long serialVersionUID = 102831973239L;	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request,response);
	} 
protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
  String urllink=request.getParameter("url");
  
  System.out.print(urllink);
  String parts[] = urllink.split(";");
  
  
  int i = 0;
  System.out.println(i);
  int j =0;
  /*
  String phantomjsHome = "/home/shikhar/phantomjs-1.9.8-linux-x86_64";
  String phantomjsRasterizeScript = phantomjsHome + "examples/rasterize.js";
  
  */
  
 //CREATE TABLE datatable1 (url1 varchar(45) DEFAULT NULL,location1 varchar(45), PRIMARY KEY(url1)) ENGINE = INNODB;


  try{
	  GrabzItClient grabzIt = new GrabzItClient("NGUyNWQ4NWYwM2NhNGI3MzljOGQ0OTFlMWRjNTkxZGU=", "CD9KPz8wKCdcP0E/SW8/P00/Pz8iP2A/BwM/Kj8/Bj8=");
    
	  Class.forName("com.mysql.jdbc.Driver");
    
    String host = "jdbc:mysql://localhost:3306/demo" ;
    
    String uName = "root";
    String uPassword = "shikharsingh22";
    System.out.println("hello1");
    String SQLquer = "INSERT INTO datatable1 values (?,?)";
    Connection con1 = DriverManager.getConnection(host, uName, uPassword);
    System.out.println("hello3");
    PreparedStatement prep = con1.prepareStatement(SQLquer);
    Statement stmt1 = con1.createStatement();
    InputStream is = null;
    System.out.println("hello2");
    i=parts.length;
    System.out.println(i);
    
    while(i>0){
      
      j++;
      //printing the entered URL
      //	System.out.println("The url is" +parts);
      i--;
      System.out.println(parts[i]);
      String url1 = parts[i];
      System.out.println(url1);
      
      
      String filename1[]= url1.split("\\.");
      System.out.println(filename1[1]);
      String file = "/home/shikhar/"+filename1[1]+".jpg";
      System.out.println(file);
      //String file = "/home/shikhar/sample1.jpg";
      grabzIt.SetImageOptions(url1);
      /*
      System.out.println(file);
      ProcessBuilder pb = new ProcessBuilder(phantomjsHome + "/bin/phantomjs", phantomjsRasterizeScript, url, file);
      ProcessBuilder.Redirect error = pb.redirectError();
      ProcessBuilder.Redirect out = pb.redirectOutput();
      ProcessBuilder.Redirect in = pb.redirectInput();
      Process process = pb.start();
      
      try {
    	    process.waitFor();
    	} catch (InterruptedException e) {
    	   
    	    e.printStackTrace();
    	}
      
      response.setContentType("image/jpg");
      String pathToWeb = getServletContext().getRealPath(File.separator);
      System.out.println(file);
      File f = new File(file);
      System.out.println("file");
      BufferedImage bi = ImageIO.read(f);
      OutputStream out1 = response.getOutputStream();
      ImageIO.write(bi, "jpg", out1);
         
         */   
      //String filepath = "/home/shikhar/sample1.jpg";
      grabzIt.SaveTo(file);
      prep.setString(1, parts[i]);
      //is = new FileInputStream(new File("/home/shikhar/Desktop/index.jpg"));
      //prep.setBinaryStream(2, is);
      prep.setString(2, file);
      
      prep.executeUpdate();
      
     // out1.close();
      
    };
    is.close();
    prep.close();
    

    con1.close();
  }catch(Exception E){
    System.out.println("The error occurred:"+E.getMessage());
  }
 }


}


