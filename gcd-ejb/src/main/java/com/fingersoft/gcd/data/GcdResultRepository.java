package com.fingersoft.gcd.data;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.fingersoft.gcd.model.GcdResult;

/**
 * Repository for fetching GCDs from the database.
 * 
 * @author KevinWang
 *
 */
@ApplicationScoped
public class GcdResultRepository {
	@Inject
    private EntityManager em;
	
	/**
	 * Fetches all GCDs added to the database.
	 * 
	 * @return A list of GCDs in the order computed.
	 */
	public List<GcdResult> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<GcdResult> criteria = cb.createQuery(GcdResult.class);
        Root<GcdResult> result = criteria.from(GcdResult.class);
        criteria.select(result).orderBy(cb.asc(result.get("computedDate")));
        return em.createQuery(criteria).getResultList();
    }
	
	/**
	 * Calculate the sum of all computed GCDs.
	 * 
	 * @return the sum of all computed GCDs.
	 */
	public Integer sumAll() {
        Query query = em.createQuery ("SELECT SUM(a.gcd) FROM GcdResult a");
        Number sum = (Number)query.getSingleResult();
        
        return sum == null? 0 : sum.intValue();
    }
}
