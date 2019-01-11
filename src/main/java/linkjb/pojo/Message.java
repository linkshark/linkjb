package linkjb.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Message {
    private Integer id;
    private Integer ownerId;//查看人的Id
    private Integer passerId;//被留言者的ID
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")

    private Date createTime;

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getPasserId() {
        return passerId;
    }

    public void setPasserId(Integer passerId) {
        this.passerId = passerId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", passerId=" + passerId +
                ", message='" + message + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
