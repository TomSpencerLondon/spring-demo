package com.codurance;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class BootdemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(BootdemoApplication.class, args);
  }
}

@RestController
@RequestMapping("/coffees")
class RestApiDemoController {
  private List<Coffee> coffees = new ArrayList<>();
}

class Coffee {
  private final String id;
  private String name;

  public Coffee(String id, String name){
    this.id = id;

  }
}
