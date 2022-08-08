package com.example.SpringLaptop.controller;

import com.example.SpringLaptop.SpringLaptopApplication;
import com.example.SpringLaptop.entities.Laptop;
import com.example.SpringLaptop.model.LaptopRepoitory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;




import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {

    @Autowired
    ApplicationContext applicationContext;
    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;
    @Autowired
    private LaptopRepoitory repoitory;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    /**
     * metodo inserta datos en el test
     */
    public void isertaDatos(){
        Laptop laptop1 = new Laptop(null,"Apple","Air",1299.0);
        Laptop laptop2 = new Laptop(null,"Huawei","matebook",1299.0);
        Laptop laptop3 = new Laptop(null,"Acer","zfp",1299.0);

        repoitory.save(laptop1);
        repoitory.save(laptop2);
        repoitory.save(laptop3);
        System.out.println(repoitory.findAll());
    }

    @Test
    void findAll() {

        isertaDatos();

      ResponseEntity <Laptop[]> response =
              testRestTemplate.getForEntity("/api/laptops",Laptop[].class);
      assertEquals(HttpStatus.OK, response.getStatusCode());
      assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getBookById() {
        isertaDatos();
        ResponseEntity <Laptop> response =
                testRestTemplate.getForEntity("/api/laptops/1",Laptop.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    void getBookByIdResponse() {
    }

    @Test
    void saveLaptop() {
    }

    @Test
    void saveLaptopHeaders() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void deleteAll() {
    }
}