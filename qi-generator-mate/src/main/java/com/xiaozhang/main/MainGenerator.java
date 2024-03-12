package com.xiaozhang.main;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.xiaozhang.generator.JarGenerator;
import com.xiaozhang.generator.ScriptGenerator;
import com.xiaozhang.meta.MetaManager;
import com.xiaozhang.meta.Meta;
import com.xiaozhang.meta.MetaManager;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author:22603
 * @Date:2024/1/31 0:00
 */
public class MainGenerator {
    public static void main(String[] args) throws IOException, TemplateException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        String property = System.getProperty("user.dir");
        File parentFile = new File(property).getParentFile();
        String outPath = parentFile + File.separator + "generated";
        if (!FileUtil.exist(outPath)) {
            FileUtil.mkdir(outPath);
        }
        System.out.println(property);
        // 读取 resources 目录
        String path = property + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;


        String basePackage = meta.getBasePackage();
        String outPutPackage = StrUtil.join("/", StrUtil.split(basePackage, "."));

        String outPutPath = outPath + File.separator + "src/main/java/" + outPutPackage;
        String inputFilePath;
        String outputFilePath;
        inputFilePath = path + "templates\\java\\model";
        outputFilePath = outPutPath + File.separator + "model" + File.separator;
        extracted(inputFilePath, "DateMeta.java.ftl", outputFilePath, "DateMeta.java", meta);
        inputFilePath = path + "templates\\java\\cli\\command";
        outputFilePath = outPutPath + File.separator + "cli" + File.separator+"command" + File.separator;
        extracted(inputFilePath, "ConfigCommand.java.ftl", outputFilePath, "ConfigCommand.java", meta);
        extracted(inputFilePath, "GenerateCommand.java.ftl", outputFilePath, "GenerateCommand.java", meta);
        extracted(inputFilePath, "ListCommand.java.ftl", outputFilePath, "ListCommand.java", meta);
        inputFilePath = path + "templates\\java\\cli";
        outputFilePath = outPutPath + File.separator + "cli" + File.separator;
        extracted(inputFilePath, "CommandExecutor.java.ftl", outputFilePath, "CommandExecutor.java", meta);
        inputFilePath = path + "templates\\java\\cli\\command\\example";
        outputFilePath = outPutPath + File.separator + "cli" + File.separator+"command" + File.separator+"example" + File.separator;
        extracted(inputFilePath, "CommandLineUtils.java.ftl", outputFilePath, "CommandLineUtils.java", meta);
        inputFilePath = path + "templates\\java";
        outputFilePath = outPutPath + File.separator ;
        extracted(inputFilePath, "MainGenerator.java.ftl", outputFilePath, "MainGenerator.java", meta);

        inputFilePath = path + "templates";
        outputFilePath =  outPath + File.separator;
        extracted(inputFilePath, "pom.xml.ftl", outputFilePath, "pom.xml", meta);
        // 构建 jar 包
        System.out.println(outPath);
        JarGenerator.doGenerate(outPath);
        // 封装脚本
        String shellOutputFilePath = outPath + File.separator + meta.getName();;
        String jarName = String.format("%s-%s-jar-with-dependencies.jar", meta.getName(), meta.getVersion());
        String jarPath = "target/" + jarName;
        ScriptGenerator.doGenerate(shellOutputFilePath, jarPath);
    }

    public static void extracted(String inputFilePath, String modelName, String outputFilePath, String fileName, Object data) throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File(inputFilePath));
        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");
        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate(modelName);
        // 生成数据和模板的合并结果
        if (!FileUtil.exist(outputFilePath)) {
            FileUtil.mkdir(outputFilePath);
        }
        Writer out = new FileWriter(outputFilePath + fileName);
        template.process(data, out);
        out.close();
    }
}
