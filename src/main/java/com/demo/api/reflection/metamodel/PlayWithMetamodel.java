package com.demo.api.reflection.metamodel;

import java.util.List;

import com.demo.api.reflection.model.Person;
import com.demo.api.reflection.util.ColumnField;
import com.demo.api.reflection.util.Metamodel;
import com.demo.api.reflection.util.PrimaryKeyField;

public class PlayWithMetamodel {

	public static void main(String[] args) {
		
		Metamodel metamodel = Metamodel.of(Person.class);
		
		PrimaryKeyField primaryKeyField = metamodel.getPrimaryKey();
		List<ColumnField> columnFields = metamodel.getColumns();
		
		System.out.println("Primary Key name: " + primaryKeyField.getName() + ", type: " + primaryKeyField.getType().getSimpleName() );

		for (ColumnField columnField : columnFields) {
			System.out.println("Column name: " + columnField.getName() + ", type: " +columnField.getType().getSimpleName());			
		}
		
	}
}
