package com.yupi.springbootinit.model.dto.xs;

import lombok.Data;

import java.io.Serializable;

/**
 * 编辑请求
 */
@Data
public class XsEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 分析目标
     */
    private String goal;

    private static final long serialVersionUID = 1L;
}