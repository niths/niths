package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;

@Repository
public class AccessFieldRepositoryImpl extends AbstractGenericRepositoryImpl<AccessField> implements AccessFieldRepository {

	public AccessFieldRepositoryImpl() {
		super(AccessField.class);
	}

	@Override
	public void hibernateDelete(long id) {
		AccessField af = new AccessField();
		af.setId(id);
		getSession().getCurrentSession().delete(af);	
	}

}
