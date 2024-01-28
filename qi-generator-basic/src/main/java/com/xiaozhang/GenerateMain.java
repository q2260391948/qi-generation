package com.xiaozhang;

import cn.hutool.core.io.FileUtil;
import com.xiaozhang.cli.CommandExecutor;
import com.xiaozhang.model.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author:22603
 * @Date:2024/1/16 17:29
 */
public class GenerateMain {
    public static void main(String[] args) throws IOException, TemplateException {
//        args = new String[]{"generate", "-l", "-a", "-o"};
//        args = new String[]{"config"};
//        args = new String[]{"list"};
        CommandExecutor commandExecutor = new CommandExecutor();
        commandExecutor.doExecute(args);
    }

    public void doGenerate(Config config) throws IOException, TemplateException {
        //获取当前目录
        String property = System.getProperty("user.dir");
        //获取当前目录的父目录
        File parentFile = new File(property).getParentFile();
        System.out.println(parentFile);
        //获取源文件路径 File.separator为系统分隔符
        String sourcePath = parentFile + File.separator + "demo" + File.separator + "acm-template";
        //复制文件
        copyFile(sourcePath, parentFile.getPath());
        //获取目标文件路径
        String targetPath = parentFile + File.separator + "acm-template" + File.separator + "src" + File.separator + "com" + File.separator + "xiaozhang" + File.separator + "acm";
        //System.out.println(targetPath);
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(property + "/src/main/resources/templates"));
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");
        // 生成数据和模板的合并结果
        Writer out = new FileWriter(targetPath + "/MainTemplate.java");
        template.process(config, out);
        // 生成文件后别忘了关闭
        out.close();
    }


    public static void copyFile(String sourcePath, String targetPath) {
        FileUtil.copy(sourcePath, targetPath, false);
    }

}