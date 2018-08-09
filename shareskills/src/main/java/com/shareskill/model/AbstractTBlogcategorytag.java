package com.shareskill.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * AbstractTBlogcategorytag entity provides the base persistence definition of
 * the TBlogcategorytag entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractTBlogcategorytag implements java.io.Serializable {

	// Fields
	@NotNull(message = "非法用户请求",groups = {BlogCateRuleB.class,BlogCateRuleC.class})
	private Integer id;
	private TUser user;
	@NotBlank(message = "分类名不能为空",groups = {BlogCateRuleA.class,BlogCateRuleC.class})
	private String flmc;

	// Constructors

	/** default constructor */
	public AbstractTBlogcategorytag() {
	}

	/** full constructor */
	public AbstractTBlogcategorytag(TUser user,String flmc) {
		this.user=user;
		this.flmc = flmc;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public TUser getUser() {
		return user;
	}

	public void setUser(TUser user) {
		this.user = user;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlmc() {
		return this.flmc;
	}

	public void setFlmc(String flmc) {
		this.flmc = flmc;
	}

	/**
	 * 增
	 */
	public interface BlogCateRuleA{}

	/**
	 * 查删
	 */
	public interface BlogCateRuleB{}

	/**
	 * 改
	 */
	public  interface BlogCateRuleC{}
}