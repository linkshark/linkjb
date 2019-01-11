package linkjb.dao;

import linkjb.pojo.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface MessageDao {
    public void save(Message message1);

    public List<Map<String,Object>> findmessageByFlag(@Param(value = "ownerId") Integer ownerId, @Param(value = "flag") Integer flag);
}
