package com.twistlet.example.springtransactional;

import static org.springframework.transaction.TransactionDefinition.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
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
			@Qualifier("table2Dao") final TableDao table2Dao,
			@Qualifier("table3Dao") final TableDao table3Dao,
			final PlatformTransactionManager transactionManager) {
		this.table1Dao = table1Dao;
		this.table2Dao = table2Dao;
		this.table3Dao = table3Dao;
		this.transactionManager = transactionManager;
	}

	@Override
	public void insertMultipleOneLayer(final int propagationBehavior,
			final String[] values) {
		final TransactionTemplate transactionTemplate = create(propagationBehavior);
		final TransactionCallbackInsert callback = new TransactionCallbackInsert(
				table1Dao, values);
		transactionTemplate.execute(callback);
	}

	@Override
	public void insertMultipleTwoLayerWithoutCatch(
			final int propagationBehavior, final String[] values1,
			final String[] values2, final String[] values3,
			final List<String> attempt) {
		final TransactionCallbackLayerWithoutCatch callback = new TransactionCallbackLayerWithoutCatch(
				propagationBehavior, values1, values2, values3, attempt);
		insertMultipleTwoLayer(propagationBehavior, values1, values2, values3,
				attempt, callback);
	}

	@Override
	public void insertMultipleTwoLayerWithCatch(final int propagationBehavior,
			final String[] values1, final String[] values2,
			final String[] values3, final List<String> attempt) {
		final TransactionCallbackLayerWithCatch callback = new TransactionCallbackLayerWithCatch(
				propagationBehavior, values1, values2, values3, attempt);
		insertMultipleTwoLayer(propagationBehavior, values1, values2, values3,
				attempt, callback);

	}

	private void insertMultipleTwoLayer(final int propagationBehavior,
			final String[] values1, final String[] values2,
			final String[] values3, final List<String> attempt,
			final TransactionCallback<Object> callback) {
		final TransactionTemplate transactionTemplate = create(propagationBehavior);
		transactionTemplate.setPropagationBehavior(PROPAGATION_REQUIRES_NEW);
		transactionTemplate.execute(callback);
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
		final TransactionTemplate transactionTemplate = create(PROPAGATION_REQUIRES_NEW);
		return transactionTemplate.execute(new TransactionCallbackCount(
				tableDao));
	}

	private void clear(final TableDao tableDao) {
		final TransactionTemplate transactionTemplate = create(PROPAGATION_REQUIRES_NEW);
		transactionTemplate.execute(new TransactionCallbackClear(tableDao));
	}

	private class TransactionCallbackCount implements TransactionCallback<Long> {
		private final TableDao tableDao;

		public TransactionCallbackCount(final TableDao tableDao) {
			this.tableDao = tableDao;
		}

		@Override
		public Long doInTransaction(final TransactionStatus status) {
			return tableDao.count();
		}
	}

	private class TransactionCallbackClear implements
			TransactionCallback<Object> {
		private final TableDao tableDao;

		public TransactionCallbackClear(final TableDao tableDao) {
			super();
			this.tableDao = tableDao;
		}

		@Override
		public Object doInTransaction(final TransactionStatus status) {
			tableDao.clear();
			return null;
		}
	}

	private class TransactionCallbackLayerWithoutCatch implements
			TransactionCallback<Object> {

		protected final int propagationBehavior;
		protected final String[] values1;
		protected final String[] values2;
		protected final String[] values3;
		protected final List<String> attempts;

		public TransactionCallbackLayerWithoutCatch(
				final int propagationBehavior, final String[] values1,
				final String[] values2, final String[] values3,
				final List<String> attempts) {
			this.propagationBehavior = propagationBehavior;
			this.values1 = values1;
			this.values2 = values2;
			this.values3 = values3;
			this.attempts = attempts;
		}

		public void insertMultiple(final TableDao tableDao,
				final String[] values) {
			for (final String value : values) {
				tableDao.insert(value);
			}
		}

		@Override
		public Object doInTransaction(final TransactionStatus status) {
			attempts.clear();
			attempts.add("1");
			insertMultiple(table1Dao, values1);
			final TransactionCallbackInsert callback = new TransactionCallbackInsert(
					table2Dao, values2);
			final TransactionTemplate transactionTemplate = create(propagationBehavior);
			attempts.add("2");
			transactionTemplate.execute(callback);
			attempts.add("3");
			insertMultiple(table3Dao, values3);
			return null;
		}
	}

	private class TransactionCallbackLayerWithCatch extends
			TransactionCallbackLayerWithoutCatch {

		public TransactionCallbackLayerWithCatch(final int propagationBehavior,
				final String[] values1, final String[] values2,
				final String[] values3, final List<String> attempts) {
			super(propagationBehavior, values1, values2, values3, attempts);
		}

		@Override
		public Object doInTransaction(final TransactionStatus status) {
			attempts.clear();
			attempts.add("1");
			insertMultiple(table1Dao, values1);
			final TransactionCallbackInsert callback = new TransactionCallbackInsert(
					table2Dao, values2);
			final TransactionTemplate transactionTemplate = create(propagationBehavior);
			attempts.add("2");
			try {
				transactionTemplate.execute(callback);
			} catch (final Exception e) {
				e.printStackTrace();
			}
			attempts.add("3");
			insertMultiple(table3Dao, values3);
			return null;
		}
	}

	private class TransactionCallbackInsert implements
			TransactionCallback<Object> {

		private final TableDao tableDao;
		private final String[] values;

		public TransactionCallbackInsert(final TableDao tableDao,
				final String[] values) {
			this.tableDao = tableDao;
			this.values = values;
		}

		@Override
		public Object doInTransaction(final TransactionStatus status) {
			for (final String value : values) {
				tableDao.insert(value);
			}
			return null;
		}
	}

	private class TransactionCallbackInsertWithCatch implements
			TransactionCallback<Object> {

		private final TableDao tableDao;
		private final String[] values;

		public TransactionCallbackInsertWithCatch(final TableDao tableDao,
				final String[] values) {
			this.tableDao = tableDao;
			this.values = values;
		}

		@Override
		public Object doInTransaction(final TransactionStatus status) {
			for (final String value : values) {
				try {
					tableDao.insert(value);
				} catch (final Exception e) {
					e.toString();
				}
			}
			return null;
		}
	}

	private TransactionTemplate create(final int propagationBehavior) {
		final TransactionTemplate transactionTemplate = new TransactionTemplate();
		transactionTemplate.setTransactionManager(transactionManager);
		transactionTemplate.setPropagationBehavior(propagationBehavior);
		return transactionTemplate;
	}

	@Override
	public void clear() {
		clear(table1Dao);
		clear(table2Dao);
		clear(table3Dao);
	}

	@Override
	public void insertMultipleWithCatchOneLayer(final int propagationBehavior,
			final String[] values) {
		final TransactionTemplate transactionTemplate = create(propagationBehavior);
		final TransactionCallbackInsertWithCatch callback = new TransactionCallbackInsertWithCatch(
				table1Dao, values);
		transactionTemplate.execute(callback);
	}

}
