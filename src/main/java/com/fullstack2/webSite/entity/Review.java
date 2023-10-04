package com.fullstack2.webSite.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "product")
public class Review extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rno;
	private String text;
	private String reviewer;
	private String content;

	@ManyToOne(fetch = FetchType.LAZY) // 다대일 관계 설정
    private Product product; // Product 엔티티와의 관계

	
	public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getContent() {
    	return content;
    }
    
    public void setContent(String content) {
    	this.content = content;
    }

	public void setProduct(Product product) {
		this.product = product;
	}

}
