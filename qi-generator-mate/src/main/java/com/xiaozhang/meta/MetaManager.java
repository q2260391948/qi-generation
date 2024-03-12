package com.xiaozhang.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

import javax.sound.midi.MetaMessage;

/**
 * @author:22603
 * @Date:2024/1/30 21:49
 */
public class MetaManager {

    private static volatile Meta meta;


    public static Meta getMeta() {
        if (meta == null) {
            synchronized(MetaMessage.class){
                if (meta == null) {
                    meta = init();
                }
                return meta;
            }
        }
        return meta;
    }


    private static Meta init() {
        String json = ResourceUtil.readUtf8Str("meta.json");
        Meta bean = JSONUtil.toBean(json, Meta.class);
        //todo 检测是否合法
        return bean;
    }

}
