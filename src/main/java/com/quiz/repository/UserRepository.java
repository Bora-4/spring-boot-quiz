package com.quiz.repository;

import com.quiz.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    public UserEntity getUserEntitiesByUsername(@Param("username") String username);

//    This interface is a subtype of CrudRepository defined by Spring Data JPA so Spring will
//    generate implementation class at runtime. We define the getUserByUsername() method
//    annotated by a JPA query to be used by Spring Security for authentication.

}
