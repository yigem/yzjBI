package com.yupi.springbootinit.model.dto.xs;

import lombok.Data;

import java.io.Serializable;

/**
 * 文件上传请求
 */
@Data
public class GenXsByAiRequest implements Serializable {

    /**
     * 分析目标
     */
    private String goal;

    private static final long serialVersionUID = 1L;
}