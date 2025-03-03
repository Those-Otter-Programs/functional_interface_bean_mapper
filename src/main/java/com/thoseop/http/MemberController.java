package com.thoseop.http;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoseop.http.response.MemberResponse;
import com.thoseop.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public @ResponseBody ResponseEntity<MemberResponse> searchMember(@PathVariable Long id) {
	
	MemberResponse member = memberService.retrieveMember(id);
	
	return ResponseEntity.ok(member);
    }

    /**
     * 
     * @param page
     * @param size
     * @param sort
     * @return
     * @throws Exception
     */
    @GetMapping("/members")
    public @ResponseBody ResponseEntity<Page<MemberResponse>> getMembers(
	    @RequestParam(defaultValue = "0") Integer page, 
	    @RequestParam(defaultValue = "8") Integer size,
	    @RequestParam(defaultValue = "asc") String sort
	    ) throws Exception { 	
	
	Direction sortDirection = sort.equalsIgnoreCase("desc") ? Direction.DESC : Direction.ASC;
	Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));
	Page<MemberResponse> members = memberService.retrieveMembers(pageable);

	return ResponseEntity.ok(members);
    }
}
