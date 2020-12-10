package com.oozeander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oozeander.model.PersonHbm;
import com.oozeander.repository.PersonHbmRepository;
import com.oozeander.service.PersonHbmService;

@Service("personHbmService")
@Transactional
public class PersonHbmServiceImpl implements PersonHbmService {
	@Autowired
	private PersonHbmRepository personHbmRepository;

	@Override
	public List<PersonHbm> get() {
		return personHbmRepository.findAll();
	}

	@Override
	public PersonHbm get(Long id) {
		return personHbmRepository.findOne(id);
	}

	@Override
	public void save(PersonHbm person) {
		personHbmRepository.saveAndFlush(person);
	}

	@Override
	public void update(Long id, PersonHbm person) {
		PersonHbm p = get(id);
		if (p != null) {
			p.setFirstname(person.getFirstname());
			p.setLastname(person.getLastname());
			p.setAge(person.getAge());
			p.setEmails(person.getEmails());
		}
	}

	@Override
	public void delete(Long id) {
		personHbmRepository.delete(id);
	}
}