package com.shareskill.model;

/**
 * TLocalnetmemo entity. @author MyEclipse Persistence Tools
 */
public class TLocalnetmemo extends AbstractTLocalnetmemo implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TLocalnetmemo() {
	}

	/** minimal constructor */
	public TLocalnetmemo(String gwip) {
		super(gwip);
	}

	/** full constructor */
	public TLocalnetmemo(String bwnr, String gwip) {
		super(bwnr, gwip);
	}

}
