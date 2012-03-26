package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.signaling.AccessField;
import no.niths.infrastructure.interfaces.AccessFieldRepository;

@Repository
public class AccessFieldRepositoryImpl extends
		AbstractGenericRepositoryImpl<AccessField> implements
		AccessFieldRepository {

	public AccessFieldRepositoryImpl() {
		super(AccessField.class, new AccessField());
	}
}
