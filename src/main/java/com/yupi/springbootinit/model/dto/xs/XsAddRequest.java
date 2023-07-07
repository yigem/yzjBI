package com.yupi.springbootinit.model.dto.xs;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建请求
 */
@Data
public class XsAddRequest implements Serializable {

    /**
     * 分析目标
     */
    private String goal;

    private static final long serialVersionUID = 1L;
}