package com.example.demo;
  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.web.bind.annotation.RestController;  

import java.sql.*;

@RestController  
public class Controller 
{  
    @Autowired  
    JdbcTemplate sJdbcTemp;    

    @RequestMapping("/anbo")
    public String sAnbo() throws Exception
    {
      String sConnectionUrl = "jdbc:goldilocks://192.168.0.120:1024/galyk";
      Connection sConnection = DriverManager.getConnection(sConnectionUrl, "TEST", "test");
      sConnection.setAutoCommit(false);
      
      PreparedStatement sPstmtSelect = sConnection.prepareStatement("SELECT * from anbo;");
      sPstmtSelect.execute();
      sConnection.commit();

      sPstmtSelect.close();
      sConnection.close();

      return "Anbo is handsome.";
    }

    @RequestMapping("/anboCount")
    public String sAnboCount() throws Exception
    {
      int sRowCount = 0;

      String sConnectionUrl = "jdbc:goldilocks://192.168.0.120:1024/galyk";
      Connection sConnection = DriverManager.getConnection(sConnectionUrl, "TEST", "test");
      sConnection.setAutoCommit(false);

      Statement sStmtCount = sConnection.createStatement();
      ResultSet sResultSet = sStmtCount.executeQuery("SELECT count(*) from anbo");

      sResultSet.next();
      
      sRowCount = sResultSet.getInt(1);
      sConnection.commit();

      sResultSet.close();
      sStmtCount.close();
      sConnection.close();

      return "Row Count is = " + sRowCount;
    }
   
    @RequestMapping("/anboInsert")  
    public String sAnboInsert() throws Exception
    {  
      String sConnectionUrl = "jdbc:goldilocks://192.168.0.120:1024/galyk";
      Connection sConnection = DriverManager.getConnection(sConnectionUrl, "TEST", "test");
      sConnection.setAutoCommit(false);
      
      PreparedStatement sPstmtSelect = sConnection.prepareStatement("insert into anbo values('1','www.anbo.com')");
      sPstmtSelect.execute();
      sConnection.commit();

      sPstmtSelect.close();
      sConnection.close();

      return "Anbo is handsome.";
    } 

    @RequestMapping("/insertJdbcTemp")  
    public String sInsert()
    {  
      sJdbcTemp.execute("insert into anbo values('1','www.anbo.com')"); 

      return "data inserted Successfully";  
    }  

    @RequestMapping("/count")
    public String sCountIndex()
    {
      return "ANBO IS HANDSOME";
      /*sJdbcTemp.execute("select count(1) from anbo");*/
      /*sJdbcTemp.execute("select count(1) from anbo");*/

      /*return "data inserted Successfully";  */
    }
}  
