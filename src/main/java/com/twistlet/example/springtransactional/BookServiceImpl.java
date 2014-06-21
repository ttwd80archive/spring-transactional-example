package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

	private final BookDao bookDao;

	@Autowired
	public BookServiceImpl(final BookDao bookDao) {
		super();
		this.bookDao = bookDao;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertRequired(final String[] group1) {
		insertMultiple(group1);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void insertRequiresNew(final String[] group1) {
		insertMultiple(group1);
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void insertNested(final String[] group1) {
		insertMultiple(group1);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public void insertSupports(final String[] group1) {
		insertMultiple(group1);
	}

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void insertNotSupported(final String[] group1) {
		insertMultiple(group1);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public void insertNever(final String[] group1) {
		insertMultiple(group1);
	}

	private void insertMultiple(final String[] group1) {
		for (final String item : group1) {
			bookDao.insertOk(item);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Long countBooks() {
		return bookDao.count();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void clean() {
		bookDao.clean();
	}

}
