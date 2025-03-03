package com.thoseop.http;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import com.thoseop.http.response.MemberResponse;

@DisplayName("Testing MemberController")
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MemberControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    void setUp() throws Exception {}

    @DisplayName("test Search Member_when Existent Member Id_then Returns Member Data")
    @ParameterizedTest
    @ValueSource(longs = {1l, 2l, 3l})
    void testSearchMember_whenExistentMemberId_thenReturnsMemberData(Long memberId) {
	// g
	String route = "/%d".formatted(memberId);

	// creating the headers for the requestString
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(List.of(MediaType.APPLICATION_JSON));

	HttpEntity<?> request = new HttpEntity<>(
		null, headers);
	// w
	ResponseEntity<MemberResponse> response = testRestTemplate
		.exchange(route, HttpMethod.GET, request, MemberResponse.class);
	// t
	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(),
		() -> "The returned http status code was not the expected.");
	Assertions.assertNotNull(response.getBody().getMemberId(), 
		() -> "Member's id should not be null");
	Assertions.assertNotNull(response.getBody().getMemberName(), 
		() -> "Member's name should not be null");
	Assertions.assertNotNull(response.getBody().getMemberEmail(), 
		() -> "Member's email should not be null");
    }
    
    @DisplayName("test Get Members_when Right Pagination Attributes_then Returns Paginated List Of Members")
    @ParameterizedTest
    @ValueSource(strings = {
	    "/members?page=0&size=8&sort=asc",
	    "/members?page=0&size=8",
	    "/members?page=0",
	    "/members"
	    })
    void testGetMembers_whenRightPaginationAttributes_thenReturnsPaginatedListOfMembers(String route) {
	// g
	// creating the headers for the request
	HttpHeaders headers = new HttpHeaders();
	headers.setAccept(List.of(MediaType.APPLICATION_JSON));

	HttpEntity<?> request = new HttpEntity<>(null, headers);
	// w
	ResponseEntity<CustomTestsPageImpl<MemberResponse>> response = testRestTemplate
		.exchange(route, HttpMethod.GET, request,
			new ParameterizedTypeReference<CustomTestsPageImpl<MemberResponse>>() {
		});
	
        Page<MemberResponse> pagedMembers = response.getBody();        
        
        List<MemberResponse> members = pagedMembers.getContent().stream().collect(Collectors.toList());
        MemberResponse m1 = members.get(0);
	
	// t
	Assertions.assertEquals(HttpStatus.OK, response.getStatusCode(), 
		() -> "The returned http status code returned was not the expected.");

	Assertions.assertEquals(1, pagedMembers.getSize(), 
		() -> "The page size was not the expected");
	Assertions.assertTrue(pagedMembers.getTotalElements() >= 3, 
		() -> "The total number of elements was not the expected");
	Assertions.assertTrue(pagedMembers.getTotalPages() >= 1, 
		() -> "The total number of pages was not the expected");
	Assertions.assertEquals(0, pagedMembers.getNumber(), 
		() -> "The page number was not the expected");
	
	Assertions.assertNotNull(m1.getMemberName(), 
		() -> "Member's id should not be null");
	Assertions.assertNotNull(m1.getMemberName(), 
		() -> "Member's name should not be null");
	Assertions.assertNotNull(m1.getMemberEmail(), 
		() -> "Member's email should not be null");
    }
}
