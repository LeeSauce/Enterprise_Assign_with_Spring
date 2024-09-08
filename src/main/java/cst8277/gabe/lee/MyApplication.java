package cst8277.gabe.lee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        var app = new SpringApplication(MyApplication.class);
        app.run(args);
    }

}