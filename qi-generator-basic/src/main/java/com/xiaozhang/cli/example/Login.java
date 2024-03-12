package com.xiaozhang.cli.example;

import lombok.Data;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.util.concurrent.Callable;
@Data
public class Login implements Callable<Integer> {
    @Option(names = {"-u", "--user"}, description = "User name",interactive = true)
    String user;

    /**
     *  interactive属性为true时，表示该选项需要用户输入
     */
    @Option(names = {"-p", "--password"}, description = "Passphrase", interactive = true,arity = "0..1")
    String password;

    @Option(names = {"-cp", "--checkPassword"}, description = "check password", interactive = true)
    String checkPassword;

    public Integer call() throws Exception {
        System.out.println("password = " + password);
        System.out.println("checkPassword = " + checkPassword);
        if (user == null && System.console() != null) {
            // alternatively, use Console::readPassword
            user = System.console().readLine("Enter value for --interactive: ");
        }
        System.out.println("You provided value '" + user + "'");
        return 0;
    }

//    public static void main(String[] args) {
////        new CommandLine(new Login()).execute("-u", "user123", "-p");
////        new CommandLine(new Login()).execute("-u", "user123", "-p", "123", "-cp");
//        new CommandLine(new Login()).execute("-u");
//    }
}

