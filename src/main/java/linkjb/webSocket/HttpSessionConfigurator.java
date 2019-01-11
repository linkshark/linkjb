package linkjb.webSocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

/**
 * @author scq
 * @Description
 * @date 2018/11/13 15:43
 */
public class HttpSessionConfigurator extends ServerEndpointConfig.Configurator{

    @Override
    public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
        HttpSession session = (HttpSession) request.getHttpSession();
        config.getUserProperties().put(HttpSession.class.getName(), session);
    }
}