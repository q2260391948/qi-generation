package com.xiaozhang.cli.example;

import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.lang.reflect.Field;
import java.util.Scanner;

/**
 * 命令行工具类
 */
public class CommandLineUtils {

    /**
     * 交互式命令行检测，如果某个选项需要用户输入，但是用户没有输入，则提示用户输入
     * @param command
     */
    public static void promptForMissingValues(Object command) {
        //通过反射获取传入对象的类
        Class<?> clazz = command.getClass();
        //获取这个类所有的字段
        Field[] fields = clazz.getDeclaredFields();
        //输入
        Scanner scanner = new Scanner(System.in);
        //遍历字段，找到需要交互的字段
        for (Field field : fields) {
            Option optionAnnotation = field.getAnnotation(Option.class);
            //判断这个字段是否使用了Option注解，并且interactive属性为true，以及是否存在至少一个选项名称
            if (optionAnnotation != null && optionAnnotation.interactive() && optionAnnotation.names().length > 0) {
                //获取选项名称
                String optionName = optionAnnotation.names()[0];
                //设置访问权限
                field.setAccessible(true);
                try {
                    // 获取字段的值
                    Object value = field.get(command);
                    if (value == null) {
                        System.out.print("请输入" + optionName + "参数对应的值: ");
                        String userInput = scanner.nextLine();
                        field.set(command, userInput);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//    public static void main(String[] args) {
//        Login login = new Login();
//        login.setPassword("123");
//        CommandLineUtils.promptForMissingValues(login);
//        new CommandLine(login).execute(args);
//    }
}
