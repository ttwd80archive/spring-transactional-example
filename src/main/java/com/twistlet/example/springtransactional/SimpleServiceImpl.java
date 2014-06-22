package com.twistlet.example.springtransactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class SimpleServiceImpl implements SimpleService {

	private final TableDao table1Dao;
	private final TableDao table2Dao;
	private final TableDao table3Dao;
	private final PlatformTransactionManager transactionManager;

	@Autowired
	public SimpleServiceImpl(@Qualifier("table1Dao") final TableDao table1Dao,
			@Qualifier("table1Dao") final TableDao table2Dao,
			@Qualifier("table1Dao") final TableDao table3Dao,
			final PlatformTransactionManager transactionManager) {
		this.table1Dao = table1Dao;
		this.table2Dao = table2Dao;
		this.table3Dao = table3Dao;
		this.transactionManager = transactionManager;
	}

	@Override
	public void insertMultipleOneLayer(final int propagationBehavior,
			final String[] values) {
		final TransactionTemplate transactionTemplate = new TransactionTemplate(
				transactionManager);
		transactionTemplate.setPropagationBehavior(propagationBehavior);
		transactionTemplate.execute(new TransactionCallback<Object>() {

			@Override
			public Long doInTransaction(final TransactionStatus status) {
				for (final String value : values) {
					table1Dao.insert(value);
				}
				return null;
			}
		});
	}

	@Override
	public Long table1Count() {
		return count(table1Dao);
	}

	@Override
	public Long table2Count() {
		return count(table2Dao);
	}

	@Override
	public Long table3Count() {
		return count(table3Dao);
	}

	private Long count(final TableDao tableDao) {
		final TransactionTemplate transactionTemplate = new TransactionTemplate(
				transactionManager);
		transactionTemplate
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		return transactionTemplate.execute(new TransactionCallback<Long>() {

			@Override
			public Long doInTransaction(final TransactionStatus status) {
				return tableDao.count();
			}
		});
	}

	private void clear(final TableDao tableDao) {
		final TransactionTemplate transactionTemplate = new TransactionTemplate(
				transactionManager);
		transactionTemplate
				.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
		transactionTemplate.execute(new TransactionCallback<Long>() {

			@Override
			public Long doInTransaction(final TransactionStatus status) {
				tableDao.clear();
				return null;
			}
		});
	}

	@Override
	public void clear() {
		clear(table1Dao);
		clear(table2Dao);
		clear(table3Dao);
	}

}
