package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping
    public List<ProductResponse> findAllProducts(){
        return productService.findAllProducts();
    }

    @PutMapping("/{id}")
    public void updateProduct (@RequestBody ProductRequest productRequest, @PathVariable Long id){
        productService.updateProduct(productRequest, id);
    }

    @GetMapping
    public List<ProductResponse> searchProductsByName(@RequestParam("name") String name){
        return productService.searchProductsByName(name);
    }
}
