package com.thoseop.service;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thoseop.entity.MemberEntity;
import com.thoseop.http.response.MemberResponse;
import com.thoseop.mapper.MemberMapper;
import com.thoseop.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 
     * @param id
     * @return
     */
    public MemberResponse retrieveMember(Long id) {
	Optional<MemberEntity> entityOp = this.memberRepository.findOneById(id);

	if (entityOp.isPresent())
		return MemberMapper.mapToResponse.mapIt(entityOp.get());
	else	
	    throw new NoSuchElementException("Member not found"); 
    }

    /**
     * 
     * @param pageable
     * @return
     */
    public Page<MemberResponse> retrieveMembers(Pageable pageable) {

	Page<MemberEntity> membersList = memberRepository.findAll(pageable);
	Page<MemberResponse> resultList = membersList.map(entity -> 
		MemberMapper.mapToResponse.mapIt(entity)
	);

	return resultList;
    }
}
