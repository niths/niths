package no.niths.infrastructure;

import no.niths.domain.developing.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DeveloperRepositoryImpl extends AbstractGenericRepositoryImpl<Developer>
		implements DeveloperRepository {

	public DeveloperRepositoryImpl() {
		super(Developer.class, new Developer());
	}
	
	@Override
	@Deprecated
	public Developer getByDeveloperToken(String token, boolean isEnabled) {
		String sql = "from " + Developer.class.getSimpleName() + " d " +
						"where d.developerToken = :token";
		if (isEnabled){
			sql += " and d.enabled=true";
		}
		return (Developer) getSession().getCurrentSession().createQuery(sql)
										.setString("token", token).uniqueResult();
		
	}
	
	/**
	 * Returns the developer with matching key, 
	 * or null if no developer is found
	 * 
	 * @param key the developer key
	 * @return a developer or null
	 */
	@Override
	public Developer getByDeveloperKey(String key) {
		String sql = "from " + Developer.class.getSimpleName() + " d " +
				"where d.developerKey = :token";
		
		return (Developer) getSession().getCurrentSession().createQuery(sql)
				.setString("token", key).uniqueResult();
		
	}
}