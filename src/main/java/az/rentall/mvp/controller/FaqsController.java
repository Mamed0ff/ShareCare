package az.rentall.mvp.controller;

import az.rentall.mvp.model.dto.request.FaqsRequest;
import az.rentall.mvp.model.dto.response.FaqsResponse;
import az.rentall.mvp.service.FaqsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faqs")
public class FaqsController {

    private final FaqsService faqsService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createFaq(@RequestBody @Valid FaqsRequest request){
        faqsService.createFaq(request);
    }

    @GetMapping("/all")
    public List<FaqsResponse> getAllFaqs(){
        return faqsService.getAllFaqs();
    }

    @PutMapping("/{id}")
    public void updateFaq(@PathVariable Long id, @RequestBody @Valid FaqsRequest request){
        faqsService.updateFaq(request,id);
    }

    @DeleteMapping("/{id}")
    public void deleteFaqs(@PathVariable Long id){
        faqsService.deleteFaq(id);
    }

    @GetMapping("/{id}")
    public FaqsResponse getFaqById(@PathVariable Long id){
       return faqsService.getFaqById(id);
    }
}
