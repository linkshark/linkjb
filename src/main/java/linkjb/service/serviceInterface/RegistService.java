package linkjb.service.serviceInterface;

import linkjb.pojo.User;

public interface RegistService {
    public void Regist(User user) ;

    int checkName(String name);

    int checkEmail(String email);

    int checkMobile(String mobile);
}
