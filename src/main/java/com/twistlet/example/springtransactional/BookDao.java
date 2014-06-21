package com.twistlet.example.springtransactional;

public interface BookDao {

	void insertOk(String title);

	void insertBad(String title);

	void clean();

	Long count();
}
