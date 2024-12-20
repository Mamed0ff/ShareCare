package az.rentall.mvp.service.impl;

import az.rentall.mvp.mapper.ProductImageMapper;
import az.rentall.mvp.model.dto.request.ProductImagesRequest;
import az.rentall.mvp.model.dto.response.ProductImageResponse;
import az.rentall.mvp.model.entity.ProductImages;
import az.rentall.mvp.repository.ProductImagesRepository;
import az.rentall.mvp.service.ProductImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {

    private final AmazonS3Service  amazonS3Service;
    private final ProductImagesRepository productImagesRepository;


    @Override
    public void addImages(List<MultipartFile> images, Long productId) {
    boolean main = true;
    for(MultipartFile image : images ){
        ProductImagesRequest request =ProductImagesRequest.builder()
                .productId(productId)
                .path(amazonS3Service.uploadFile(image))
                .main(main)
                .build();
        ProductImages entity = ProductImageMapper.INSTANCE.requestToEntity(request);
        entity.setUploadDate(LocalDate.now());
        productImagesRepository.save(entity);
        main = false;
    }
    }

    @Override
    public void editImage(List<MultipartFile> images, Long productId) {
        List<ProductImages> productImages = productImagesRepository
                .findByProductIdAndStatus(productId, true);
        productImages.forEach(entity -> entity.setStatus(false));
        productImagesRepository.saveAll(productImages);
        addImages(images, productId);
    }

    @Override
    public List<ProductImageResponse> getIMages(Long productId) {
        List<ProductImages> entities = productImagesRepository
                .findByProductIdAndStatus(productId, true);
        return ProductImageMapper.INSTANCE.entitiesToResponses(entities);
    }
}
