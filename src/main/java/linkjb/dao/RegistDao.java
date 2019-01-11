package linkjb.dao;

import linkjb.pojo.User;

public interface RegistDao {
    public void Regist(User user);

    public int checkName(String name);

    public int checkEmail(String email);

    public int checkMobile(String mobile);
}
