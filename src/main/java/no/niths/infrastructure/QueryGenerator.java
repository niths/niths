package no.niths.infrastructure;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class QueryGenerator<T> {

	private final String SPLITT = "&";
	private final String PRE = "e";
	private final String PREFIX = PRE + ".";
	private final String FROM = "from ";
	private final String WHERE = " where ";

	private Class<T> persistentClass;

	public QueryGenerator(Class<T> persistentClass) {
		setPersistentClass(persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> whereQuery(String criteria, String columnName,
			Session session) {
		String[] conditionBuilder = splittingCriteria(criteria);
		String condition = condition(columnName, conditionBuilder);
		Query query = queryBuilder(session, conditionBuilder, condition);
		return query.list();
	}

	private Query queryBuilder(Session session, String[] conditionBuilder,
			String condition) {
		Query query = null;

		query = session.createQuery(FROM + persistentClass.getSimpleName()
				+ " " + PRE + WHERE + condition);

		for (int i = 0; i < conditionBuilder.length; i++) {
			query.setParameter("a" + i, "%" + conditionBuilder[i] + "%");
		}
		return query;
	}

	private String condition(String columnName, String[] conditionBuilder) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < conditionBuilder.length; i++) {
			if (conditionBuilder[i].length() > 0) {
				if (conditionBuilder.length - 1 == i) {
					buffer.append(PREFIX + columnName + " like :a" + i);
				} else {
					buffer.append(PREFIX + columnName + " like :a" + i
							+ " and ");
				}
			}
		}
		return buffer.toString();
	}

	private String[] splittingCriteria(String criteria) {
		String[] conditionBuilder;

//		if (criteria.length() > 0 && (SPLITT.equals(criteria.charAt(0)))) {
//			conditionBuilder = new String[] { criteria };
//		} else
			
		if (!criteria.contains(SPLITT)) {
			conditionBuilder = new String[] { criteria };
		} else {

			conditionBuilder = criteria.replaceAll(" ", "").split(SPLITT);
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
