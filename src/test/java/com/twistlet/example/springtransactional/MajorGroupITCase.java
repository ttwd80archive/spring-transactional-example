package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.springframework.transaction.TransactionDefinition.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class MajorGroupITCase extends AbstractJUnit4SpringContextTests {

	@Autowired
	SimpleService simpleService;

	private final String[] SINGLE_VALUE_GOOD_DATA = { "A", "B", "C", "D", "E" };
	private final String[] SINGLE_VALUE_FAIL_ON_LAST = { "A", "B", "C", "D",
			"EGGPLANT" };

	@Before
	public void init() {
		simpleService.clear();
	}

	// TransactionDefinition
	// PROPAGATION_REQUIRED
	// PROPAGATION_SUPPORTS
	// PROPAGATION_MANDATORY
	// PROPAGATION_REQUIRES_NEW
	// PROPAGATION_NOT_SUPPORTED
	// PROPAGATION_NEVER
	// PROPAGATION_NESTED
	@Test
	public void testRequiredGoodData() {
		insertMultiple(PROPAGATION_REQUIRED, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testRequiredFailOnLast() {
		insertMultiple(PROPAGATION_REQUIRED, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(0L));
	}

	@Test
	public void testSupportsGoodData() {
		insertMultiple(PROPAGATION_SUPPORTS, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testSupportsFailOnLast() {
		insertMultiple(PROPAGATION_SUPPORTS, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(4L));
	}

	@Test
	public void testMandatoryGoodData() {
		insertMultiple(PROPAGATION_MANDATORY, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(0L));
	}

	@Test
	public void testMandatoryFailOnLast() {
		insertMultiple(PROPAGATION_MANDATORY, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(0L));
	}

	@Test
	public void testRequiresNewGoodData() {
		insertMultiple(PROPAGATION_REQUIRES_NEW, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testRequiresNewFailOnLast() {
		insertMultiple(PROPAGATION_REQUIRES_NEW, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(0L));
	}

	@Test
	public void testNotSupportedGoodData() {
		insertMultiple(PROPAGATION_NOT_SUPPORTED, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testNotSupportedFailOnLast() {
		insertMultiple(PROPAGATION_NOT_SUPPORTED, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(4L));
	}

	@Test
	public void testNeverGoodData() {
		insertMultiple(PROPAGATION_NEVER, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testNeverFailOnLast() {
		insertMultiple(PROPAGATION_NEVER, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(4L));
	}

	@Test
	public void testNestedGoodData() {
		insertMultiple(PROPAGATION_NESTED, SINGLE_VALUE_GOOD_DATA);
		assertThat(simpleService.table1Count(), equalTo(5L));
	}

	@Test
	public void testNestedFailOnLast() {
		insertMultiple(PROPAGATION_NESTED, SINGLE_VALUE_FAIL_ON_LAST);
		assertThat(simpleService.table1Count(), equalTo(0L));
	}

	private void insertMultiple(final int propagation, final String[] values) {
		try {
			simpleService.insertMultipleOneLayer(propagation, values);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
