package com.enlinkmob.ucenterapi.model;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Document(collection = "users")
public class MongoUser extends BaseEntity {

    private static final long serialVersionUID = -3814199231759885239L;
    //	@Id
//	@JsonIgnore
//	private ObjectId _id;
    @Indexed(name = "userName", unique = true, dropDups = true, background = true, direction = IndexDirection.ASCENDING)
    private String userName;
    private String password;
    @DBRef
    private List<Role> authorities;
    @DBRef
    private MongoOAuthClientDetails mongoclient;
    @Transient
    private List<String> roles;
    private String realName;//真实姓名
    private String nickName;//昵称
    private String idNo;//身份证号
    private String phoneNum;//电话号
    private String email;//电子邮箱
    private String address;//地址
    private String city;//城市 与基础库关联
    private List<String> tags;//用户tag
    private Date birthday;//生日
    private int age;//年龄
    private String nation;//民族
    private String industry;//行业
    private String profession;//职业
    private String workUnit;//工作单位
    private String country;//国籍
    private String province;//省份
    private String businessDistrict;//商圈
    private int isMarried;//婚否
    private int hasChildren;//是否有孩子
    private Map<String, String> headIcon;//头像
    private String sex;//male  female
    private List<String> bindAccounts;//已绑定账号
    private int accountStatus;//当前账号状态  1正常 -1已被绑定

    private int userType;//用户类型  0 用户 1厂商 ......

    public List<String> getBindAccounts() {
        return bindAccounts;
    }

    public void setBindAccounts(List<String> bindAccounts) {
        this.bindAccounts = bindAccounts;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    //	public ObjectId get_id() {
//		return _id;
//	}
//	public void set_id(ObjectId _id) {
//		this._id = _id;
//	}
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MongoOAuthClientDetails getMongoclient() {
        return mongoclient;
    }

    public void setMongoclient(MongoOAuthClientDetails mongoclient) {
        this.mongoclient = mongoclient;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, String> getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(Map<String, String> headIcon) {
        this.headIcon = headIcon;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }


    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBusinessDistrict() {
        return businessDistrict;
    }

    public void setBusinessDistrict(String businessDistrict) {
        this.businessDistrict = businessDistrict;
    }

    public int getIsMarried() {
        return isMarried;
    }

    public void setIsMarried(int isMarried) {
        this.isMarried = isMarried;
    }

    public int getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(int hasChildren) {
        this.hasChildren = hasChildren;
    }
}
