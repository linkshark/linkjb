package linkjb.service;

import linkjb.dao.RegistDao;
import linkjb.pojo.User;
import linkjb.service.serviceInterface.RegistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistServiceImpl implements RegistService {
    @Autowired
    private RegistDao registdao;



    @Override
    public void Regist(User user)  {
        registdao.Regist(user);
    }

    @Override
    public int checkName(String name) {
        int a = registdao.checkName(name);
        return a;
    }

    @Override
    public int checkEmail(String email) {
        int a = registdao.checkEmail(email);
        return a;
    }

    @Override
    public int checkMobile(String mobile) {
        int a = registdao.checkMobile(mobile);
        return a;
    }
}
