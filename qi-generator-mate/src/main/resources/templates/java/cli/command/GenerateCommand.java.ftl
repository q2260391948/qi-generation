package ${basePackage}.cli.command;

import cn.hutool.core.bean.BeanUtil;
import ${basePackage}.MainGenerator;
import ${basePackage}.cli.command.example.CommandLineUtils;
import ${basePackage}.model.DateMeta;
import freemarker.template.TemplateException;
import lombok.Data;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.util.concurrent.Callable;

;

/**
* @author:${author}
* @Date:${createTime}
*/
@Data
@Command(name = "generate", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class GenerateCommand implements Callable<Integer> {

    <#list modelConfig.models as metaInfo>
    @Option(names = {"-${metaInfo.abbr}", "--${metaInfo.fieldName}"}, description = "${metaInfo.description}", interactive = true,arity = "0..1")
    ${metaInfo.type} ${metaInfo.fieldName};
    </#list>

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
