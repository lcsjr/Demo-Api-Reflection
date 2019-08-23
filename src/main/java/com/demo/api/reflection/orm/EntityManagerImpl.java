package com.demo.api.reflection.orm;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.demo.api.reflection.util.ColumnField;
import com.demo.api.reflection.util.Metamodel;

public class EntityManagerImpl<T> implements EntityManager<T> {

	private final AtomicLong idGenerator = new AtomicLong();	
	
	@Override
	public void persist(T t) throws IllegalArgumentException, IllegalAccessException, SQLException {

		Metamodel metamodel = Metamodel.of(t.getClass());
		String sql = metamodel.buildInsertRequest();
		PreparedStatement statement =  prepareStatementWith(sql).andParameters(t);
		statement.executeUpdate();
	}

	private PreparedStatementWrapper prepareStatementWith(String sql) throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:h2:c:\\ecli_develop\\demo-api-reflection\\db-files\\db-pluralsight", "sa","");
		PreparedStatement statement = connection.prepareStatement(sql);
		
		return new PreparedStatementWrapper(statement);
	}

	private class PreparedStatementWrapper {

		private PreparedStatement statement;

		public PreparedStatementWrapper(PreparedStatement statement) {
			this.statement = statement;
		}

		public PreparedStatement andParameters(T t) throws SQLException, IllegalArgumentException, IllegalAccessException {
			Metamodel metamodel = Metamodel.of(t.getClass());
			Class<?> primaryKeyType = metamodel.getPrimaryKey().getType();
			if(primaryKeyType == long.class) {
				long id = idGenerator.incrementAndGet();
				statement.setLong(1, id);
			}
			
			for (int columnIndex = 0; columnIndex < metamodel.getColumns().size() ; columnIndex++ ) {
				  ColumnField columnField = metamodel.getColumns().get(columnIndex);
				  Class<?> fieldType = columnField.getType();
				  Field field = columnField.getField();
				  field.setAccessible(true);

				  Object value = field.get(t);
				  if (fieldType == int.class) {
					  statement.setInt(columnIndex+2, (int) value);
				  }else if (fieldType == String.class) {
					  statement.setString(columnIndex+2, (String) value);
				  }				  
			}
			return statement;
		}
	}

}
