package no.niths.infrastructure;

import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DeveloperRepositoryImpl extends AbstractGenericRepositoryImpl<Developer>
		implements DeveloperRepository {

	public DeveloperRepositoryImpl() {
		super(Developer.class, new Developer());
	}
	
	@Override
	public Developer getByDeveloperToken(String token, boolean isEnabled) {
		String sql = "from " + Developer.class.getSimpleName() + " d " +
						"where d.developerToken = :token";
		if (isEnabled){
			sql += " and d.enabled=true";
		}
		return (Developer) getSession().getCurrentSession().createQuery(sql)
										.setString("token", token).uniqueResult();
		
	}
}