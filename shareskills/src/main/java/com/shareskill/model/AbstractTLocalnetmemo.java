package com.shareskill.model;

/**
 * AbstractTLocalnetmemo entity provides the base persistence definition of the
 * TLocalnetmemo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTLocalnetmemo implements java.io.Serializable {

	// Fields

	private int id;
	private String bwnr;
	private String gwip;

	// Constructors

	/** default constructor */
	public AbstractTLocalnetmemo() {
	}

	/** minimal constructor */
	public AbstractTLocalnetmemo(String gwip) {
		this.gwip = gwip;
	}

	/** full constructor */
	public AbstractTLocalnetmemo(String bwnr, String gwip) {
		this.bwnr = bwnr;
		this.gwip = gwip;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBwnr() {
		return this.bwnr;
	}

	public void setBwnr(String bwnr) {
		this.bwnr = bwnr;
	}

	public String getGwip() {
		return this.gwip;
	}

	public void setGwip(String gwip) {
		this.gwip = gwip;
	}

}