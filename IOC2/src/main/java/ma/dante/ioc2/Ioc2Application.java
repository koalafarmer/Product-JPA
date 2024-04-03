package ma.dante.ioc2;

import ma.dante.ioc2.entities.Product;
import ma.dante.ioc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.util.List;

@SpringBootApplication
public class Ioc2Application implements CommandLineRunner {
    @Autowired
private ProductRepository productRepository;
    public static void main(String[] args) {
        SpringApplication.run(Ioc2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        productRepository.save(new Product(null,"PC",5000,10));
        productRepository.save(new Product(null,"MOUSE",70,30));
        productRepository.save(new Product(null,"KEYBOARD",100,15));
        List<Product> products =productRepository.findAll();
        products.forEach(p->{
            System.out.println(p.toString());
        });

        Product product=productRepository.findById(Long.valueOf(1)).get();
        System.out.println("*************");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getQuantity());
        System.out.println("*************");
        List<Product> ProductList=productRepository.findByNameContains("C");
        ProductList.forEach(p->{
            System.out.println(p);
        });



    }
}
