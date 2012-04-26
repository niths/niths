package no.niths.infrastructure.developing;

import java.util.List;

import no.niths.domain.developing.Application;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.developing.interfaces.ApplicationRepository;

import org.springframework.stereotype.Repository;

@Repository
public class ApplicationRepositoryImpl extends
		AbstractGenericRepositoryImpl<Application> implements
		ApplicationRepository {

	public ApplicationRepositoryImpl() {
		super(Application.class, new Application());
	}

	@Override
	@Deprecated
	public Application getByApplicationToken(String token) {
		String sql = "from " + Application.class.getSimpleName() + " a "
				+ "where a.applicationToken = :token and a.enabled=true";
		return (Application) getSession().getCurrentSession().createQuery(sql)
				.setString("token", token).uniqueResult();

	}
	
	/**
	 * Returns the application matching the key
	 * The application must be enabled to be returned
	 * 
	 * @param key the application key as a string
	 * @return the application or null if no matching key or app is not enabled
	 */
	@Override
	public Application getByApplicationKey(String key, boolean enabled) {
		String sql = "from " + Application.class.getSimpleName() + " a "
				+ "where a.applicationKey = :key";
		if(enabled){
			sql += " and a.enabled=true";
		}
		return (Application) getSession().getCurrentSession().createQuery(sql)
				.setString("key", key).uniqueResult();
		
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Application> getTopApps(int maxResults){
		String sql = "from " + Application.class.getSimpleName() + " a " +
						" order by a.requests desc";
		return getSession().getCurrentSession().createQuery(sql)
						.setMaxResults(maxResults).list();
	}
}