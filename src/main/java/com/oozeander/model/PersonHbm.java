package com.oozeander.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data @NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class PersonHbm {
	private Long id;
	@NonNull
	private String firstname;
	@NonNull
	private String lastname;
	@NonNull private Integer age;
	@NonNull
	private Set<String> emails;
}