package no.niths.infrastructure;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * 
 * This is a dynamic query generator that creates where 
 * queries based on provided column name and given criteria
 * 
 *
 * @param <T>
 */
public class QueryGenerator<T> {

	private final String SPLITT = "&";

	private Class<T> persistentClass;

	public QueryGenerator(Class<T> persistentClass) {
		setPersistentClass(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> whereQuery(String criteria, String columnName,
			Session session) {
		return criteriaBuilder(session, splittingCriteria(criteria), columnName).list();
	}

	/**
	 * Builds the criteria
	 * @param session
	 * @param conditionBuilder
	 * @param columnName
	 * @return
	 */
	private Criteria criteriaBuilder(Session session, String[] conditionBuilder,
			String columnName) {
		
		Criteria crit = session.createCriteria(persistentClass);
		
		for (int i = 0; i < conditionBuilder.length; i++) {
			crit.add(Restrictions.like(columnName, conditionBuilder[i], MatchMode.ANYWHERE));
		}
		
		return crit;
	}

	/**
	 * Splits the given criteria into a String list
	 * @param criteria
	 * @return
	 */
	private String[] splittingCriteria(String criteria) {
		String[] conditionBuilder;

		if (!criteria.contains(SPLITT)) {
			conditionBuilder = new String[] { criteria };
		} else {
			conditionBuilder = criteria.split(SPLITT);
		}
		return conditionBuilder;
	}

	/**
	 * Gets the provided persistence class
	 * @return
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}
	
	/**
	 * Sets the provided persistence class
	 * @param persistentClass
	 */
	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
}
