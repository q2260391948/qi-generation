package com.xiaozhang.cli.command;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
 * @author:22603
 * @Date:2024/1/22 0:17
 */
@Data
@Command(name = "list", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class ListCommand implements Runnable {


    @Override
    public void run() {
        //输出文件及下所有文件名称
        String property = System.getProperty("user.dir");
        //获取当前项目路径
        File parentFile = new File(property).getParentFile();
        //获取父级目录
        String inputPath = new File(parentFile, "demo/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
