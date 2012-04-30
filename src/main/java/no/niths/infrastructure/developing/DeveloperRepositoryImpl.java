package no.niths.infrastructure.developing;

import no.niths.domain.developing.Developer;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.developing.interfaces.DeveloperRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Developer
 *
 * <p>
 * Inherits the basic CRUD actions and has method
 * for getByDeveloperKey
 * </p>
 */
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
     * {@inheritDoc}
     */
	@Override
	public Developer getByDeveloperKey(String key) {
		String sql = "from " + Developer.class.getSimpleName() + " d " +
				"where d.developerKey = :token";
		
		return (Developer) getSession().getCurrentSession().createQuery(sql)
				.setString("token", key).uniqueResult();
		
	}
}