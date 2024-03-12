package com.xiaozhang.generator;

import java.io.*;
import java.util.Map;

/**
 * @author:22603
 * @Date:2024/2/5 21:38
 */
public class JarGenerator {
    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 清理之前的构建并打包
        // 注意不同操作系统，执行的命令不同
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;
        // 这里一定要拆分！
        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Map<String, String> environment = processBuilder.environment();
        System.out.println(environment);
        Process process = processBuilder.start();

        // 读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // 等待命令执行完成
        int exitCode = process.waitFor();
        System.out.println("退出码：" + exitCode);
    }
    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("E:\\\\学习笔记\\\\项目\\\\代码生成器\\\\qi-generation\\\\qi-generation\\\\qi-generator-mate\\\\generated");
    }

}
