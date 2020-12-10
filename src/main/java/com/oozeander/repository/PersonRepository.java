package com.oozeander.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.oozeander.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{
	@Modifying
	@Query(value = "UPDATE Person p SET p.firstname = :firstname WHERE person_id = :id", nativeQuery = false)
	void updateFirstname(@Param("id") Long id, @Param("firstname") String firstname);
}