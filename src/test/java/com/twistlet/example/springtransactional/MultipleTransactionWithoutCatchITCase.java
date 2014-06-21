package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class MultipleTransactionWithoutCatchITCase extends
		AbstractJUnit4SpringContextTests {

	@Resource
	AuthorService authorService;

	@Before
	public void init() {
		authorService.clean();
	}

	@Test
	public void testRequiredInsideTransactionOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiredInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}

	@Test
	public void testRequiredInsideTransactionNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiredInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(1));
		assertThat(list, equalTo(Arrays.asList("1")));
	}

	@Test
	public void testRequiredInsideTransactionNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiredInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(2));
		assertThat(list, equalTo(Arrays.asList("1", "2")));
	}

	@Test
	public void testRequiredInsideTransactionNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiredInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}

	@Test
	public void testRequiresNewInsideTransactionOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiresNewInsideTransactionWithoutCatch(
					group1, group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}

	@Test
	public void testRequiresNewInsideTransactionNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiresNewInsideTransactionWithoutCatch(
					group1, group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(1));
		assertThat(list, equalTo(Arrays.asList("1")));
	}

	@Test
	public void testRequiresNewInsideTransactionNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiresNewInsideTransactionWithoutCatch(
					group1, group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(2));
		assertThat(list, equalTo(Arrays.asList("1", "2")));
	}

	@Test
	public void testRequiresNewInsideTransactionNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertRequiresNewInsideTransactionWithoutCatch(
					group1, group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}

	/* Nested */
	@Test
	public void testNestedInsideTransactionOk() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertNestedInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(2L));
		assertThat(authorService.countBooks(), equalTo(3L));
		assertThat(authorService.countShops(), equalTo(4L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}

	@Test
	public void testNestedInsideTransactionNotOk1() {
		final String[] group1 = { "A1", "TOO_LONG_TO_FIT" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertNestedInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(1));
		assertThat(list, equalTo(Arrays.asList("1")));
	}

	@Test
	public void testNestedInsideTransactionNotOk2() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "TOO_LONG_TO_FIT" };
		final String[] group3 = { "A3", "B3", "C3", "D4" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertNestedInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(2));
		assertThat(list, equalTo(Arrays.asList("1", "2")));
	}

	@Test
	public void testNestedInsideTransactionNotOk3() {
		final String[] group1 = { "A1", "B1" };
		final String[] group2 = { "A2", "B2", "C2" };
		final String[] group3 = { "A3", "B3", "C3", "TOO_LONG_TO_FIT" };
		final List<String> list = new ArrayList<>();
		try {
			authorService.insertNestedInsideTransactionWithoutCatch(group1,
					group2, group3, list);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		assertThat(authorService.count(), equalTo(0L));
		assertThat(authorService.countBooks(), equalTo(0L));
		assertThat(authorService.countShops(), equalTo(0L));
		assertThat(list.size(), equalTo(3));
		assertThat(list, equalTo(Arrays.asList("1", "2", "3")));
	}
}
