package com.thoseop.repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.thoseop.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long>{

    Optional<MemberEntity> findOneById(Long id);

    Page<MemberEntity> findAll(Pageable pageable);
}
