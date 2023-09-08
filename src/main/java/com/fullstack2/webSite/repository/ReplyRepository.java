package com.fullstack2.webSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fullstack2.webSite.entity.QnABoard;
import com.fullstack2.webSite.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

	//이건 Select이 아니야! Up, Del 둘 중의 하나를 수행할거야 라는 어노테이션을 반드시 선언해야 함.
	@Modifying
	@Query("DELETE FROM Reply r WHERE r.qnaBoard.bno = :bno")
	void deleteByBno(@Param("bno") Long bno);
	
	// ReplyRepository.java
	List<Reply> getRepliesByQnaBoardOrderByRno(QnABoard qnaBoard);

	Long countByQnaBoard(QnABoard qnaBoard);

}
