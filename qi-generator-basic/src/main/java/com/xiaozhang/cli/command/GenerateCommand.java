package com.xiaozhang.cli.command;

import cn.hutool.core.bean.BeanUtil;
import com.xiaozhang.GenerateMain;
import com.xiaozhang.model.Config;
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
        Config config=new Config();
        BeanUtil.copyProperties(this, config);
        System.out.println(config);
        GenerateMain main=new GenerateMain();
        main.doGenerate(config);
        return 0;
    }
}
