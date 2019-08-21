package com.demo.api.reflection;

import com.demo.api.reflection.model.Person;
import com.demo.api.reflection.orm.EntityManager;

public class WritingObjects {

	public static void main(String[] args) {

		EntityManager<Person> entityManager = EntityManager.of(Person.class);
		
		Person linda = new Person("Linda",31);
		Person luiz = new Person("Luiz",35);
		Person cinthia = new Person("Cinthia",34);
		Person Davi = new Person("Davi",5);
		
		
		entityManager.persist(linda);
		entityManager.persist(luiz);
		entityManager.persist(cinthia);
		entityManager.persist(Davi);
		
		
	}

}
