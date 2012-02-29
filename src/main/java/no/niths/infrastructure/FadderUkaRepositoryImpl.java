package no.niths.infrastructure;

import org.springframework.stereotype.Repository;

import no.niths.domain.FadderUka;
import no.niths.infrastructure.interfaces.FadderUkaRepository;

@Repository
public class FadderUkaRepositoryImpl extends GenericRepositoryImpl<FadderUka> implements FadderUkaRepository {

	public FadderUkaRepositoryImpl() {
		super(FadderUka.class);
	}

	@Override
	public void hibernateDelete(long id) {
		FadderUka f = new FadderUka();
		f.setId(id);
		getSession().getCurrentSession().delete(f);
	}
}
