package no.niths.infrastructure;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class QueryGenerator<T> {

	private final String SPLITT = "&";

	private Class<T> persistentClass;

	public QueryGenerator(Class<T> persistentClass) {
		setPersistentClass(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> whereQuery(String criteria, String columnName,
			Session session) {
		return queryBuilder(session, splittingCriteria(criteria), columnName).list();
	}

	private Criteria queryBuilder(Session session, String[] conditionBuilder,
			String columnName) {
		
		Criteria crit = session.createCriteria(persistentClass);
		
		for (int i = 0; i < conditionBuilder.length; i++) {
			crit.add(Restrictions.like(columnName, conditionBuilder[i], MatchMode.ANYWHERE));
		}
		
		return crit;
	}

	private String[] splittingCriteria(String criteria) {
		String[] conditionBuilder;

		if (criteria.length() > 0 && (SPLITT.charAt(0) == (criteria.charAt(0)))) {
			conditionBuilder = new String[] { criteria };
		} else if (!criteria.contains(SPLITT)) {
			conditionBuilder = new String[] { criteria };
		} else {
			conditionBuilder = criteria.split(SPLITT);
		}
		return conditionBuilder;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
}
