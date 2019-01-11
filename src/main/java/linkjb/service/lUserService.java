package linkjb.service;

import linkjb.dao.TMgtUserDAO;
import linkjb.pojo.BlackUser;
import linkjb.pojo.User;
import linkjb.service.serviceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class lUserService implements UserService {

    @Autowired
    private TMgtUserDAO tMgtUserMapper;

    @Override
    public User login(String userName, String pw) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "userName", userName);
        map.put( "pw", pw );
        return tMgtUserMapper.login( map );



    }

    @Override
    public User getUser(String userId) throws Exception {
        return tMgtUserMapper.getUser(userId);
    }

    @Override
    public void updateUser(User user) {
         tMgtUserMapper.updateUser(user);
    }

    @Override
    public User findUserById(Integer id) {
        return tMgtUserMapper.findUserById(id);
    }

    @Override
    public List<User> findAllUser() {
        return tMgtUserMapper.findAllUser();
    }

    @Override
    public List<User> findUserByFlag(String findFlag, String userId) {
        return tMgtUserMapper.findUserByFlag(findFlag,userId);
    }

    @Override
    public User loginByMobile(String userName, String pw) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put( "userName", userName);
        map.put( "pw", pw );
        return tMgtUserMapper.loginByMobile(map);
    }

    @Override
    public void addHeart(Integer likeId) {
        tMgtUserMapper.addHeart(likeId);
    }

    @Override
    public void insetIntoLikes(Integer ownerId, Integer likeId) {
        Date newDate =new Date();
        tMgtUserMapper.insetIntoLikes(ownerId,likeId,newDate);
    }

    @Override
    public List<Integer> findLikesByOwnerId(Integer ownerId) {
        return tMgtUserMapper.findLikesByOwnerId(ownerId);
    }

    @Override
    public Map<String,Object> findCountAndTimeById(Integer ownerId, Integer likeId) {

        return tMgtUserMapper.findCountAndTimeById(ownerId,likeId);
    }

    @Override
    public void updateLikeTime(Integer ownerId, Integer likeId, Date date) {
        tMgtUserMapper.updateLikeTime( ownerId,  likeId,date);
    }

    @Override
    public Integer addBlack(Integer userId, Integer reportId, String reportResult) {
        BlackUser user =new BlackUser();
        user.setCreateTime(new Date());
        user.setUserId(userId);
        user.setReportId(reportId);
        user.setReportResult(reportResult);
        return tMgtUserMapper.addBlack(user);
    }

    @Override
    public List<User> findUserByFlagAndAddress(String IPAddress, String findFlag,String userId) {
        return tMgtUserMapper.findUserByFlagAndAddress(IPAddress,findFlag,userId);
    }


}
