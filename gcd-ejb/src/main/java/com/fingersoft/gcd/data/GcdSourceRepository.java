package com.fingersoft.gcd.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.fingersoft.gcd.model.GcdSource;

/**
 * Repository for fetching numbers from the database.
 * 
 * @author KevinWang
 *
 */
@ApplicationScoped
public class GcdSourceRepository {
	@Inject
    private EntityManager em;
    
	/**
	 * Fetch all numbers added to the database.
	 * 
	 * @return A list of objects in the order added.
	 */
    public List<GcdSource> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GcdSource> criteria = cb.createQuery(GcdSource.class);
        Root<GcdSource> source = criteria.from(GcdSource.class);
        criteria.select(source).orderBy(cb.asc(source.get("addedDate")));
        return em.createQuery(criteria).getResultList();
    }
}
