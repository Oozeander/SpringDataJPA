package com.oozeander.service;

import java.util.List;

import com.oozeander.model.Person;

public interface PersonService {
	/**
	 * Renvoie la liste de toutes les personnes
	 *
	 * @return liste de personnes
	 */
	List<Person> get();

	/**
	 * Renvoie une personne depuis son ID
	 *
	 * @param id
	 * @return personne
	 */
	Person get(Long id);

	/**
	 * Suavegarder une personne
	 *
	 * @param person
	 */
	void save(Person person);

	/**
	 * Modifié une personne depuis son ID avec de nouvelles
	 * données présentes dans un autre POJO Person
	 *
	 * @param id
	 * @param person
	 */
	void update(Long id, Person person);

	/**
	 * Supprimer une personne depuis son ID
	 *
	 * @param id
	 */
	void delete(Long id);

	/**
	 * Modifie la liste d'emails d'une personne depuis son ID
	 *
	 * @param id
	 * @param emails
	 */
	void updateFirstname(Long id, String firstname);
}