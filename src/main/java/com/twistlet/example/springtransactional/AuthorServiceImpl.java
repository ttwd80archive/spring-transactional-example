package com.twistlet.example.springtransactional;

import static org.springframework.transaction.annotation.Propagation.*;

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
		authorDao.insertOk(value);
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
	public void insertRequiredInsideTransaction(final String[] group1,
			final String[] group2, final String[] group3)

	{
		insertMultipleAuthor(group1);
		try {
			bookService.insertRequired(group2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		insertMultipleShop(group3);

	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertRequiresNewInsideTransaction(final String[] group1,
			final String[] group2, final String[] group3) {
		insertMultipleAuthor(group1);
		try {
			bookService.insertRequiresNew(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		insertMultipleShop(group3);

	}

	@Override
	@Transactional(propagation = REQUIRES_NEW)
	public void insertNestedInsideTransaction(final String[] group1,
			final String[] group2, final String[] group3) {
		insertMultipleAuthor(group1);
		try {
			bookService.insertNested(group2);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		insertMultipleShop(group3);
	}

	private void insertMultipleAuthor(final String... items) {
		for (final String item : items) {
			authorDao.insertOk(item);
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
