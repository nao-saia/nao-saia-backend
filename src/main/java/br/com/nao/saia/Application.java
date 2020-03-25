package br.com.nao.saia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe responsável por realizar o boot da aplicação
 *
 * @author Taynan Rezende Silva
 * @since 22/03/2020
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
