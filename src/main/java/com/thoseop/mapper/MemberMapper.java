package com.thoseop.mapper;

import com.thoseop.entity.MemberEntity;
import com.thoseop.http.response.MemberResponse;

public interface MemberMapper<R, G> {

    G mapIt(R r);
    
    MemberMapper<MemberResponse, MemberEntity> mapToEntity = dto ->
        new MemberEntity(dto.getMemberId(), 
        		dto.getMemberName(), 
        		dto.getMemberEmail());
    
    MemberMapper<MemberEntity, MemberResponse> mapToResponse = entity ->
        new MemberResponse(entity.getId(), 
        		entity.getName(), 
        		entity.getEmail());
}
