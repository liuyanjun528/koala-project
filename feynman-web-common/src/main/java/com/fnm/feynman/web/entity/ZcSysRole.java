package com.fnm.feynman.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author Sir_小三
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ZcSysRole implements Serializable, GrantedAuthority {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "role_id", type = IdType.AUTO)
    private Long roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码
     */
    private Integer roleCode;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人id
     */
    private Long createUser;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 更新人id
     */
    private Long updateUser;

    /**
     * 是否删除  1-不删除  2-删除
     */
    private Integer isDelete;


    @Override
    public String getAuthority() {
        return this.roleName;
    }
}
