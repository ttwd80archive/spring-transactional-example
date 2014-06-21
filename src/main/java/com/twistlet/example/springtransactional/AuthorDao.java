package com.twistlet.example.springtransactional;

public interface AuthorDao {

	void insert(String name);

	void clean();

	Long count();
}
