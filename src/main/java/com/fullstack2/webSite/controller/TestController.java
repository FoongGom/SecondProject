package com.fullstack2.webSite.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fullstack2.webSite.dtos.QnAPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.entity.QnA;
import com.fullstack2.webSite.entity.Review;
import com.fullstack2.webSite.repository.QnARepository;
import com.fullstack2.webSite.repository.ReviewRepository;
import com.fullstack2.webSite.service.QnAService;
import com.fullstack2.webSite.service.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TestController {
	
	@Autowired
	private final ReviewService reviewService;
	@Autowired
	private final ReviewRepository reviewRepository;
	
	@Autowired
	private final QnAService qnaService;
	@Autowired
	private final QnARepository qnaRepository;
	
	@GetMapping("/test")
	public void test(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO, Model model) {
		//Model에게 review란에 뿌려질 항목 DTO를 전달함.
		model.addAttribute("result", reviewService.getList(reviewPageRequestDTO));
	}
	
	@GetMapping("/test/review")
	public void getReviewData(@ModelAttribute("reviewPageRequestDTO") ReviewPageRequestDTO reviewPageRequestDTO, Model model) {
		// 리뷰 데이터를 가져오는 메서드
		model.addAttribute("result", reviewService.getList(reviewPageRequestDTO));
	}
	
	@GetMapping("/test/qna")
	public void getQnAData(@ModelAttribute("qnaPageRequestDTO") QnAPageRequestDTO qnaPageRequestDTO, Model model) {
		// QnA 데이터를 가져오는 메서드
		model.addAttribute("result", qnaService.getList(qnaPageRequestDTO));
	}
	
	@PostMapping("/addReview")
	public String addReview(@RequestParam String text) {
		// 현재 로그인한 사용자의 정보를 가져옵니다.
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	    // 인증 정보에서 사용자 이름을 가져옵니다.
	    String reviewer = authentication.getName();
		
		// 엔티티 생성 및 데이터 저장
        Review review = new Review();
        review.setText(text);
        review.setReviewer(reviewer);
        review.setModDate(LocalDateTime.now());
        review.setRegDate(LocalDateTime.now());

        // 리뷰 저장
        reviewRepository.save(review);

        return "redirect:/test"; // 리다이렉트할 페이지 지정
	}
	
	@PostMapping("/addQnA")
	public String addQnA(@RequestParam String text) {
		//현재 로그인 한 사용자의 정보를 가져옴
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		//인증 정보에서 사용자 이름을 가져옴
		String questioner = authentication.getName();
		
		//엔티티 생성 및 데이터 저장
		QnA qna = new QnA();
		qna.setText(text);
		qna.setQuestioner(questioner);
		qna.setModDate(LocalDateTime.now());
		qna.setRegDate(LocalDateTime.now());
		
		qnaRepository.save(qna);
		
		return "redirect:/test";
	}
}
