package com.twistlet.example.springtransactional;

public interface AuthorService {

	void clean();

	Long count();

	Long countBooks();

	void insert(String value);

	void insertMultipleRequired(String... items);

	void insertMultipleMandatory(String... items);

	void insertMultipleNested(String... items);

	void insertMultipleSupports(String... items);

	void insertMultipleRequiresNew(String... items);

	void insertMultipleNotSupported(String... items);

	void insertMultipleNever(String... items);

	void insertRequiredInsideTransaction(String[] group1, String[] group2,
			String[] group3);

	void insertRequiresNewInsideTransaction(String[] group1, String[] group2,
			String[] group3);

	void insertNestedInsideTransaction(String[] group1, String[] group2,
			String[] group3);

	Long countShops();

}
