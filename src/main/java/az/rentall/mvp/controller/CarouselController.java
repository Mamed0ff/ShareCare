package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.CarouselRequest;
import az.rentall.mvp.model.dto.response.CarouselResponse;
import az.rentall.mvp.service.CarouselService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carousel")
public class CarouselController {

    private final CarouselService carouselService;

    @PostMapping
    public void create(@RequestPart("request") CarouselRequest request,
                       @RequestPart("image") MultipartFile image){

        carouselService.create(request,image);
    }

    @GetMapping("/{id}")
    public CarouselResponse getCarousel(@PathVariable Long id){
        return carouselService.findById(id);
    }

    @PutMapping("/{id}")
    public void update(@RequestPart("request") CarouselRequest request,
                       @RequestPart("image") MultipartFile image, @PathVariable Long id){

        carouselService.update(request,id,image);
    }

    @GetMapping("/all")
    public List<CarouselResponse> getAll(){
        return carouselService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        carouselService.delete(id);
    }
}
