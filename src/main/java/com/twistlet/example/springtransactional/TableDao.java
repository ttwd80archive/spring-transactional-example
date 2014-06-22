package com.twistlet.example.springtransactional;

public interface TableDao {

	void insert(String value);

	Long count();

	void clear();
}
