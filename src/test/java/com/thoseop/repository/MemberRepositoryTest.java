package com.thoseop.repository;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.thoseop.entity.MemberEntity;

@DisplayName("Testing MemberRepository")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @BeforeEach
    void setUp() throws Exception {}

    @DisplayName("test Find One By Id_when Existent Id_then Return Member Data")
    @ParameterizedTest
    @ValueSource(longs = {1l, 2l, 3l})
    void testFindOneById_whenExistentId_thenReturnMemberData(Long memberId) {
	MemberEntity entity = repository.findOneById(memberId).get();

	Assertions.assertNotNull(entity.getId(), 
		() -> "Member's id should not be null");
	Assertions.assertEquals(memberId, entity.getId(), 
		() -> "Member's id was not the expected");
	Assertions.assertNotNull(entity.getName(), 
		() -> "Member's name should not be null");
	Assertions.assertNotNull(entity.getEmail(),  
		() -> "Member's email should not be null");
    }

    @DisplayName("test Find One By Id_when Non Existent Id_then Throw No Such Element Exception")
    @Test
    void testFindOneById_whenNonExistentId_thenThrowNoSuchElementException() {
	// g
	Long memberId = 1000L;
	// w
	// t
	Assertions.assertThrows(NoSuchElementException.class, () -> {
            repository.findOneById(memberId).get();
	}, () -> "Should throw NoSuchElementException");
    }

    @DisplayName("test Find All Pageable_when Requested_then Return Members Data")
    @Test
    void testFindAllPageable_whenRequested_thenReturnMembersData() {
	// g
        Pageable pageable = PageRequest.of(0, 8, Sort.by(Direction.ASC, "id"));
	// w
        MemberEntity entity = repository.findAll(pageable).getContent().get(0);
        // t
	Assertions.assertNotNull(entity.getId(), 
            () -> "Member's id should not be null");
	Assertions.assertNotNull(entity.getName(), 
            () -> "Member's name should not be null");
	Assertions.assertNotNull(entity.getEmail(), 
            () -> "Member's email should not be null");
    }
}
