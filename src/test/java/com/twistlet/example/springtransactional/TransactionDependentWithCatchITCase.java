package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;
import static org.springframework.transaction.TransactionDefinition.*;

import java.util.Arrays;

import org.junit.Test;

public class TransactionDependentWithCatchITCase extends
		AbstractTransactionDependentITCase {
	@Override
	@Test
	public void testNeverAllValid() {
		insert(PROPAGATION_NEVER, MULTIPLE_ALL_VALID);
		assertThat(attempt.size(), equalTo(3));
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(4L));
	}

	@Override
	@Test
	public void testSupportsInvalid2() {
		insert(PROPAGATION_SUPPORTS, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Override
	@Test
	public void testNotSupportedInvalid2() {
		insert(PROPAGATION_NOT_SUPPORTED, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(2L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	@Override
	@Test
	public void testNeverInvalid2() {
		insert(PROPAGATION_NEVER, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(4L));
	}

	@Override
	@Test
	public void testNeverInvalid3() {
		insert(PROPAGATION_NEVER, MULTIPLE_T3_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));
	}

	@Override
	protected void insert(final int propagationBehavior, final String[][] values) {
		try {
			simpleService.insertMultipleTwoLayerWithCatch(propagationBehavior,
					values[0], values[1], values[2], attempt);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
