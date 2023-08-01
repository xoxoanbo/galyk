package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		/*SpringApplication.run(DemoApplication.class, args);*/

		SpringApplication anbo = new SpringApplication(DemoApplication.class);

		anbo.run(args);
        
        /*System.out.println("Anbo is handsome");*/
	}

}
