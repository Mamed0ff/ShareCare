package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.service.ProductService;
import az.rentall.mvp.util.annotation.ValidImages;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
    public ProductResponse createProduct(@RequestPart("productRequest") @Valid ProductRequest productRequest,
                                         @RequestPart("images") @ValidImages List<MultipartFile> images) {
        return productService.createProduct(productRequest);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping
    public Page<ProductResponse> findAllProducts(@PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return productService.findAllProducts((java.awt.print.Pageable) pageable);
    }

    @PutMapping("/{id}")
    public void updateProduct (@RequestPart("productRequest") @Valid ProductRequest productRequest,
                               @RequestPart("images") @ValidImages List<MultipartFile> images, @PathVariable Long id){
        productService.updateProduct(productRequest, id);
    }

    @GetMapping
    public List<ProductResponse> searchProductsByName(@RequestParam("name") String name){
        return productService.searchProductsByName(name);
    }
}
