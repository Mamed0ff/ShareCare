package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.ProductRequest;
import az.rentall.mvp.model.dto.response.ProductResponse;
import az.rentall.mvp.service.ProductService;
import az.rentall.mvp.util.annotation.ValidImages;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestPart("productRequest")  @Valid ProductRequest productRequest,
                                         @RequestPart("images")   @ValidImages List<MultipartFile> images) {
        return productService.createProduct(productRequest,images);
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/all")
    public List<ProductResponse> findAllProducts(@PageableDefault(size = 20, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return productService.findAllProducts( pageable);
    }

    @PutMapping("/{id}")
    public void updateProduct (@RequestPart("productRequest") @Valid ProductRequest productRequest,
                               @RequestPart("images") @ValidImages List<MultipartFile> images, @PathVariable Long id){
        productService.updateProduct(productRequest, id,images);
    }

    @GetMapping("/search")
    public List<ProductResponse> searchProductsByName(@RequestParam("name") String name){
        return productService.searchProductsByName(name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
