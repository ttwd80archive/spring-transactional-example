package com.twistlet.example.springtransactional;

public interface SimpleService {

	void insertMultipleOneLayer(int propagationBehavior, String[] values);

	Long table1Count();

	Long table2Count();

	Long table3Count();

	void clear();
}
