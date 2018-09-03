package com.shareskill.controllerimp;

import com.shareskill.controller.BlogoSphereController_json;
import com.shareskill.model.BlogoSphere;
import com.shareskill.model.TUser;
import com.shareskill.service.BlogoSphereService;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BlogoSphereControllerImp_json implements BlogoSphereController_json {
    private static final Log logger = LogFactory.getLog(BlogoSphereControllerImp_json.class);
    @Autowired
    private BlogoSphereService blogoSphereService;
    final static String RESULT = "result";
    final static String MESSAGE = "message";
    final static String TRUE = "true";
    final static String FALSE = "false";

    /**
     * 返回所有说说按照时间降序排
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/browseAllBlogoSphere_json")
    @Override
    public List<Object> browseAllBlogoSphere_json() {
        List<Object> list = new ArrayList();
        List<BlogoSphere> bcList=null;
        JSONObject massage=new JSONObject();

        massage.put("operation", "success");

        bcList = blogoSphereService.browseAllBlogSphere();
        if (bcList.size() != 0) {//如果获取到为用户信息不为为0
            for (BlogoSphere bs:bcList) {
                bs.setYhnc(bs.getUser().getYhnc());
                bs.setUser(null);
            }
        } else {
            massage.put("operation", "failure");
        }
        list.add(0,massage);
        list.addAll(bcList);

        return list;
    }

    @ResponseBody
    @RequestMapping("/json/delBlogSphereById")
    @Override
    public List<Object> delBlogSphereById(HttpServletRequest request, @Validated(BlogoSphere.BSRuleB.class) BlogoSphere bsId, BindingResult bindingResult) {

        List<Object> jsonList = new ArrayList<>();
        StringBuffer message = new StringBuffer();
        Map<String, Object> mapList = new HashMap<>();
        mapList.put(RESULT, FALSE);
        mapList.put(MESSAGE, message);
        jsonList.add(mapList);
        if (bindingResult.hasErrors()) {//遍历错误信息
            List<ObjectError> errorList = bindingResult.getAllErrors();
            for (ObjectError error : errorList) {
                logger.info(error.getDefaultMessage());
                message.append(error.getDefaultMessage());
            }
            return jsonList;
        }

        BlogoSphere bs = blogoSphereService.loadBlogSphereById(bsId.getId());

        if (blogoSphereService.delUserWordById(bs.getId())) {
            mapList.put(RESULT, TRUE);
            message.append("删除成功");
        } else {
            mapList.put(RESULT, FALSE);
            message.append("删除失败");
        }




        return jsonList;
    }
    @ResponseBody
    @ResourceMapping("/json/browseMyBlogSphere")
    @Override
    public List<BlogoSphere> browseMyBlogoSphere(@Validated(TUser.TUserRuleD.class) TUser user,BindingResult bindingResult) {
        logger.info("userid"+user.getId());
        return blogoSphereService.browseBlogSphereByUser(user.getId());
    }


}
