package com.xiaozhang;

import cn.hutool.core.io.FileUtil;
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
public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        //获取当前目录
        String property = System.getProperty("user.dir");
        //获取源文件路径 File.separator为系统分隔符
        String sourcePath = property + File.separator + "demo" + File.separator + "acm-template";
        System.out.println(sourcePath);
        //复制文件
        copyFile(sourcePath, property);
        //获取目标文件路径
        String targetPath = property + File.separator + "acm-template" + File.separator + "src" + File.separator + "com" + File.separator + "xiaozhang" + File.separator + "acm";
        System.out.println(targetPath);
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(property+"/qi-generator-basic/src/main/resources/templates"));
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");

        Config config=new Config();
        config.setAuthor("小张");
        config.setOutputText("输出啦");
        config.setLoop(false);

        // 生成数据和模板的合并结果
        Writer out = new FileWriter(targetPath+"/MainTemplate.java");
        template.process(config, out);

        // 生成文件后别忘了关闭
        out.close();

    }

    public static void copyFile(String sourcePath, String targetPath) {
        FileUtil.copy(sourcePath, targetPath, false);
    }

}