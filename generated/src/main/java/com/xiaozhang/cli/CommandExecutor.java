package com.xiaozhang.cli;

import com.xiaozhang.cli.command.ConfigCommand;
import com.xiaozhang.cli.command.GenerateCommand;
import com.xiaozhang.cli.command.ListCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

/**
* @author:xiaozhang
* @Date:2024/1/30
*/
@Command(name = "acm-template-pro-generator", mixinStandardHelpOptions = true, version = "1.0",
        description = "ACM 示例模板生成器")
public class CommandExecutor implements Runnable {

    private final CommandLine commandLine;

    {
        commandLine = new CommandLine(this)
                .addSubcommand(new GenerateCommand())
                .addSubcommand(new ConfigCommand())
                .addSubcommand(new ListCommand());
    }

    @Override
    public void run() {
        System.out.println(" 请输入具体命令，或者输入 --help 查看命令提示");
    }

    /**
     *  执行命令
     * @param args
     * @return
     */
    public Integer doExecute(String[] args) {
        return commandLine.execute(args);
    }
}
