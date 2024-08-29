package com.mapping.app.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mapping.app.model.User;

public interface UserRepo extends JpaRepository<User, Long>{
	
	@Query("SELECT u FROM User u JOIN FETCH u.address WHERE u.id = :id")
    User findUserWithAddressById(@Param("id") long id);
	
	 @EntityGraph(attributePaths = {"address"})
	    Optional<User> findById(Long id);

}
