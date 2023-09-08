package com.fullstack2.webSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack2.webSite.entity.Member;



public interface MemberRepository extends JpaRepository<Member, String> {

}
