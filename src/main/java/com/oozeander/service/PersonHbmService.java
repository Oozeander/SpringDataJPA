package com.oozeander.service;

import java.util.List;

import com.oozeander.model.PersonHbm;

public interface PersonHbmService {
	/**
	 * Renvoie la liste de toutes les personnes
	 *
	 * @return liste de personnes
	 */
	List<PersonHbm> get();

	/**
	 * Renvoie une personne depuis son ID
	 *
	 * @param id
	 * @return personne
	 */
	PersonHbm get(Long id);

	/**
	 * Suavegarder une personne
	 *
	 * @param person
	 */
	void save(PersonHbm person);

	/**
	 * Modifié une personne depuis son ID avec de nouvelles
	 * données présentes dans un autre POJO Person
	 *
	 * @param id
	 * @param person
	 */
	void update(Long id, PersonHbm person);

	/**
	 * Supprimer une personne depuis son ID
	 *
	 * @param id
	 */
	void delete(Long id);
}