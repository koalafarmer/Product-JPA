package ma.dante.ioc2.web;

import lombok.AllArgsConstructor;
import ma.dante.ioc2.entities.Product;
import ma.dante.ioc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor


public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products")
    public String addProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/products";
    }

    @GetMapping("/products")
    public String addProduct(Model model){
        List<Product> products=productRepository.findAll();
        model.addAttribute("products",products);
        return "products";
    }

    @GetMapping("/products/{productId}")
    public String getProduct(@PathVariable Long productId, Model model){
       Product product=productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Id Produit Invalide"));
        model.addAttribute("product",product);
        return "productDetails";
    }

    @GetMapping("/products/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        List<Product> products = productRepository.findByNameContaining(keyword);
        model.addAttribute("products", products);
        return "products";
    }

    @PostMapping("/products/{productId}")
    public String updateProduct(@PathVariable Long productId, @ModelAttribute Product updatedProduct) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Invalid product ID"));
        product.setName(updatedProduct.getName());
        product.setPrice(updatedProduct.getPrice());
        product.setQuantity(updatedProduct.getQuantity());
        productRepository.save(product);
        return "redirect:/products";
    }

    @PostMapping("/products/{productId}/delete")
    public String deleteProduct(@PathVariable Long productId) {
        productRepository.deleteById(productId);
        return "redirect:/products";
    }




}
