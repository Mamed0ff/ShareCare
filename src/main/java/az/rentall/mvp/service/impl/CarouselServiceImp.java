package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.mapper.CarouselMapper;
import az.rentall.mvp.model.dto.request.CarouselRequest;
import az.rentall.mvp.model.dto.response.CarouselResponse;
import az.rentall.mvp.model.entity.Carousel;
import az.rentall.mvp.repository.CarouselRepository;
import az.rentall.mvp.service.CarouselService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarouselServiceImp implements CarouselService {
    private final CarouselRepository carouselRepository;
    private final AmazonS3Service  amazonS3Service;

    @Override
    public void create(CarouselRequest request, MultipartFile image) {
        Carousel entity = CarouselMapper.INSTANCE.requestToEntity(request);
        entity.setImageUrl(amazonS3Service.uploadFile(image));
        carouselRepository.save(entity);
    }

    @Override
    public void update(CarouselRequest request, Long id, MultipartFile image) {
        carouselRepository.findById(id).orElseThrow(()->new NotFoundException("CAROUSEL_NOT_FOUND"));
        Carousel entity = CarouselMapper.INSTANCE.requestToEntity(request);
        entity.setImageUrl(amazonS3Service.uploadFile(image));
        entity.setId(id);
        carouselRepository.save(entity);
    }

    @Override
    public CarouselResponse findById(Long id) {
        Carousel carousel=carouselRepository.findById(id).orElseThrow(()->new NotFoundException("CAROUSEL_NOT_FOUND"));
        return CarouselMapper.INSTANCE.entityToResponse(carousel);
    }

    @Override
    public List<CarouselResponse> getAll() {
        return CarouselMapper.INSTANCE.entitiesToResponses(carouselRepository.findAll());
    }

    @Override
    public void delete(Long id){
        Carousel carousel = carouselRepository.findById(id).orElseThrow(()->new NotFoundException("CAROUSEL_NOT_FOUND"));
        carouselRepository.delete(carousel);
    }
}
