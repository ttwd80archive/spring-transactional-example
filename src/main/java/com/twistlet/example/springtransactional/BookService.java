package com.twistlet.example.springtransactional;

public interface BookService {

	Long countBooks();

	void clean();

	void insertRequired(String[] group1);

	void insertRequiresNew(String[] group1);

	void insertNested(String[] group1);

	void insertSupports(String[] group1);

	void insertNotSupported(String[] group1);

	void insertNever(String[] group1);
}
