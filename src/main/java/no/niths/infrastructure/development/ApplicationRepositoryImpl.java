package no.niths.infrastructure.development;

import java.util.List;

import no.niths.domain.development.Application;
import no.niths.infrastructure.AbstractGenericRepositoryImpl;
import no.niths.infrastructure.development.interfaces.ApplicationRepository;

import org.springframework.stereotype.Repository;
/**
 * Repository class for Application
 *
 * <p>
 * Inherits the basic CRUD actions and has methods
 * for getByApplicationKey
 * and getTopApps
 * </p>
 */
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
     * {@inheritDoc}
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

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Application> getTopApps(int maxResults){
        String sql = "from " + Application.class.getSimpleName() + " a " +
                        " order by a.requests desc";
        return getSession().getCurrentSession().createQuery(sql)
                        .setMaxResults(maxResults).list();
    }
}