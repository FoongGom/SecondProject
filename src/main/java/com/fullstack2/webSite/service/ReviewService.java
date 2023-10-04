package com.fullstack2.webSite.service;

import com.fullstack2.webSite.dtos.ReviewDTO;
import com.fullstack2.webSite.dtos.ReviewPageRequestDTO;
import com.fullstack2.webSite.dtos.ReviewPageResultDTO;
import com.fullstack2.webSite.entity.Product;
import com.fullstack2.webSite.entity.Review;

public interface ReviewService {

	//신규 리뷰 등록
	Long review(ReviewDTO dto);
	
	//리뷰란에서 페이지에 해당하는 글 목록 조회 리스트 get
	ReviewPageResultDTO<ReviewDTO, Review> getList(ReviewPageRequestDTO pageRequestDTO);
	
	//DTO를 Entity로 변환
	default Review dtoToEntity(ReviewDTO dto) {
		
		Product product = Product.builder()
				.itemcount(dto.getItemcount())
				.build();
		
		Review review = Review.builder()
				.rno(dto.getRno())
				.reviewer(dto.getReviewer())
				.text(dto.getText())
				.content(dto.getContent())
				.product(product)
				.build();
		return review;
	}
	
	//위 Object[] Entity를 DTO로 변환
	default ReviewDTO entityToDTO(Review review) {
		
		ReviewDTO dto = ReviewDTO.builder()
				.rno(review.getRno())
				.reviewer(review.getReviewer())
				.text(review.getText())
				.regDate(review.getRegDate())
				.modDate(review.getModDate())
				.content(review.getContent())
				.build();
		
		return dto;
	}
	
}
