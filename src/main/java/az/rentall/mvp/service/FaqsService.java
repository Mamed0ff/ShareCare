package az.rentall.mvp.service;

import az.rentall.mvp.model.dto.request.FaqsRequest;
import az.rentall.mvp.model.dto.response.FaqsResponse;

import java.util.List;

public interface FaqsService {
    public void createFaq(FaqsRequest request);
    public FaqsResponse updateFaq(FaqsRequest request, Long faqId);
    public List<FaqsResponse> getAllFaqs();
    public void deleteFaq(Long faqId);
}
