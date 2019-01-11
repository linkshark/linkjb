package linkjb.service.serviceInterface;

import linkjb.pojo.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

    public User login (String userName, String pw) throws Exception;

    public User getUser(String userId) throws Exception;

    public void updateUser(User user);

    public User findUserById(Integer id);

    public List<User> findAllUser();

    public List<User> findUserByFlag(String findFlag, String userId);

    public User loginByMobile(String userName, String pw);

    public void addHeart(Integer likeId);


    public void insetIntoLikes(Integer ownerId, Integer likeId);

    public List<Integer> findLikesByOwnerId(Integer ownerId);

    public Map<String,Object> findCountAndTimeById(Integer ownerId, Integer likeId);

    public void updateLikeTime(Integer ownerId, Integer likeId, Date date);
    //查询黑名单表并返回影响行数
    public Integer addBlack(Integer userId, Integer reportId, String reportResult);

    List<User> findUserByFlagAndAddress(String IPAddress, String findFlag, String userId);
}