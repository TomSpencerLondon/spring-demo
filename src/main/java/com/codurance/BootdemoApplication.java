package com.codurance;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BootdemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootdemoApplication.class, args);
  }
}

@RestController
@RequestMapping("/")
class RestApiDemoController {
  private List<Coffee> coffees = new ArrayList<>();

  public RestApiDemoController(){
    coffees.addAll(List.of(
        new Coffee("Café Cereza"),
        new Coffee("Café Ganador"),
        new Coffee("Café Lareño"),
        new Coffee("Café Três Pontas")
    ));
  }

  @GetMapping("/coffees")
  Iterable<Coffee> getCoffees(){
    return coffees;
  }

  @GetMapping("/coffees/{id}")
  Optional<Coffee> getCoffeeById(@PathVariable String id) {
    for (Coffee c : coffees){
      if (c.getId().equals(id)){
        return Optional.of(c);
      }
    }

    return Optional.empty();
  }

  @PostMapping("/coffees")
  Coffee postCoffee(@RequestBody Coffee coffee) {
    coffees.add(coffee);
    return coffee;
  }

  @PutMapping("/coffees/{id}")
  ResponseEntity<Coffee> putCoffee(@PathVariable String id, @RequestBody Coffee coffee) {
    int coffeeIndex = -1;

    for (Coffee c : coffees){
      if (c.getId().equals(id)){
        coffeeIndex = coffees.indexOf(c);
        coffees.set(coffeeIndex, coffee);
      }
    }
    return (coffeeIndex == -1) ?
        new ResponseEntity<Coffee>(postCoffee(coffee), HttpStatus.CREATED) :
        new ResponseEntity<Coffee>(coffee, HttpStatus.OK);

  }

  @DeleteMapping("/coffees/{id}")
  void deleteCoffee(@PathVariable String id) {
    coffees.removeIf(c -> c.getId().equals(id));
  }
}

class Coffee {
  private final String id;
  private String name;

  public Coffee(String id, String name){
    this.id = id;
    this.name = name;
  }

  public Coffee(String name){
    this(UUID.randomUUID().toString(), name);
  }

  public String getId(){
    return id;
  }

  public String getName(){
    return name;
  }

  public void setName(String name){
    this.name = name;
  }
}
