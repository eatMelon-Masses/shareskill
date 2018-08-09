package com.shareskill.model;




import com.shareskill.utils.JsonDateSerializer;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
/**
 * TUser entity. @author MyEclipse Persistence Tools
 */

public class TUser implements java.io.Serializable {
	//String datePattern="^((\\d{2}(([02468][048])|([13579][26]))[\\/\\/\\s]?((((0?"+"[13578])|(1[02]))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +"|(((0?[469])|(11))[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|" +"(0?2[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][12"+"35679])|([13579][01345789]))[\\/\\/\\s]?((((0?[13578])|(1[02]))" +"[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))" +"[\\/\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\/\\/\\s]?((0?[" +"1-9])|(1[0-9])|(2[0-8]))))))";
	// Fields
	@NotNull(message = "非法请求参数,",groups = {TUserRuleD.class})
	private Integer id;
	@NotBlank(message="昵称不能为空,",groups = {TUserRuleA.class})
	private String yhnc;//昵称
	//@NotBlank(message = "姓名不能为空",groups = {TUserRuleb.class})
	private String xm;//姓名
	@NotBlank(message = "注册账号不能为空,",groups = {TUserRuleA.class,TUserRuleb.class,TUserRuleC.class})
    @Pattern(regexp = "^1(3|4|5|7|8)\\d{9}$",message = "账号必须为手机号,",groups = {TUserRuleA.class,TUserRuleb.class,TUserRuleC.class})
	private String zh;//账号
	@NotBlank(message = "注册密码不能为空,",groups = {TUserRuleA.class,TUserRuleb.class,TUserRuleC.class})
	@Size(min = 6,max = 10,message = "密码必须是6-10位",groups = {TUserRuleA.class,TUserRuleD.class})
	private String dlmm;//登录密码
    @NotBlank(message = "性别不能为空,",groups = {TUserRuleA.class,TUserRuleb.class})
	private String xb;//性别

	//@DateTimeFormat(pattern="yyyy-MM-dd")
	//@JsonSerializec(using= JsonDateSerializer.class)
	@JsonSerialize(using =JsonDateSerializer.class )
	/*@NotBlank(message="生日不能为空,",groups = {TUserRuleA.class,TUserRuleb.class})*/
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past(message = "生日必须是过去的时间,",groups = {TUserRuleA.class,TUserRuleb.class,TUserRuleD.class})
	//@Pattern(regexp ="^\\d{4}\\D+\\d{2}\\D+\\d{2}$",message = "日期格式有误需为yyyy-mm-dd")
	public Date csny;//出生年月
	private String grjj;//个人简介
	private String jybs;//
	private String userImg;
	private String userBgImg;

	public String getUserBgImg() {
		return userBgImg;
	}

	public void setUserBgImg(String userBgImg) {
		this.userBgImg = userBgImg;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}
	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String zh, String dlmm, String xb, Date csny, String jybs) {
		this.zh = zh;
		this.dlmm = dlmm;
		this.xb = xb;
		this.csny = csny;
		this.jybs = jybs;
	}

	/** full constructor */
	public TUser(String yhnc, String xm, String zh, String dlmm, String xb, Date csny, String grjj, String jybs) {
		this.yhnc = yhnc;
		this.xm = xm;
		this.zh = zh;
		this.dlmm = dlmm;
		this.xb = xb;
		this.csny = csny;
		this.grjj = grjj;
		this.jybs = jybs;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYhnc() {
		return this.yhnc;
	}

	public void setYhnc(String yhnc) {
		this.yhnc = yhnc;
	}

	public String getXm() {
		return this.xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getZh() {
		return this.zh;
	}

	public void setZh(String zh) {
		this.zh = zh;
	}

	public String getDlmm() {
		return this.dlmm;
	}

	public void setDlmm(String dlmm) {
		this.dlmm = dlmm;
	}

	public String getXb() {
		return this.xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	public Date getCsny() {
		return this.csny;
	}

	public void setCsny(Date csny) {
		this.csny = csny;
	}

	public String getGrjj() {
		return this.grjj;
	}

	public void setGrjj(String grjj) {
		this.grjj = grjj;
	}

	public String getJybs() {
		return this.jybs;
	}

	public void setJybs(String jybs) {
		this.jybs = jybs;
	}

	@Override
	public String toString() {
		return "TUser{" +
				"id=" + id +
				", yhnc='" + yhnc + '\'' +
				", xm='" + xm + '\'' +
				", zh='" + zh + '\'' +
				", dlmm='" + dlmm + '\'' +
				", xb='" + xb + '\'' +
				", csny=" + csny +
				", grjj='" + grjj + '\'' +
				", jybs='" + jybs + '\'' +
				", userImg='" + userImg + '\'' +
				'}';
	}

	/**
	 * 注册 手机端 web端后台管理端
	 */
	public interface TUserRuleA{

	};
	/**
	 * web前端验证
	 */
	public interface TUserRuleb{

	}

	/**
	 * 用户登录验证
	 */
	public interface TUserRuleC{}

	/**
	 * id验证
	 */
	public interface TUserRuleD{}
}