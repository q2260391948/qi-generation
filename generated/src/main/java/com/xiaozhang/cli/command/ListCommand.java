package com.xiaozhang.cli.command;

import cn.hutool.core.io.FileUtil;
import lombok.Data;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

/**
* @author:xiaozhang
* @Date:2024/1/30
*/
@Data
@Command(name = "list", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class ListCommand implements Runnable {


    @Override
    public void run() {
        //获取文件路径
        String inputPath ="E:/学习笔记/项目/代码生成器/qi-generation/qi-generation/demo/acm-template-pro";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
