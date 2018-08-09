package com.shareskill.model;

/**
 * TAdmin entity. @author MyEclipse Persistence Tools
 */

public class TAdmin implements java.io.Serializable {

	// Fields

	private Integer id;
	private String zh;
	private String mm;
	private String ylzd;

	// Constructors

	/** default constructor */
	public TAdmin() {
	}

	/** minimal constructor */
	public TAdmin(String zh, String mm) {
		this.zh = zh;
		this.mm = mm;
	}

	/** full constructor */
	public TAdmin(String zh, String mm, String ylzd) {
		this.zh = zh;
		this.mm = mm;
		this.ylzd = ylzd;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getZh() {
		return this.zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getMm() {
		return this.mm;
	}

	public void setMm(String mm) {
		this.mm = mm;
	}

	public String getYlzd() {
		return this.ylzd;
	}

	public void setYlzd(String ylzd) {
		this.ylzd = ylzd;
	}

}