package com.demo.api.reflection;

import com.demo.api.reflection.model.Person;
import com.demo.api.reflection.orm.EntityManager;

public class WritingObjects {

	public static void main(String[] args) throws Exception {

		EntityManager<Person> entityManager = EntityManager.of(Person.class);
		
		Person linda = new Person("Linda",31);
		Person luiz = new Person("Luiz",35);
		Person cinthia = new Person("Cinthia",34);
		Person davi = new Person("Davi",5);
		
		System.out.println(linda);
		System.out.println(luiz);
		System.out.println(cinthia);
		System.out.println(davi);
		
		entityManager.persist(linda);
		entityManager.persist(luiz);
		entityManager.persist(cinthia);
		entityManager.persist(davi);
		
		System.out.println(linda);
		System.out.println(luiz);
		System.out.println(cinthia);
		System.out.println(davi);		
		
		
	}

}
