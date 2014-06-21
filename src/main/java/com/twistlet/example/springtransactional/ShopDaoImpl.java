package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class ShopDaoImpl implements ShopDao {

	private final JdbcOperations jdbcOperations;

	@Autowired
	public ShopDaoImpl(final JdbcOperations jdbcOperations) {
		super();
		this.jdbcOperations = jdbcOperations;
	}

	@Override
	public void insert(final String name) {
		final String sql = "insert into shop (name) values (?)";
		jdbcOperations.update(sql, name);
	}

	@Override
	public void clean() {
		final String sql = "delete from shop";
		jdbcOperations.update(sql);
	}

	@Override
	public Long count() {
		final String sql = "select count(*) from shop";
		return jdbcOperations.queryForObject(sql, Long.class);
	}

}
