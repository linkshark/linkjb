package linkjb.pojo;


public class TMgtUser {
    private String userId;      //用户编号

    private String userName;    //用户名称

    private String enName;      //英文名字

    private String pw;          //密码

    private String sex;         //性别

    private String birthday;    //出生日期

    private String email;       //邮箱

    private String phone;       //联系方式

    private String addr;        //现住址

    private String homeTown;        //家乡

    private String chaneseId;       //身份证号

    private String deptId;          //部门编号

    private String jobId;           //岗位编号

    private String userLevel;       //用户等级

    private String status;          //状态

    private String img;             //图片地址

    private String creator;

    private String createTime;

    private String mender;

    private String mendTime;

    private String remark;

    /* 其它表字段  */
    private String sexName;     //性别名称
    private String deptName;    //部门名称
    private String jobName;     //岗位名称

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr == null ? null : addr.trim();
    }

    public String getHomeTown() {
        return homeTown;
    }

    public void setHomeTown(String homeTown) {
        this.homeTown = homeTown == null ? null : homeTown.trim();
    }

    public String getChaneseId() {
        return chaneseId;
    }

    public void setChaneseId(String chaneseId) {
        this.chaneseId = chaneseId == null ? null : chaneseId.trim();
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId == null ? null : deptId.trim();
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getMender() {
        return mender;
    }

    public void setMender(String mender) {
        this.mender = mender == null ? null : mender.trim();
    }

    public String getMendTime() {
        return mendTime;
    }

    public void setMendTime(String mendTime) {
        this.mendTime = mendTime == null ? null : mendTime.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}