package com.fnm.feynman.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 前端用户表
 * </p>
 *
 * @author Sir_小三
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ZcWebUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "web_id", type = IdType.AUTO)
    private Long webId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 真名
     */
    private String realName;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机
     */
    private String phone;

    /**
     * 生日
     */
    private LocalDateTime birthday;

    /**
     * openid
     */
    private String openId;
    /**
     * password
     */
    private String password;
    /**
     * unionid
     */
    private String unionId;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 创建人
     */
    private Long createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private Long updateUser;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 是否已删除  1不删除 2删除
     */
    private Integer isDeleted;


}
