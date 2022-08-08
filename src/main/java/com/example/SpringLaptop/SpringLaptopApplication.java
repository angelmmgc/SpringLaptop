package com.example.SpringLaptop;

import com.example.SpringLaptop.entities.Laptop;
import com.example.SpringLaptop.model.LaptopRepoitory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringLaptopApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringLaptopApplication.class, args);
		LaptopRepoitory repository = context.getBean(LaptopRepoitory.class);

		Laptop laptop1 = new Laptop(null,"Apple","Air",1299.0);
		Laptop laptop2 = new Laptop(null,"Huawei","matebook",1299.0);
		Laptop laptop3 = new Laptop(null,"Acer","zfp",1299.0);

		repository.save(laptop1);
		repository.save(laptop2);
		repository.save(laptop3);

		System.out.println("aplicacion ejecutada");

	}

}
