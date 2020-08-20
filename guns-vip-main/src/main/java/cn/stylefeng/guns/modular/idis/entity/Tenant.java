package cn.stylefeng.guns.modular.idis.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 租户管理
 * </p>
 *
 * @author hanqw
 * @since 2019-10-18
 */
@TableName("t_tenant")
public class Tenant implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 统一社会信用代码
     */
    @TableField("tenant_credit_code")
    private String tenantCreditCode;

    /**
     * 企业名称
     */
    @TableField("tenant_name")
    private String tenantName;

    /**
     * 推荐码
     */
    @TableField("referral_code")
    private String referralCode;

    /**
     * 所在地（省）
     */
    @TableField("province")
    private String province;

    /**
     * 所在地（市）
     */
    @TableField("city")
    private String city;

    /**
     * 所在地（区）
     */
    @TableField("district")
    private String district;

    /**
     * 地址
     */
    @TableField("tenant_address")
    private String tenantAddress;

    /**
     * 企业法定代表人
     */
    @TableField("tenant_representative")
    private String tenantRepresentative;

    /**
     * 法人证件号
     */
    @TableField("tenant_number")
    private String tenantNumber;

    /**
     * 法人身份证正面
     */
    @TableField("card_font_path")
    private String cardFontPath;

    /**
     * 法人身份证反面
     */
    @TableField("card_back_path")
    private String cardBackPath;

    /**
     * 企业经营范围
     */
    @TableField("tenant_business_scope")
    private String tenantBusinessScope;

    /**
     * 企业类型
     */
    @TableField("tenant_type")
    private String tenantType;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;

    /**
     * 联系方式
     */
    @TableField("phone_number")
    private String phoneNumber;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 企业简介
     */
    @TableField("profile")
    private String profile;

    /**
     * 官网地址
     */
    @TableField("website")
    private String website;

    /**
     * 二级节点登陆名
     */
    @TableField("idis_uname")
    private String idisUName;

    /**
     * 二级节点登陆密码
     */
    @TableField("idis_pwd")
    private String idisPwd;

    /**
     * 企业前缀
     */
    @TableField("idis_prefix")
    private String idisPrefix;

    /**
     * 剩余码数
     */
    @TableField("remain_count")
    private Integer remainCount;

    /**
     * 拥有中天币
     */
    @TableField("ztt_coin")
    private Integer zttCoin;

    /**
     * 所属代理商ID
     */
    @TableField("agent_id")
    private Long agentId;

    /**
     * 已购菜单ID
     */
    @TableField("menu_id")
    private String menuId;

    /**
     * 工商营业执照
     */
    @TableField("business_license")
    private String businessLicense;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 是否审核
     */
    @TableField("is_checked")
    private String isChecked;

    /**
     * 反馈内容
     */
    @TableField("check_reason")
    private String checkReason;

    /**
     * 是否删除
     */
    @TableField("is_delete")
    private String isDelete;

    @TableField("electronic_certificate")
    private String electronicCertificate;

    /**
     * 省编码
     */
    @TableField("province_code")
    private String provinceCode;

    /**
     * 市编码
     */
    @TableField("city_code")
    private String cityCode;

    /**
     * 地区编码
     */
    @TableField("district_code")
    private String districtCode;

    /**
     * 行业大类编码
     */
    @TableField("industry_code")
    private String industryCode;

    /**
     * 行业小类编码
     */
    @TableField("trade_code")
    private String tradeCode;

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenantCreditCode() {
        return tenantCreditCode;
    }

    public void setTenantCreditCode(String tenantCreditCode) {
        this.tenantCreditCode = tenantCreditCode;
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

    public String getTenantAddress() {
        return tenantAddress;
    }

    public void setTenantAddress(String tenantAddress) {
        this.tenantAddress = tenantAddress;
    }

    public String getTenantRepresentative() {
        return tenantRepresentative;
    }

    public void setTenantRepresentative(String tenantRepresentative) {
        this.tenantRepresentative = tenantRepresentative;
    }

    public String getTenantNumber() {
        return tenantNumber;
    }

    public void setTenantNumber(String tenantNumber) {
        this.tenantNumber = tenantNumber;
    }

    public String getTenantBusinessScope() {
        return tenantBusinessScope;
    }

    public void setTenantBusinessScope(String tenantBusinessScope) {
        this.tenantBusinessScope = tenantBusinessScope;
    }

    public String getTenantType() {
        return tenantType;
    }

    public void setTenantType(String tenantType) {
        this.tenantType = tenantType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdisPrefix() {
        return idisPrefix;
    }

    public void setIdisPrefix(String idisPrefix) {
        this.idisPrefix = idisPrefix;
    }

    public Integer getRemainCount() {
        return remainCount;
    }

    public void setRemainCount(Integer remainCount) {
        this.remainCount = remainCount;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }


    public String getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(String isChecked) {
        this.isChecked = isChecked;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCardFontPath() {
        return cardFontPath;
    }

    public void setCardFontPath(String cardFontPath) {
        this.cardFontPath = cardFontPath;
    }

    public String getCardBackPath() {
        return cardBackPath;
    }

    public void setCardBackPath(String cardBackPath) {
        this.cardBackPath = cardBackPath;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getIdisUName() {
        return idisUName;
    }

    public void setIdisUName(String idisUName) {
        this.idisUName = idisUName;
    }

    public String getIdisPwd() {
        return idisPwd;
    }

    public void setIdisPwd(String idisPwd) {
        this.idisPwd = idisPwd;
    }

    public Integer getZttCoin() {
        return zttCoin;
    }

    public void setZttCoin(Integer zttCoin) {
        this.zttCoin = zttCoin;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getCheckReason() {
        return checkReason;
    }

    public void setCheckReason(String checkReason) {
        this.checkReason = checkReason;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getElectronicCertificate() {
        return electronicCertificate;
    }

    public void setElectronicCertificate(String electronicCertificate) {
        this.electronicCertificate = electronicCertificate;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "id=" + id +
                ", tenantCreditCode=" + tenantCreditCode +
                ", tenantName=" + tenantName +
                ", referralCode=" + referralCode +
                ", province=" + province +
                ", city=" + city +
                ", district=" + district +
                ", tenantAddress=" + tenantAddress +
                ", tenantRepresentative=" + tenantRepresentative +
                ", tenantNumber=" + tenantNumber +
                ", cardFontPath=" + cardFontPath +
                ", cardBackPath=" + cardBackPath +
                ", tenantBusinessScope=" + tenantBusinessScope +
                ", tenantType=" + tenantType +
                ", contact=" + contact +
                ", phoneNumber=" + phoneNumber +
                ", email=" + email +
                ", profile=" + profile +
                ", website=" + website +
                ", idisUname=" + idisUName +
                ", idisPwd=" + idisPwd +
                ", idisPrefix=" + idisPrefix +
                ", remainCount=" + remainCount +
                ", zttCoin=" + zttCoin +
                ", agentId=" + agentId +
                ", menuId=" + menuId +
                ", businessLicense=" + businessLicense +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
                ", updateUser=" + updateUser +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                ", checkReason=" + checkReason +
                "}";
    }
}
