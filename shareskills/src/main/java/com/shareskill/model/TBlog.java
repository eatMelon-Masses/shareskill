package com.shareskill.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shareskill.utils.JsonDateSerializer;
import com.shareskill.utils.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * TBlog entity. @author MyEclipse Persistence Tools
 */

public class TBlog implements java.io.Serializable {

	// Fields
	@NotNull(message = "非法请求参数",groups = {BlogRuleD.class})
	private Integer id;
	@NotNull(message = "用户id不能为空",groups = {BlogRuleB.class})
	private Integer userId;
	@NotNull(message = "分类id不能为空",groups = {BlogRuleB.class})
	private Integer blogCategoryId;
	@NotBlank(message = "标题不能为空",groups = {BlogRuleB.class})
	private String bwbt;
	private String bwjj;
	@NotBlank(message = "内容不能为空",groups = {BlogRuleB.class})
	private String bwnr;
	private Integer bwdjcs;
	@JsonSerialize(using= JsonDateTimeSerializer.class)
	private Date bwcjsj;
	private String editor;

	// Constructors

	/** default constructor */
	public TBlog() {
	}

	/** minimal constructor */
	public TBlog(String bwbt, String bwjj, String bwnr, Integer bwdjcs, Timestamp bwcjsj) {
		this.bwbt = bwbt;
		this.bwjj = bwjj;
		this.bwnr = bwnr;
		this.bwdjcs = bwdjcs;
		this.bwcjsj = bwcjsj;
	}

	/** full constructor */
	public TBlog(Integer userId, Integer blogCategoryId, String bwbt, String bwjj, String bwnr, Integer bwdjcs,
			Timestamp bwcjsj) {
		this.userId = userId;
		this.blogCategoryId = blogCategoryId;
		this.bwbt = bwbt;
		this.bwjj = bwjj;
		this.bwnr = bwnr;
		this.bwdjcs = bwdjcs;
		this.bwcjsj = bwcjsj;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBlogCategoryId() {
		return this.blogCategoryId;
	}

	public void setBlogCategoryId(Integer blogCategoryId) {
		this.blogCategoryId = blogCategoryId;
	}

	public String getBwbt() {
		return this.bwbt;
	}

	public void setBwbt(String bwbt) {
		this.bwbt = bwbt;
	}

	public String getBwjj() {
		return this.bwjj;
	}

	public void setBwjj(String bwjj) {
		this.bwjj = bwjj;
	}

	public String getBwnr() {
		return this.bwnr;
	}

	public void setBwnr(String bwnr) {
		this.bwnr = bwnr;
	}

	public Integer getBwdjcs() {
		return this.bwdjcs;
	}

	public void setBwdjcs(Integer bwdjcs) {
		this.bwdjcs = bwdjcs;
	}
	/*@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
	public Date getBwcjsj() {
		return bwcjsj;
	}

	public void setBwcjsj(Date bwcjsj) {
		this.bwcjsj = bwcjsj;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	/**
	 * Returns a string representation of the object. In general, the
	 * {@code toString} method returns a string that
	 * "textually represents" this object. The result should
	 * be a concise but informative representation that is easy for a
	 * person to read.
	 * It is recommended that all subclasses override this method.
	 * <p>
	 * The {@code toString} method for class {@code Object}
	 * returns a string consisting of the name of the class of which the
	 * object is an instance, the at-sign character `{@code @}', and
	 * the unsigned hexadecimal representation of the hash code of the
	 * object. In other words, this method returns a string equal to the
	 * value of:
	 * <blockquote>
	 * <pre>
	 * getClass().getName() + '@' + Integer.toHexString(hashCode())
	 * </pre></blockquote>
	 *
	 * @return a string representation of the object.
	 */
	@Override
	public String toString() {
		return new String("id:"+getId()+"categoryid"+getBlogCategoryId()+"博文标签:"+getBwbt()+"博文缩略"+getBwjj()+"博文时间"+getBwcjsj());
	}

	/**
	 * 删,查(通过id)
	 */
	public  interface BlogRuleD{}

	/**
	 * 手机端 新增博客规则
	 */
	public interface BlogRuleB{}
}