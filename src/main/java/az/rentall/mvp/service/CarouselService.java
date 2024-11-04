package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.CarouselRequest;
import az.rentall.mvp.model.dto.response.CarouselResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarouselService {

    public void create(CarouselRequest request, MultipartFile image);

    public void update(CarouselRequest request, Long id, MultipartFile image);

    public CarouselResponse findById(Long id);

    public List<CarouselResponse> getAll();

    public void delete(Long id);
}
