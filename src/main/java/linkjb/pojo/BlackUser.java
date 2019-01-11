package linkjb.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BlackUser {
    private Integer id; //主键
    private Integer userId; //被举报者ID
    private Integer reportId; //举报者ID
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime; //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updateTime; //更新时间
    private String reportResult; //举报原因
    private Integer status; //状态值 0为失效 1为有效 默认为1

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getReportResult() {
        return reportResult;
    }

    public void setReportResult(String reportResult) {
        this.reportResult = reportResult;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BlackUser{" +
                "id=" + id +
                ", userId=" + userId +
                ", reportId=" + reportId +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", reportResult='" + reportResult + '\'' +
                ", status=" + status +
                '}';
    }
}
