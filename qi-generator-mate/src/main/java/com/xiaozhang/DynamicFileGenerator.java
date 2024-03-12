package com.xiaozhang;

import cn.hutool.core.io.FileUtil;
import com.xiaozhang.cli.CommandExecutor;
import com.xiaozhang.model.DateMeta;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

import static com.xiaozhang.main.MainGenerator.extracted;

/**
 * @author:22603
 * @Date:2024/1/16 17:29
 */
public class DynamicFileGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }

    public void doGenerate(DateMeta dateMeta) throws IOException, TemplateException {
        //获取当前目录
        String property = System.getProperty("user.dir");
        //获取当前目录的父目录
        File parentFile = new File(property).getParentFile();
        //获取源文件路径 File.separator为系统分隔符
        String sourcePath = parentFile + File.separator + "demo" + File.separator + "acm-template";
        //复制文件
        copyFile(sourcePath, parentFile.getPath());
        //获取目标文件路径
        String targetPath = parentFile + File.separator + "acm-template" + File.separator + "src" + File.separator + "com" + File.separator + "xiaozhang" + File.separator + "acm";
        String inputFilePath = property + "/src/main/resources/templates";
        String outputFilePath = targetPath;
        extracted(inputFilePath, "MainTemplate.java.ftl", outputFilePath, "MainTemplate.java", dateMeta);
    }



    public static void copyFile(String sourcePath, String targetPath) {
        FileUtil.copy(sourcePath, targetPath, false);
    }

}