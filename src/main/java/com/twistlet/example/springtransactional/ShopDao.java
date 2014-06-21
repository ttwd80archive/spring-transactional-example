package com.twistlet.example.springtransactional;

public interface ShopDao {

	void insert(String name);

	void clean();

	Long count();
}
