package linkjb.service;

import linkjb.dao.MessageDao;
import linkjb.pojo.Message;
import linkjb.service.serviceInterface.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messagedao;
    @Override
    public void save(Message message1) {
        messagedao.save(message1);
    }

    @Override
    public List<Map<String,Object>> findmessageByFlag(Integer ownerId, Integer flag) {
        return messagedao.findmessageByFlag(ownerId,flag);
    }
}
