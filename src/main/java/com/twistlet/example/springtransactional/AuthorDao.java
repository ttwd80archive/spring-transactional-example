package com.twistlet.example.springtransactional;

public interface AuthorDao {

	void insertOk(String name);

	void insertBad(String name);
	
	void clean();

	Long count();
}
