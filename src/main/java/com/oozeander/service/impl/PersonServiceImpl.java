package com.oozeander.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oozeander.model.Person;
import com.oozeander.repository.PersonRepository;
import com.oozeander.service.PersonService;

@Service("personService")
@Transactional
public class PersonServiceImpl implements PersonService {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public List<Person> get() {
		return personRepository.findAll();
	}

	@Override
	public Person get(Long id) {
		return personRepository.findOne(id);
	}

	@Override
	public void save(Person person) {
		personRepository.saveAndFlush(person);
	}

	@Override
	public void update(Long id, Person person) {
		Person p = get(id);
		if (p != null) {
			p.setFirstname(person.getFirstname());
			p.setLastname(person.getLastname());
			p.setAge(person.getAge());
			p.setEmails(person.getEmails());
		}
	}

	@Override
	public void delete(Long id) {
		personRepository.delete(id);
	}

	@Override
	public void updateFirstname(Long id, String firstname) {
		personRepository.updateFirstname(id, firstname);
	}
}