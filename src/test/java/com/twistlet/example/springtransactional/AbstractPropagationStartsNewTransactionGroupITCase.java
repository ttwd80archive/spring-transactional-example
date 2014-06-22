package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.springframework.transaction.TransactionDefinition.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Titi Wangsa
 *
 */
public abstract class AbstractPropagationStartsNewTransactionGroupITCase extends
		AbstractSpringTransactionalITCase {

	protected List<String> attempt;

	@Before
	public void initList() {
		attempt = new ArrayList<>();
	}

	@Test
	public void testRequiredAllValid() {
		insert(PROPAGATION_REQUIRED, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	@Test
	public void testRequiresNewAllValid() {
		insert(PROPAGATION_REQUIRES_NEW, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	@Test
	public void testNestedAllValid() {
		insert(PROPAGATION_NESTED, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(4L));
	}

	@Test
	public void testRequiredInvalid1() {
		insert(PROPAGATION_REQUIRED, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testRequiresNewInvalid1() {
		insert(PROPAGATION_REQUIRES_NEW, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testNestedInvalid1() {
		insert(PROPAGATION_NESTED, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));
	}

	public abstract void testRequiredInvalid2();

	public abstract void testRequiresNewInvalid2();

	public abstract void testNestedInvalid2();

	@Test
	public void testRequiredInvalid3() {
		insert(PROPAGATION_REQUIRED, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	/**
	 * 
	 */
	@Test
	public void testRequiresNewInvalid3() {
		insert(PROPAGATION_REQUIRES_NEW, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testNestedInvalid3() {
		insert(PROPAGATION_NESTED, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));
	}

	protected abstract void insert(final int propagationBehavior,
			final String[][] values);

}
