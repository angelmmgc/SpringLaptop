package com.example.SpringLaptop.controller;

import com.example.SpringLaptop.entities.Laptop;
import com.example.SpringLaptop.model.LaptopRepoitory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private final Logger log = LoggerFactory.getLogger(LaptopController.class);

    LaptopRepoitory laptopRepoitory;

    /**
     * metdodo constructor
     * @param laptopRepoitory
     */
    public LaptopController(LaptopRepoitory laptopRepoitory) {
        this.laptopRepoitory = laptopRepoitory;
    }

    //recupera todos los libros
    @GetMapping("/api/laptops")
    public List<Laptop> findAll(){
        return laptopRepoitory.findAll();

    }

    //recupera libro por id
    @GetMapping("/api/laptops/{id}")
    @ApiOperation("Buscar un laptop por clave primaria id Long")
    public Laptop getLaptopById(@ApiParam("Clave primaria tipo Long") @PathVariable Long id){


        Optional<Laptop> laptopOpt = laptopRepoitory.findById(id);

        //opcion 1
        if(laptopOpt.isPresent()){
            return laptopOpt.get();
        }else{
            return null;
        }
        //opcion 2
        //return laptopOpt.orElse(null);
    }

    //recupera libros por id

    /**
     * {id}--> passa la varriable de la url al parametro id
     * @PathVariable-->esta anotacion carga la variable {id}
     * @param id
     * @return
     */
    @GetMapping("/api/laptopsResponseEntity/{id}")
    public ResponseEntity<Laptop>getBookByIdResponse(@PathVariable Long id){

        Optional<Laptop> laptopOpt = laptopRepoitory.findById(id);//optional envuelve el objeto de la clase laptop o tiene un null

        if(laptopOpt.isPresent()){
            return ResponseEntity.ok(laptopOpt.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }



    //insertamos un libro en la base de datos
    @PostMapping("/api/save")
    public Laptop saveLaptop(@RequestBody Laptop laptop){
        //guardo el laptop que paso por parametro en la base de datos

        return laptopRepoitory.save(laptop);//el laptop devuelto tiene una clave primaria

    }

    /**
     * LOS METODOS CREAR Y ACTUALIZAR LLEVAN LA MISMA URL PERO NO COLISIIONAN YA QUE LOS METODOS DE PETICION SON DIFERENTES,POST AND PUT
     * @param laptop
     * @param headers
     * @return
     */

    @PostMapping("/api/create")
    public ResponseEntity<Laptop> saveLaptopHeaders(@RequestBody Laptop laptop, @RequestHeader HttpHeaders headers){

        System.out.println(headers.get("User-Agent"));
        //guardo el laptop que paso por parametro

        if(laptop.getId()!=null){//quiere decir que existe el id y por lo tanto no se esta creando nuevo
            log.warn("trying to create a laptop with id");
            System.out.println("trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepoitory.save(laptop);
        return ResponseEntity.ok(result);


    }

    /**
     * actualizar un laptop existente en base de datos
     */
    @PutMapping("/api/create")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){

        if (laptop.getId() == null){//si no tiene id es una creacion por lo que este metodo no es el indicado
            log.warn("trying to update a non existent laptop");
            return ResponseEntity.badRequest().build();//se esta enviando mal la peticion error 400


        }
        if(!laptopRepoitory.existsById(laptop.getId())){//si no existe el id

            log.warn("trying to update a non existent laptop");
            return ResponseEntity.notFound().build();// no existe el id error 404
        }
        Laptop resutl =  laptopRepoitory.save(laptop);

        return ResponseEntity.ok(resutl);
    }

    //borramos laptop por id
    @ApiIgnore//ignorar este metodo para que no aparezca en la documentacion de swagger
    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){

        if(!laptopRepoitory.existsById(id)){//si no existe el id

            log.warn("trying to delete a non existent laptop");
            return ResponseEntity.notFound().build();// no existe el id error 404
        }

        laptopRepoitory.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    //borramos todos los latops de la base de datos
    @ApiIgnore//ignorar este metodo para que no aparezca en la documentacion de swagger
    @DeleteMapping("/api/laptop")
    public ResponseEntity<Laptop> deleteAll(){

        log.info("REST Request for Deleting all laptops");
        laptopRepoitory.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
