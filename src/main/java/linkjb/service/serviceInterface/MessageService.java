package linkjb.service.serviceInterface;

import linkjb.pojo.Message;

import java.util.List;
import java.util.Map;

public interface MessageService {
    public void save(Message message1);

    public List<Map<String,Object>> findmessageByFlag(Integer ownerId, Integer flag);
}
