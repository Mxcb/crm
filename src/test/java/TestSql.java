import com.ssm.exception.LoginException;
import com.ssm.settings.dao.DicTypeDao;
import com.ssm.settings.dao.DicValueDao;
import com.ssm.settings.domain.DicType;
import com.ssm.settings.domain.DicValue;
import com.ssm.settings.domain.User;
import com.ssm.settings.service.DicService;
import com.ssm.settings.service.UserService;
import com.ssm.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ContextConfiguration(locations = "classpath:applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSql {

//    @Autowired
//    private UserService userService;
//
    @Autowired
    private DicTypeDao dicDao;

    @Test
    public void test01() {
        System.out.println(dicDao.getTypeAll());
    }

}
