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
 * JPA entity that maps the GCD_SOURCE table in the database. 
 * GCD_SOURCE table stores all numbers sent by the user.
 * 
 * @author KevinWang
 *
 */
@Entity
@Table(name = "GCD_SOURCE")
public class GcdSource implements Serializable {
	private static final long serialVersionUID = -4264653533471864682L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NUMBER_1")
	private int number1;

	@Column(name = "NUMBER_2")
	private int number2;

	@Column(name = "ADDED_DATE")
	private Date addedDate;
	
	public GcdSource() {
	}

	public GcdSource(int number1, int number2) {
		this.number1 = number1;
		this.number2 = number2;
		
		this.addedDate = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	@Override
	public String toString() {
		return number1 + ", " + number2;
	}
}
