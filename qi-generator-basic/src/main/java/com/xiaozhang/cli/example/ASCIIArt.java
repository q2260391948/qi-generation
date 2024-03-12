package com.xiaozhang.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

/**
 * Command注解标记该类为其命名 mixinStandardHelpOptions属性给程序添加了--help和--version选项
 */
@Command(name = "ASCIIArt", version = "ASCIIArt 1.0", mixinStandardHelpOptions = true) 
public class ASCIIArt implements Runnable {

    /**
     * Option注解将字段设置为命令选项，可以给选项添加名称和描述
     */
    @Option(names = { "-s", "--font-size" }, description = "Font size",required = true)
    int fontSize = 19;

    /**
     *  Parameters注解将字段设置为命令参数，可以给参数添加名称和描述
     *  paramLabel 表示参数的标签，是命令行参数的可读标识。
     */
    @Parameters(paramLabel = "<word>", defaultValue = "Hello, picocli", 
               description = "Words to be translated into ASCII art.")
    private String[] words = { "Hello,", "picocli" }; 

    @Override
    public void run() {
        // 自己实现业务逻辑
        System.out.println("fontSize = " + fontSize);
        System.out.println("words = " + String.join(",", words));
    }

//    public static void main(String[] args) {
//        int exitCode = new CommandLine(new ASCIIArt()).execute(args);
//        System.exit(exitCode);
//    }
}