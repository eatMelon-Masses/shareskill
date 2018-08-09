package com.shareskill.controllerimp;

import com.shareskill.dao.BaseDAO;
import com.shareskill.dao.BaseDaoImp;
import com.shareskill.model.TUser;
import com.shareskill.service.UserService;
import com.shareskill.serviceImp.UserServiceImp;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;



import static org.junit.Assert.*;

public class UserControllerImpForPhoneTest {
        UserServiceImp userService=null;
        TUser user =null;
        Integer id = null;
        JSONArray jsonArray=null;
        JSONObject jsonObject=null;
        private final static Log logger = LogFactory.getLog(UserControllerImpForPhoneTest.class);
    @Before
    public void setUp() throws Exception {
        //userService=new UserServiceImp();
        id=new Integer(1);
        user=new TUser();
        //BaseDAO dao = new BaseDaoImp();
        //userService.setDao(dao);
        //user.setId(new Integer(1));
       // jsonObject=new JSONObject();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void editMember() {
    }

    @Test
    public void updateMember() {
    }

    @Test
    public void viewMember() {
        //user=userService.loadMember(id);
        if (user!=null)
        jsonObject=JSONObject.fromObject(user);
        jsonObject.put("csrq","1999-10-1");
        jsonObject.put("csrq","2000-10-1");
        logger.info(jsonObject.toString());

    }
}