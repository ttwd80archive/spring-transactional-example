package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorDaoImpl implements AuthorDao {

	private final JdbcOperations jdbcOperations;

	@Autowired
	public AuthorDaoImpl(final JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public void insert(final String title) {
		final String sql = "insert into author (name) values (?)";
		jdbcOperations.update(sql, title);
	}

	@Override
	public void clean() {
		final String sql = "delete from author";
		jdbcOperations.update(sql);
	}

	@Override
	public Long count() {
		final String sql = "select count(*) from author";
		return jdbcOperations.queryForObject(sql, Long.class);
	}

}
