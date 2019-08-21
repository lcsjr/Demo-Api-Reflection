package com.demo.api.reflection.orm;

import com.demo.api.reflection.util.Metamodel;

public class EntityManagerImpl<T> implements EntityManager<T> {

	@Override
	public void persist(T t) {

		Metamodel metamodel = Metamodel.of(t.getClass());
		String sql = metamodel.buildInsertRequest();
//		PreparedStatement statement =  prepareStatementWith(sql).andParameters(t);
//		statement.executeUpdate();
	}



}
