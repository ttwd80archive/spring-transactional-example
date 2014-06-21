package com.twistlet.example.springtransactional;

import java.util.List;

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

	void insertRequiredInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertRequiresNewInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNestedInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertRequiredInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertRequiresNewInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNestedInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	// SUPPORTS, NOT_SUPPORTED, NEVER
	void insertSupportsInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertSupportsInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNotSupportedInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNotSupportedInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNeverInsideTransactionWithCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	void insertNeverInsideTransactionWithoutCatch(String[] group1,
			String[] group2, String[] group3, List<String> list);

	Long countShops();

}
