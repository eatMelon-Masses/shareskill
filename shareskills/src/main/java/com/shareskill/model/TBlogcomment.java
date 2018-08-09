package com.shareskill.model;


import java.util.Date;

/**
 * TBlogcomment entity. @author MyEclipse Persistence Tools
 */
public class TBlogcomment extends AbstractTBlogcomment implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public TBlogcomment() {
	}

	/** full constructor */
	public TBlogcomment(TUser user, Integer blog, Date plsj, String plnr) {
		super(user,blog,plsj, plnr);
	}


}
