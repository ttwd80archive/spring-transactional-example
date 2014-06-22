package com.twistlet.example.springtransactional;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public abstract class AbstractSpringTransactionalITCase extends
		AbstractJUnit4SpringContextTests {
	protected final String[] T1_VALID = { "A1", "B1" };
	protected final String[] T1_INVALID = { "A1", "INVALID" };
	protected final String[] T2_VALID = { "A2", "B2", "C2" };
	protected final String[] T2_INVALID = { "A2", "B2", "INVALID" };
	protected final String[] T3_VALID = { "A3", "B3", "C3", "D3" };
	protected final String[] T3_INVALID = { "A3", "B3", "C3", "INVALID" };

	protected final String[] SINGLE_VALUE_GOOD_DATA = { "A", "B", "C", "D", "E" };
	protected final String[] SINGLE_VALUE_FAIL_ON_LAST = { "A", "B", "C", "D",
			"EGGPLANT" };
	protected final String[] SINGLE_VALUE_FAIL_ON_MIDDLE = { "A", "BANANA",
			"C", "D", "E" };

	protected final String[][] MULTIPLE_ALL_VALID = { T1_VALID, T2_VALID,
			T3_VALID };
	protected final String[][] MULTIPLE_T1_INVALID = { T1_INVALID, T2_VALID,
			T3_VALID };
	protected final String[][] MULTIPLE_T2_INVALID = { T1_VALID, T2_INVALID,
			T3_VALID };
	protected final String[][] MULTIPLE_T3_INVALID = { T1_VALID, T2_VALID,
			T3_INVALID };

	@Autowired
	protected SimpleService simpleService;

	protected List<String> attempt;

	@Before
	public void init() {
		simpleService.clear();
		attempt = new ArrayList<>();

	}

}
