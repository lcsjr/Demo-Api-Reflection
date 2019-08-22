package com.demo.api.reflection.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.demo.api.reflection.annotation.Column;
import com.demo.api.reflection.annotation.PrimaryKey;

public class Metamodel {

	private Class<?> clss;

	public static Metamodel of(Class<?> clss) {
		return new Metamodel(clss);		
	}

	public Metamodel(Class<?> clss) {
		this.clss = clss;
	}

	public PrimaryKeyField getPrimaryKey() {
		
		Field[] Fields = clss.getDeclaredFields();
		for (Field field : Fields){
			PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
			if(annotation != null) 
				return new PrimaryKeyField(field);
		}
		throw new IllegalArgumentException("No primary key found in class " + clss.getSimpleName());
	}

	public List<ColumnField> getColumns() {
		
		List<ColumnField> columnsFields = new ArrayList<>();
		Field[] Fields = clss.getDeclaredFields();
		for (Field field : Fields){
			Column annotation = field.getAnnotation(Column.class);
			if(annotation != null) 
				columnsFields.add(new ColumnField(field));
		}
		return columnsFields;
	}

	public String buildInsertRequest() {
		return "insert into " + this.clss.getSimpleName() + " ("+buildColumnNames()+") values (" + buildQuestionMarksElement() + ")" ;
	}

	private String buildQuestionMarksElement() {
		int numberOfColumns = getColumns().size()+1;
		return IntStream.range(0, numberOfColumns).mapToObj( index -> "?").collect(Collectors.joining(", "));
	}

	private String buildColumnNames() {
		String primeryKeyColumnName = getPrimaryKey().getName();
		List<String> columnNames = getColumns().stream().map(ColumnField::getName).collect(Collectors.toList());
		columnNames.add(0, primeryKeyColumnName);
		return String.join(", ", columnNames);
	}

}
