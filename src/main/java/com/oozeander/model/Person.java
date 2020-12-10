package com.oozeander.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity @Table(name = "persons", schema = "default_schema", uniqueConstraints = {@UniqueConstraint(columnNames = {"person_id"})})
public class Person {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id @Column(name = "person_id")
	private Long id;

	@Column(name = "person_firstname", nullable = false,length = 16)
	@NonNull private String firstname;

	@Column(name = "person_lastname", nullable = false,length = 32)
	@NonNull private String lastname;

	@Column(name = "person_age", nullable = false)
	@NonNull private Integer age;

	@ElementCollection
	@CollectionTable(name = "user_emails", schema = "default_schema", joinColumns = {@JoinColumn(name = "person_id")})
	@NonNull @Column(name = "email")
	private Set<String> emails;
}