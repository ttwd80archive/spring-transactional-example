package com.twistlet.example.springtransactional;

import static org.hamcrest.core.IsEqual.*;
import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class TransactionITCase extends AbstractJUnit4SpringContextTests {

	@Resource
	AuthorService authorService;

	@Before
	public void init() {
		authorService.clean();
	}

	@Test
	public void testCountClean() {
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	@Test
	public void testInsertA() {
		authorService.insert("ant");
		final Long count = authorService.count();
		assertThat(count, equalTo(1L));
	}

	@Test
	public void testInsertB() {
		authorService.insert("ball");
		final Long count = authorService.count();
		assertThat(count, equalTo(1L));
	}

	@Test
	public void testInsertRequiredAll() {
		try {
			authorService.insertMultipleRequired("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertRequiredNothing() {
		try {
			authorService.insertMultipleRequired("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	@Test
	public void testInsertMandatoryFailSimple() {
		try {
			authorService.insertMultipleMandatory("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	@Test
	public void testInsertMandatoryFailSimpleAndBad() {
		try {
			authorService.insertMultipleMandatory("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	@Test
	public void testInsertNestedAll() {
		try {
			authorService.insertMultipleNested("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertNestedNothing() {
		try {
			authorService.insertMultipleNested("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	@Test
	public void testInsertSupportsAll() {
		try {
			authorService.insertMultipleSupports("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertSupportsGoodAndBad() {
		try {
			authorService.insertMultipleSupports("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(4L));
	}

	@Test
	public void testInsertRequiresNewGoodOnly() {
		try {
			authorService.insertMultipleRequiresNew("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertRequiresNewGoodAndBad() {
		try {
			authorService.insertMultipleRequiresNew("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(0L));
	}

	public void testInsertNotSupportedGoodOnly() {
		try {
			authorService.insertMultipleNotSupported("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertNotSupportedGoodAndBad() {
		try {
			authorService.insertMultipleNotSupported("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(4L));
	}

	public void testInsertNeverGoodOnly() {
		try {
			authorService.insertMultipleNever("A", "B", "C", "D", "E");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(5L));
	}

	@Test
	public void testInsertNeverGoodAndBad() {
		try {
			authorService.insertMultipleNever("A", "B", "C", "D",
					"Data Too Big For Column");
		} catch (final Exception e) {
			e.printStackTrace();
		}
		final Long count = authorService.count();
		assertThat(count, equalTo(4L));
	}
}
