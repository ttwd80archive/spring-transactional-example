package com.twistlet.example.springtransactional;

import static org.springframework.transaction.annotation.Propagation.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorDao authorDao;
	private final BookService bookService;
	private final ShopDao shopDao;

	@Autowired
	public AuthorServiceImpl(final AuthorDao authorDao,
			final BookService bookService, final ShopDao shopDao) {
		super();
		this.authorDao = authorDao;
		this.bookService = bookService;
		this.shopDao = shopDao;
	}

	@Override
	@Transactional
	public void clean() {
		authorDao.clean();
		bookService.clean();
		shopDao.clean();
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return authorDao.count();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insert(final String value) {
		authorDao.insert(value);
	}

	@Override
	@Transactional(propagation = REQUIRED)
	public void insertMultipleRequired(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = MANDATORY)
	public void insertMultipleMandatory(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = NESTED)
	public void insertMultipleNested(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = SUPPORTS)
	public void insertMultipleSupports(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertMultipleRequiresNew(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = NOT_SUPPORTED)
	public void insertMultipleNotSupported(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = NEVER)
	public void insertMultipleNever(final String... items) {
		insertMultipleAuthor(items);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertRequiredInsideTransactionWithCatch(final String[] group1,
			final String[] group2, final String[] group3,
			final List<String> list)

	{
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertRequired(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);

	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertRequiresNewInsideTransactionWithCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertRequiresNew(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNestedInsideTransactionWithCatch(final String[] group1,
			final String[] group2, final String[] group3,
			final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertNested(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertRequiredInsideTransactionWithoutCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertRequired(group2);
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertRequiresNewInsideTransactionWithoutCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertRequiresNew(group2);
		list.add("3");
		insertMultipleShop(group3);

	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNestedInsideTransactionWithoutCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertNested(group2);
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertSupportsInsideTransactionWithCatch(final String[] group1,
			final String[] group2, final String[] group3,
			final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertSupports(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertSupportsInsideTransactionWithoutCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertSupports(group2);
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNotSupportedInsideTransactionWithCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertNotSupported(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);

	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNotSupportedInsideTransactionWithoutCatch(
			final String[] group1, final String[] group2,
			final String[] group3, final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertNotSupported(group2);
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNeverInsideTransactionWithCatch(final String[] group1,
			final String[] group2, final String[] group3,
			final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		try {
			list.add("2");
			bookService.insertNever(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		list.add("3");
		insertMultipleShop(group3);
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNeverInsideTransactionWithoutCatch(final String[] group1,
			final String[] group2, final String[] group3,
			final List<String> list) {
		list.add("1");
		insertMultipleAuthor(group1);
		list.add("2");
		bookService.insertNever(group2);
		list.add("3");
		insertMultipleShop(group3);

	}

	private void insertMultipleAuthor(final String... items) {
		for (final String item : items) {
			authorDao.insert(item);
		}
	}

	private void insertMultipleShop(final String... items) {
		for (final String item : items) {
			shopDao.insert(item);
		}
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public Long countBooks() {
		return bookService.countBooks();
	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public Long countShops() {
		return shopDao.count();
	}

}
