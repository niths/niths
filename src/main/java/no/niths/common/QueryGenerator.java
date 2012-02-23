package no.niths.common;

import java.util.List;

import no.niths.domain.Event;

import org.hibernate.Query;
import org.hibernate.Session;

public class QueryGenerator {

	private final String SPLITT = "&";
	private final String PRE = "e";
	private final String PREFIX = PRE + ".";
	private final String FROM = "from ";
	private final String WHERE = " where ";

	@SuppressWarnings("unchecked")
	public List<Event> whereQuery(String criteria, String columnName,
			Session session) {
		String[] conditionBuilder = splittingCriteria(criteria);
		String condition = condition(columnName, conditionBuilder);
		Query query = queryBuilder(session, conditionBuilder, condition);
		return query.list();
	}

	private Query queryBuilder(Session session, String[] conditionBuilder,
			String condition) {
		Query query = null;

		query = session.createQuery(FROM + Event.class.getSimpleName() + " "
				+ PRE + WHERE + condition);

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

		if (!criteria.contains(SPLITT)) {
			conditionBuilder = new String[] { criteria };
		} else {
			conditionBuilder = criteria.replaceAll(" ", "").split(SPLITT);
		}
		return conditionBuilder;
	}
}
