package com.twistlet.example.springtransactional;

import java.util.List;

public interface SimpleService {

	void insertMultipleOneLayer(int propagationBehavior, String[] values);

	Long table1Count();

	Long table2Count();

	Long table3Count();

	void clear();

	void insertMultipleTwoLayerWithoutCatch(int propagationBehavior,
			String[] values1, String[] values2, String[] values3,
			List<String> attempt);

	void insertMultipleTwoLayerWithCatch(int propagationBehavior,
			String[] strings, String[] strings2, String[] strings3,
			List<String> attempt);
}
