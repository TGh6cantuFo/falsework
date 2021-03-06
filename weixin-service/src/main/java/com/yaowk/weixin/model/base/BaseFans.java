package com.yaowk.weixin.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseFans<M extends BaseFans<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Integer id) {
		set("id", id);
		return (M)this;
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public M setOpenid(java.lang.String openid) {
		set("openid", openid);
		return (M)this;
	}

	public java.lang.String getOpenid() {
		return get("openid");
	}

	public M setNickname(java.lang.String nickname) {
		set("nickname", nickname);
		return (M)this;
	}

	public java.lang.String getNickname() {
		return get("nickname");
	}

	public M setSex(java.lang.String sex) {
		set("sex", sex);
		return (M)this;
	}

	public java.lang.String getSex() {
		return get("sex");
	}

	public M setProvince(java.lang.String province) {
		set("province", province);
		return (M)this;
	}

	public java.lang.String getProvince() {
		return get("province");
	}

	public M setCity(java.lang.String city) {
		set("city", city);
		return (M)this;
	}

	public java.lang.String getCity() {
		return get("city");
	}

	public M setCountry(java.lang.String country) {
		set("country", country);
		return (M)this;
	}

	public java.lang.String getCountry() {
		return get("country");
	}

	public M setHeadimgurl(java.lang.String headimgurl) {
		set("headimgurl", headimgurl);
		return (M)this;
	}

	public java.lang.String getHeadimgurl() {
		return get("headimgurl");
	}

	public M setPrivilege(java.lang.String privilege) {
		set("privilege", privilege);
		return (M)this;
	}

	public java.lang.String getPrivilege() {
		return get("privilege");
	}

	public M setUnionid(java.lang.String unionid) {
		set("unionid", unionid);
		return (M)this;
	}

	public java.lang.String getUnionid() {
		return get("unionid");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}

	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setStatus(java.lang.Integer status) {
		set("status", status);
		return (M)this;
	}

	public java.lang.Integer getStatus() {
		return get("status");
	}

	public M setPlatformId(java.lang.Integer platformId) {
		set("platform_id", platformId);
		return (M)this;
	}

	public java.lang.Integer getPlatformId() {
		return get("platform_id");
	}

	public M setUserId(java.lang.Integer userId) {
		set("user_id", userId);
		return (M)this;
	}

	public java.lang.Integer getUserId() {
		return get("user_id");
	}

}
