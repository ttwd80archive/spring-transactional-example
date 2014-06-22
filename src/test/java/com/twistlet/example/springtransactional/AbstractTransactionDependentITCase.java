package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.springframework.transaction.TransactionDefinition.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author Titi Wangsa
 *
 */
public abstract class AbstractTransactionDependentITCase extends
		AbstractSpringTransactionalITCase {

	@Test
	public void testSupportsAllValid() {
		insert(PROPAGATION_SUPPORTS, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	@Test
	public void testNotSupportedAllValid() {
		insert(PROPAGATION_NOT_SUPPORTED, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	public abstract void testNeverAllValid();

	@Test
	public void testSupportsInvalid1() {
		insert(PROPAGATION_SUPPORTS, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testNotSupportedInvalid1() {
		insert(PROPAGATION_NOT_SUPPORTED, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testNeverInvalid1() {
		insert(PROPAGATION_NEVER, MULTIPLE_T1_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));
	}

	public abstract void testSupportsInvalid2();

	public abstract void testNotSupportedInvalid2();

	public abstract void testNeverInvalid2();

	@Test
	public void testSupportsInvalid3() {
		insert(PROPAGATION_SUPPORTS, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Test
	public void testNotSupportedInvalid3() {
		insert(PROPAGATION_NOT_SUPPORTED, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(3L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	public abstract void testNeverInvalid3();

	protected abstract void insert(final int propagationBehavior,
			final String[][] values);

}
