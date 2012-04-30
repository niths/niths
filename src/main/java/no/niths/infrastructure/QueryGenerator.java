package no.niths.infrastructure;

import java.util.GregorianCalendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * This is a dynamic query generator that creates where queries based on
 * provided column name and given criteria
 * 
 * 
 * @param <T>
 */
public class QueryGenerator<T> {

	private static final String START_TIME = "startTime";

	private final String SPLITT = ",";

	private Class<T> persistentClass;

	public QueryGenerator(Class<T> persistentClass) {
		setPersistentClass(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> whereQuery(String criteria, String columnName,
			Session session) {

		Criteria crit = session.createCriteria(persistentClass);

		crit.add(criterionBuilder(splittingCriteria(criteria), columnName));
		
		return crit.list();
	}

	/**
	 * Returns a list on when and where on tags and location between
	 * @param criteria
	 * @param columnName
	 * @param session
	 * @param lo
	 * @param hi
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> whereAndBetween(String criteria, String columnName,String secondColumnName,
			Session session, GregorianCalendar lo, GregorianCalendar hi) {

		Criteria crit = session.createCriteria(persistentClass);

	
		if (hi == null) {
			crit.add(Restrictions.and(criterionBuilder(splittingCriteria(criteria), columnName), Restrictions.ge(secondColumnName, lo)));
		} else if (lo != null) {
			crit.add(Restrictions.and(criterionBuilder(splittingCriteria(criteria), columnName), Restrictions.between(secondColumnName, lo,hi)));
		}

		crit.addOrder(Order.asc(START_TIME));
		System.err.println(crit);
		return crit.list();
	}

	/**
	 * wrapper class for the criterion
	 * 
	 * @param conditionBuilder
	 * @param columnName
	 * @return
	 */
	private Criterion criterionBuilder(String[] conditionBuilder,
			String columnName) {

		int size = conditionBuilder.length;
		Criterion[] criterion = new Criterion[conditionBuilder.length];

		// builds criterion
		cirterionBuilder(conditionBuilder, columnName, size, criterion);

		return logicalExpressionBuilder(size, criterion);

	}

	/**
	 * Creates and Criterion up to 4 Criterion
	 * @param size
	 * @param criterion
	 * @return
	 */
	private Criterion logicalExpressionBuilder(int size, Criterion[] criterion) {
		LogicalExpression andExp = null;
		switch (size) {
		case 1:
			return criterion[0];
		case 2:
			andExp = Restrictions.and(criterion[0], criterion[1]);
			break;
		case 3:
			andExp = Restrictions.and(criterion[0],
					Restrictions.and(criterion[1], criterion[2]));
			break;
		case 4:
			andExp = Restrictions.and(
					Restrictions.and(criterion[0], criterion[1]),
					Restrictions.and(criterion[2], criterion[3]));
			break;
		default:
			throw new IllegalArgumentException("You can only split on max 4 words");

		}
		return andExp;
	}

	/**
	 * Builds like criterion arguments with match mode anywhere
	 * @param conditionBuilder
	 * @param columnName
	 * @param size
	 * @param criterion
	 */
	private void cirterionBuilder(String[] conditionBuilder, String columnName,
			int size, Criterion[] criterion) {
		for (int i = 0; i < size; i++) {
			criterion[i] = Restrictions.like(columnName, conditionBuilder[i],
					MatchMode.ANYWHERE);
		}
	}

	/**
	 * Splits the given criteria into a String list
	 * 
	 * @param criteria
	 * @return
	 */
	private String[] splittingCriteria(String criteria) {
		String[] conditionBuilder;

		if (!criteria.contains(SPLITT)) {
			conditionBuilder = new String[] { criteria.trim() };
		} else {
			conditionBuilder = criteria.trim().split(SPLITT);
		}
		return conditionBuilder;
	}

	/**
	 * Gets the provided persistence class
	 * 
	 * @return
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/**
	 * Sets the provided persistence class
	 * 
	 * @param persistentClass
	 */
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
}
