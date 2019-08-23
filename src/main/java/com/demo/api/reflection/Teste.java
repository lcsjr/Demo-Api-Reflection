package com.demo.api.reflection;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;

public class Teste {

	private static List<Pessoa> pessoas =  new ArrayList<>();
	
	static {
		pessoas.add(new Pessoa("Luiz",35));
		pessoas.add(new Pessoa("Cinthia",34));
		pessoas.add(new Pessoa("Davi",5));
		pessoas.add(new Pessoa("Hilda",62));
	}

	public static void main(String[] args) {
		List<String> nomes = pessoas.stream().map( Pessoa::getNome).collect(Collectors.toList());
		nomes.forEach(System.out::println);
		nomes.add(0, "Luiz Santos");
		System.out.println(" ");
		nomes.forEach(System.out::println);
	}
}

@Getter
class Pessoa {
	
	private String nome;
	private int age;

	public Pessoa(String nome, int age) {
		this.nome = nome;
		this.age = age;
	}
}