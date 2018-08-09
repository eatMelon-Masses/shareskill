package com.shareskill.model;


import com.shareskill.utils.JsonDateSerializer;
import com.shareskill.utils.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * AbstractTBlogcomment entity provides the base persistence definition of the
 * TBlogcomment entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTBlogcomment implements java.io.Serializable {

	// Fields

	private int id;
	private TUser user;
	private Integer blog;
	@JsonSerialize(using= JsonDateTimeSerializer.class)
	private Date plsj;
	private String plnr;

	// Constructors

	/** default constructor */
	public AbstractTBlogcomment() {
	}

	/** full constructor */
	public AbstractTBlogcomment(TUser user,Integer blog,Date plsj, String plnr) {
		this.user=user;
		this.blog=blog;
		this.plsj = plsj;
		this.plnr = plnr;
	}

	@Override
	public String toString() {
		return "AbstractTBlogcomment{" +
				"id=" + id +
				", plsj=" + plsj +
				", plnr='" + plnr + '\'' +
				'}';
	}
// Property accessors


	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public Integer getBlog() {
		return blog;
	}

	public void setBlog(Integer blog) {
		this.blog = blog;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getPlsj() {
		return plsj;
	}

	public void setPlsj(Date plsj) {
		this.plsj = plsj;
	}

	public String getPlnr() {
		return this.plnr;
	}

	public void setPlnr(String plnr) {
		this.plnr = plnr;
	}

}