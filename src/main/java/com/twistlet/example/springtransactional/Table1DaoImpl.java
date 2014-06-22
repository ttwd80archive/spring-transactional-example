package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("table1Dao")
public class Table1DaoImpl implements TableDao {

	private final JdbcOperations jdbcOperations;

	@Autowired
	public Table1DaoImpl(final JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public void insert(final String value) {
		final String sql = "insert into table1 (value) values (?)";
		jdbcOperations.update(sql, value);
	}

	@Override
	public Long count() {
		final String sql = "select count(*) from table1";
		return jdbcOperations.queryForObject(sql, Long.class);
	}

	@Override
	public void clear() {
		final String sql = "delete from table1";
		jdbcOperations.update(sql);
	}

}
