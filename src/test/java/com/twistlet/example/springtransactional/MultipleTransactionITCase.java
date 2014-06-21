package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class MultipleTransactionITCase extends AbstractJUnit4SpringContextTests {

	@Resource
	AuthorService authorService;

	@Before
	public void init() {
		authorService.clean();
	}

	@Test
	public void testRequiredNestedOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiredInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
	}

	@Test
	public void testRequiredNestedNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiredInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testRequiredNestedNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiredInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testRequiredNestedNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		try {
			authorService.insertRequiredInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testRequiresNewNestedOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiresNewInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
	}

	@Test
	public void testRequiresNewNestedNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiresNewInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testRequiresNewNestedNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertRequiresNewInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testRequiresNewNestedNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		try {
			authorService.insertRequiresNewInsideTransaction(group1, group2,
					group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	/* Nested */
	@Test
	public void testNestedOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertNestedInsideTransaction(group1, group2, group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
	}

	@Test
	public void testNestedNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertNestedInsideTransaction(group1, group2, group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testNestedNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		try {
			authorService.insertNestedInsideTransaction(group1, group2, group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}

	@Test
	public void testNestedNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		try {
			authorService.insertNestedInsideTransaction(group1, group2, group3);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
	}
}
