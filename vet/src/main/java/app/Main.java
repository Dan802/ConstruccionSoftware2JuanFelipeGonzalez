package app;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.adapters.inputs.LoginInput;

@SpringBootApplication
public class Main implements CommandLineRunner{
    
    /*@Autowired
    private ListableBeanFactory beanFactory;*/

    @Autowired
    private LoginInput loginInput;

    @Override
    public void run(String... args) throws Exception {
        /*System.out.println("Beans registrados en la aplicación:");
        String[] beanNames = beanFactory.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }*/

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("**********************************************************************");
    	System.out.println("Trae a tu mascota antes de que solo podamos atenderla en espíritu.");
        System.out.println("Consulta hoy y evita un final trágicamente innecesario.");
        System.out.println();
        System.out.println("***********************   ¡BIENVENIDO!   ***********************");
        System.out.println();
        loginInput.menu();
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(Main.class, args);
        System.out.println();
        System.out.println("Proceso terminado.");
    }
}