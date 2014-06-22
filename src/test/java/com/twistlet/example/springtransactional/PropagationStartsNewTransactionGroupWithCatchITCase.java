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
public class PropagationStartsNewTransactionGroupWithCatchITCase extends
		AbstractPropagationStartsNewTransactionGroupITCase {

	@Override
	@Test
	public void testRequiredInvalid2() {
		insert(PROPAGATION_REQUIRED, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(0L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(0L));

	}

	@Override
	@Test
	public void testRequiresNewInvalid2() {
		insert(PROPAGATION_REQUIRES_NEW, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(4L));

	}

	@Override
	@Test
	public void testNestedInvalid2() {
		insert(PROPAGATION_NESTED, MULTIPLE_T2_INVALID);
		assertThat(attempt, equalTo(Arrays.asList("1", "2", "3")));
		assertThat(simpleService.table1Count(), equalTo(2L));
		assertThat(simpleService.table2Count(), equalTo(0L));
		assertThat(simpleService.table3Count(), equalTo(4L));
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
