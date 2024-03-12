package com.xiaozhang.model;

import lombok.Data;

/**
* @author:xiaozhang
* @Date:2024/1/17 0:08
*  @Description:ACM 示例模板生成器
*/
@Data
public class DateMeta {
    /**
    * 是否生成循环
    */
        private boolean loop;

    /**
    * 作者注释
    */
        private String author;

    /**
    * 输出信息
    */
        private String outputText;

}
