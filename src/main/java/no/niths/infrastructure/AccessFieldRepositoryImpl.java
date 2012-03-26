package no.niths.infrastructure;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;

import org.springframework.stereotype.Repository;

@Repository
public class AccessFieldRepositoryImpl extends
		AbstractGenericRepositoryImpl<AccessField> implements
		AccessFieldRepository {

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
