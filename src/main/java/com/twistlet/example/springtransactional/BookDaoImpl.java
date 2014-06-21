package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class BookDaoImpl implements BookDao {

	private final JdbcOperations jdbcOperations;

	@Autowired
	public BookDaoImpl(final JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public void insertOk(final String title) {
		final String sql = "insert into book (title) values (?)";
		jdbcOperations.update(sql, title);
	}

	@Override
	public void insertBad(final String title) {
		final String sql = "insert into book (xtitle) values (?)";
		jdbcOperations.update(sql, title);
	}

	@Override
	public void clean() {
		final String sql = "delete from book";
		jdbcOperations.update(sql);
	}

	@Override
	public Long count() {
		final String sql = "select count(*) from book";
		return jdbcOperations.queryForObject(sql, Long.class);
	}

}
