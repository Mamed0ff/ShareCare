package az.rentall.mvp.service.impl;

import az.rentall.mvp.exception.NotFoundException;
import az.rentall.mvp.mapper.FaqsMapper;
import az.rentall.mvp.model.dto.request.FaqsRequest;
import az.rentall.mvp.model.dto.response.FaqsResponse;
import az.rentall.mvp.model.entity.CategoriesEntity;
import az.rentall.mvp.model.entity.Faqs;
import az.rentall.mvp.repository.CategoriesRepository;
import az.rentall.mvp.repository.FaqsRepository;
import az.rentall.mvp.service.FaqsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FaqsServiceImpl implements FaqsService {

    private final FaqsRepository faqsRepository;
    private final CategoriesRepository categoriesRepository;


    @Override
    public void createFaq(FaqsRequest request) {
        CategoriesEntity category = categoriesRepository.findById(request.getCategoryId()).orElseThrow(()->new NotFoundException("Cannot find category with id:"+request.getCategoryId()));
        faqsRepository.save(FaqsMapper.INSTANCE.requestToEntity(request));
    }

    @Override
    public FaqsResponse updateFaq(FaqsRequest request, Long faqId) {
        Faqs foundFaq = faqsRepository.findById(faqId).orElseThrow(()->new NotFoundException("Faq not found with id: "+faqId));
        FaqsMapper.INSTANCE.mapRequestToEntity(foundFaq,request);
        faqsRepository.save(foundFaq);
        return FaqsMapper.INSTANCE.entityToResponse(foundFaq);
    }

    @Override
    public List<FaqsResponse> getAllFaqs() {
        List<Faqs> faqs = faqsRepository.findAll();
        return FaqsMapper.INSTANCE.entitiesToResponses(faqs);
    }

    @Override
    public void deleteFaq(Long faqId) {
        Faqs foundFaq = faqsRepository.findById(faqId).orElseThrow(()->new NotFoundException("Faq not found with id: "+faqId));
        faqsRepository.delete(foundFaq);
    }
}
