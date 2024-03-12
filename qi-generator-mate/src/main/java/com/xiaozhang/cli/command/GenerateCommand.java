package com.xiaozhang.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.xiaozhang.DynamicFileGenerator;
import com.xiaozhang.cli.example.CommandLineUtils;
import com.xiaozhang.model.DateMeta;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

;

/**
 * @author:22603
 * @Date:2024/1/22 0:17
 */
@Data
@Command(name = "generate", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class GenerateCommand implements Callable<Integer> {


    @Option(names = {"-l", "--loop"}, description = "是否循环", interactive = true,arity = "0..1")
    String loop;

    @Option(names = {"-a", "--author"}, description = "作者", interactive = true)
    String author;

    @Option(names = {"-o", "--outputText"}, description = "输出内容", interactive = true)
    String outputText;




    @Override
    public Integer call() throws TemplateException, IOException {
        DateMeta DateMeta =new DateMeta();
        BeanUtil.copyProperties(this, DateMeta);
        System.out.println(DateMeta);
        CommandLineUtils.promptForMissingValues(DateMeta);
        DynamicFileGenerator main=new DynamicFileGenerator();
        main.doGenerate(DateMeta);
        return 0;
    }
}
