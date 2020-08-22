package com.fnm.feynman.hospital.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 消息表
 * </p>
 *
 * @author Sir_小三
 * @since 2020-08-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ZcNews implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "news_id", type = IdType.AUTO)
    private Long newsId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 消息内容
     */
    private String newsInfo;


}
