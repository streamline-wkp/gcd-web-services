package com.fingersoft.gcd.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * JPA entity that maps the GCD_RESULT table in the database. 
 * GCD_RESULT table stores all computed GCDs.
 * 
 * @author KevinWang
 *
 */
@Entity
@Table(name = "GCD_RESULT")
public class GcdResult implements Serializable {
	private static final long serialVersionUID = 6624802499312278247L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "GCD")
	private int gcd;

	@Column(name = "COMPUTED_DATE")
	private Date computedDate;
	
	public GcdResult() {
	}

	public GcdResult(int gcd) {
		this.gcd = gcd;
		this.computedDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGcd() {
		return gcd;
	}

	public void setGcd(int gcd) {
		this.gcd = gcd;
	}

	public Date getComputedDate() {
		return computedDate;
	}

	public void setComputedDate(Date computedDate) {
		this.computedDate = computedDate;
	}

	@Override
	public String toString() {
		return String.valueOf(gcd);
	}
}
