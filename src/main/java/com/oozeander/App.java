package com.oozeander;

import java.util.Arrays;
import java.util.HashSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.oozeander.model.Person;
import com.oozeander.model.PersonHbm;
import com.oozeander.service.PersonHbmService;
import com.oozeander.service.PersonService;

public class App {
	private static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-jpa.xml");
		// new AnnotationConfigApplicationContext(JavaConfig.class);
		ctx.registerShutdownHook();

		PersonService personService = ctx.getBean(PersonService.class);
		Arrays.asList(
				new Person("Billel", "KETROUCI", Integer.valueOf(24), new HashSet<String>(Arrays.asList("billel.ketrouci@gmail.com", "billel.ketrouci@outlook.fr"))),
				new Person("El Bakay", "BOURAJOINI", Integer.valueOf(27), new HashSet<String>(Arrays.asList("elbakay@gmail.com"))),
				new Person("Meriem", "KECHEROUD", Integer.valueOf(25), new HashSet<String>(Arrays.asList("meriem.kecheroud@gmail.com"))),
				new Person("Océane", "AKIYEMI", Integer.valueOf(24), new HashSet<String>(Arrays.asList("anne.parrose@gmail.com")))
				).stream().forEach(personService::save);
		LOGGER.info(personService.get());
		personService.update(1L, new Person("Oozeander", "Billel KETROUCI", 24, new HashSet<String>(Arrays.asList("billel.ketrouci@soprasteria.com"))));
		LOGGER.info(personService.get(1L));
		personService.delete(4L);
		LOGGER.info(personService.get());
		personService.updateFirstname(2L, "Bakay");
		LOGGER.info(personService.get(2L));

		// HBM

		PersonHbmService personHbmService = ctx.getBean(PersonHbmService.class);
		Arrays.asList(
				new PersonHbm("Billel", "KETROUCI", Integer.valueOf(24), new HashSet<String>(Arrays.asList("billel.ketrouci@gmail.com", "billel.ketrouci@outlook.fr"))),
				new PersonHbm("El Bakay", "BOURAJOINI", Integer.valueOf(27), new HashSet<String>(Arrays.asList("elbakay@gmail.com"))),
				new PersonHbm("Meriem", "KECHEROUD", Integer.valueOf(25), new HashSet<String>(Arrays.asList("meriem.kecheroud@gmail.com"))),
				new PersonHbm("Océane", "AKIYEMI", Integer.valueOf(24), new HashSet<String>(Arrays.asList("anne.parrose@gmail.com")))
				).stream().forEach(personHbmService::save);
		LOGGER.info(personHbmService.get());
		personHbmService.update(1L, new PersonHbm("Oozeander", "Billel KETROUCI", 24, new HashSet<String>(Arrays.asList("billel.ketrouci@soprasteria.com"))));
		LOGGER.info(personHbmService.get(1L));
		personHbmService.delete(4L);
		LOGGER.info(personHbmService.get());

		ctx.close();
	}
}