package linkjb.dao;

import linkjb.pojo.BlackUser;
import linkjb.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TMgtUserDAO {

    public User login(Map<String, Object> map);

    public User getUser(String userId);

    public void updateUser(User user);

    public User findUserById(Integer id);

    public List<User> findAllUser();

    public List<User> findUserByFlag(@Param(value = "findFlag") String findFlag, @Param(value = "userId") String userId);

    public User loginByMobile(Map<String, Object> map);

    public void addHeart(Integer likeId);

    public void insetIntoLikes(@Param(value="ownerId") Integer ownerId, @Param(value="likeId")Integer likeId,@Param(value = "newDate")Date date);

    public List<Integer> findLikesByOwnerId(Integer ownerId);

    public Map<String,Object> findCountAndTimeById(@Param(value = "ownerId") Integer ownerId, @Param(value = "likeId") Integer likeId);

    public void updateLikeTime(@Param(value = "ownerId")Integer ownerId, @Param(value = "likeId")Integer likeId, @Param(value = "newDate") Date Date);

    public Integer addBlack(BlackUser user);
    public User getUserByUserId(String userId);

    List<User> findUserByFlagAndAddress(@Param(value = "IPAddress") String IPAddress, @Param(value = "findFlag") String findFlag,@Param(value = "userId") String userId);
}
