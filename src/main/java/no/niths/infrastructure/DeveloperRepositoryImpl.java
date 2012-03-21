package no.niths.infrastructure;

import no.niths.domain.Developer;
import no.niths.infrastructure.interfaces.DeveloperRepository;

import org.springframework.stereotype.Repository;

@Repository
public class DeveloperRepositoryImpl extends AbstractGenericRepositoryImpl<Developer>
		implements DeveloperRepository {

	public DeveloperRepositoryImpl() {
		super(Developer.class);
	}
	
	@Override
	public Developer getByDeveloperToken(String token) {
		String sql = "from " + Developer.class.getSimpleName() + " d " +
						"where d.developerToken = :token and d.enabled=true";
		return (Developer) getSession().getCurrentSession().createQuery(sql)
										.setString("token", token).uniqueResult();
		
	}

	@Override
	public void hibernateDelete(long id) {
		Developer s = new Developer();
		s.setId(id);
		getSession().getCurrentSession().delete(s);
	}
}