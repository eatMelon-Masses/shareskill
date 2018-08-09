package com.shareskill.model;

import com.shareskill.utils.JsonDateSerializer;
import com.shareskill.utils.JsonDateTimeSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class BlogoSphere {
    @NotNull(message = "非法请求参数",groups = {BSRuleB.class})
    int id;//编号
    TUser user;//用户
    String yhnc;//用户昵称不是映射字段

    public String getYhnc() {
        return yhnc;
    }

    public void setYhnc(String yhnc) {
        this.yhnc = yhnc;
    }
    @NotBlank(message = "请写点内容再提交吧",groups = {BSRuleA.class})
    String userWord;// 用户一条说说
    @JsonSerialize(using= JsonDateTimeSerializer.class)
    Date wordDate;

    public Date getWordDate() {
        return wordDate;
    }

    public void setWordDate(Date wordDate) {
        this.wordDate = wordDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    @Override
    public String toString() {
        return "BlogoSphere{" +
                "id=" + id +
                ", user=" + user +
                ", userWord='" + userWord + '\'' +
                ", wordDate=" + wordDate +
                '}';
    }

    /**
     * 增
     */
    public interface BSRuleA{

    }

    /**
     * 删 查
     */
    public interface BSRuleB{}
}
