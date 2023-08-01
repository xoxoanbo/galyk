package com.example.demo;
  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.web.bind.annotation.RestController;  

@RestController  
public class Controller {  
    @Autowired  
    JdbcTemplate sJdbcTemp;    

    @RequestMapping("/insert")  
    public String index()
        {  
          sJdbcTemp.execute("insert into anbo values('1','www.anbo.com')"); 

          /*System.out.println("Anbo is handsome"); */
          return "data inserted Successfully";  
        }  
    @RequestMapping("/count")
    public String index()
        {
          sJdbcTemp.execute("select count(1) from anbo");

          /*System.out.println("Anbo is handsome"); */
          return "data inserted Successfully";  
        }
}  
