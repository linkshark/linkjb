package linkjb.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class User {
    private int id; //前台自动生成的UUID
    private String name; //用户名
    private Integer age; // 用户年龄
    private String email; //用户邮箱地址
    private String passWord; //登录的密码,前期不加密,后期采用MD5加盐的加密方式
    private String mobile; //电话号码
    private String gender; // 男1 女2
    private String findGender; // 男1 女2
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday; //生日
    private String imageUrl; //图片地址
    private String findFlag; //1:男找女 2:男找男 3:女找男 4:女找女
    /**
     * 以下为用户编辑详情字段
     */
    private String bodyShape; // 1:苗条型 2:标准型 3:丰满型 4:保密
    private String job;//前台维护or后台维护待议
    private Integer tall;//身高 cm
    private String marriageFlag; // 是否结过婚 0结婚过 1未曾婚
    private String education ; // 教育程度 0未曾上过学 1小学 2中学 3高中 4大专 5本科 6硕士 7 博士

    /*
    新增字段
     */
    private Integer weight;//体重
    private String money;//年收入 /万元
    private String drink; //是否 0不喝酒 1偶尔喝酒 2喜欢喝酒
    private String smoke;//是否抽烟 0不抽烟 1偶尔抽烟 2喜欢抽烟
    private String child; //是否要孩子 0没有意向 1有意向 2不清楚
    private String sport; //是否运动 0几乎不运动 1偶尔运动 2经常运动
    private String detail; //个人介绍
    private String address; //居住地址
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createTime; //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date modifyTime;  //最近修改时间
    private Integer heartLove; //点赞数目
    private List<Integer> likeId; //喜爱的Id


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFindGender() {
        return findGender;
    }

    public void setFindGender(String findGender) {
        this.findGender = findGender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFindFlag() {
        return findFlag;
    }

    public void setFindFlag(String findFlag) {
        this.findFlag = findFlag;
    }

    public String getBodyShape() {
        return bodyShape;
    }

    public void setBodyShape(String bodyShape) {
        this.bodyShape = bodyShape;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Integer getTall() {
        return tall;
    }

    public void setTall(Integer tall) {
        this.tall = tall;
    }

    public String getMarriageFlag() {
        return marriageFlag;
    }

    public void setMarriageFlag(String marriageFlag) {
        this.marriageFlag = marriageFlag;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getSmoke() {
        return smoke;
    }

    public void setSmoke(String smoke) {
        this.smoke = smoke;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Integer getHeartLove() {
        return heartLove;
    }

    public void setHeartLove(Integer heartLove) {
        this.heartLove = heartLove;
    }

    public List<Integer> getLikeId() {
        return likeId;
    }

    public void setLikeId(List<Integer> likeId) {
        this.likeId = likeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender='" + gender + '\'' +
                ", findGender='" + findGender + '\'' +
                ", birthday=" + birthday +
                ", imageUrl='" + imageUrl + '\'' +
                ", findFlag='" + findFlag + '\'' +
                ", bodyShape='" + bodyShape + '\'' +
                ", job='" + job + '\'' +
                ", tall=" + tall +
                ", marriageFlag='" + marriageFlag + '\'' +
                ", education='" + education + '\'' +
                ", weight=" + weight +
                ", money='" + money + '\'' +
                ", drink='" + drink + '\'' +
                ", smoke='" + smoke + '\'' +
                ", child='" + child + '\'' +
                ", sport='" + sport + '\'' +
                ", detail='" + detail + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", modifyTime=" + modifyTime +
                ", heartLove=" + heartLove +
                ", likeId=" + likeId +
                '}';
    }
}
