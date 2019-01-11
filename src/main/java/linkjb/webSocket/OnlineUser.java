package linkjb.webSocket;

import javax.websocket.Session;

public class OnlineUser {
    private String userid;
    private String nickname;
    private Session session;

    public OnlineUser() {
    }

    public OnlineUser(String userid, String nickname, Session session) {
        this.userid = userid;
        this.nickname = nickname;
        this.session = session;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid;
    }
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "OnlineUser{" +
                "userid='" + userid + '\'' +
                ", nickname='" + nickname + '\'' +
                ", session=" + session +
                '}';
    }
}