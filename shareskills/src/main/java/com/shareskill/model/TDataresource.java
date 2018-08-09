package com.shareskill.model;

import com.shareskill.utils.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * TDataresource entity. @author MyEclipse Persistence Tools
 */

public class TDataresource implements java.io.Serializable {

	// Fields
	@NotNull(message = "非法请求参数",groups = {TDataresourceD.class})
	private Integer id;

	private TUser userId;
	private String zybt;//资源标题
	private String zylx;//资源类型
	private String gjbq;//标签
	private String zyjj;//简介
	@JsonSerialize(using= JsonDateTimeSerializer.class)
	private Date scsj;//双穿时间
	private Integer djcs;//点击次数
	private String zylj;//资源路径文件夹
	private String yhnc;

	public String getYhnc() {
		return yhnc;
	}

	public void setYhnc(String yhnc) {
		this.yhnc = yhnc;
	}
	// Constructors

	/** default constructor */
	public TDataresource() {
	}

	/** minimal constructor */
	public TDataresource(String zybt, String zylx, String gjbq, String zyjj, Date scsj, Integer djcs, String zylj) {
		this.zybt = zybt;
		this.zylx = zylx;
		this.gjbq = gjbq;
		this.zyjj = zyjj;
		this.scsj = scsj;
		this.djcs = djcs;
		this.zylj = zylj;
	}

	/** full constructor */
	public TDataresource(TUser userId, String zybt, String zylx, String gjbq, String zyjj, Date scsj, Integer djcs,
						 String zylj) {
		this.userId = userId;
		this.zybt = zybt;
		this.zylx = zylx;
		this.gjbq = gjbq;
		this.zyjj = zyjj;
		this.scsj = scsj;
		this.djcs = djcs;
		this.zylj = zylj;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public TUser getUserId() {
		return userId;
	}

	public void setUserId(TUser userId) {
		this.userId = userId;
	}

	public String getZybt() {
		return this.zybt;
	}

	public void setZybt(String zybt) {
		this.zybt = zybt;
	}

	public String getZylx() {
		return this.zylx;
	}

	public void setZylx(String zylx) {
		this.zylx = zylx;
	}

	public String getGjbq() {
		return this.gjbq;
	}

	public void setGjbq(String gjbq) {
		this.gjbq = gjbq;
	}

	public String getZyjj() {
		return this.zyjj;
	}

	public void setZyjj(String zyjj) {
		this.zyjj = zyjj;
	}

	public Date getScsj() {
		return this.scsj;
	}

	public void setScsj(Date scsj) {
		this.scsj = scsj;
	}

	public Integer getDjcs() {
		return this.djcs;
	}

	public void setDjcs(Integer djcs) {
		this.djcs = djcs;
	}

	public String getZylj() {
		return this.zylj;
	}

	public void setZylj(String zylj) {
		this.zylj = zylj;
	}

	/**
	 * 删规则
	 */
	public interface TDataresourceD{}
}