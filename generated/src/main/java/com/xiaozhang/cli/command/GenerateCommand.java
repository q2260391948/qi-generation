package com.xiaozhang.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.xiaozhang.MainGenerator;
import com.xiaozhang.cli.command.example.CommandLineUtils;
import com.xiaozhang.model.DateMeta;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

;

/**
* @author:xiaozhang
* @Date:2024/1/30
*/
@Data
@Command(name = "generate", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class GenerateCommand implements Callable<Integer> {

    @Option(names = {"-l", "--loop"}, description = "是否生成循环", interactive = true,arity = "0..1")
    boolean loop;
    @Option(names = {"-a", "--author"}, description = "作者注释", interactive = true,arity = "0..1")
    String author;
    @Option(names = {"-o", "--outputText"}, description = "输出信息", interactive = true,arity = "0..1")
    String outputText;

    @Override
    public Integer call() throws TemplateException, IOException {
        DateMeta dateMate =new DateMeta();
        BeanUtil.copyProperties(this, dateMate);
        System.out.println(dateMate);
        CommandLineUtils.promptForMissingValues(dateMate);
        MainGenerator main=new MainGenerator();
        main.doGenerate(dateMate);
        return 0;
    }
}
