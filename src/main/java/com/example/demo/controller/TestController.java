package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.Sunje;
  
@RestController
public class TestController {
    @Autowired
    JdbcTemplate sJdbcTemp;

    @RequestMapping("/insert")
    public String index(){
        sJdbcTemp.execute("insert into sunje values(seq_sunje.NEXTVAL,'www.sunje.com')");

        System.out.println("sunje hello");
        return"data inserted Successfully";
    }

    @GetMapping("/select")
    public ModelAndView getSunje(){
        String sql = "SELECT * FROM sunje";
        List<Sunje> sunjeList = sJdbcTemp.query(sql, (rs, rowNum) -> {
            Sunje sunje = new Sunje();
            sunje.setId(rs.getLong("id"));
	    sunje.setEmail(rs.getString("email"));
            return sunje;
        });

        ModelAndView modelAndView = new ModelAndView("sunjeList");
        modelAndView.addObject("sunjeList", sunjeList);

        return modelAndView;
    }
}


