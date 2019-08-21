package com.demo.api.reflection.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

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
			if(annotation != null) {
				PrimaryKeyField primaryKeyField = new PrimaryKeyField(field);
				
				return primaryKeyField;
			}
		}
		
		throw new IllegalArgumentException("No primary key found in class " + clss.getSimpleName());
	}

	public List<ColumnField> getColumns() {
		
		List<ColumnField> columnsFields = new ArrayList<>();
		
		Field[] Fields = clss.getDeclaredFields();
		for (Field field : Fields){
			Column annotation = field.getAnnotation(Column.class);
			if(annotation != null) {
				ColumnField column = new ColumnField(field);
				columnsFields.add(column);
			}
		}
		
		return columnsFields;
	}

	public String buildInsertRequest() {
		
		return null;
	}

}
