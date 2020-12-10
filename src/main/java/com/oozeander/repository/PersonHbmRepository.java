package com.oozeander.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oozeander.model.PersonHbm;

public interface PersonHbmRepository extends JpaRepository<PersonHbm, Long>{}