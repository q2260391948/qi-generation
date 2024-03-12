package ${basePackage}.cli.command;

import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.DateMeta;
import lombok.Data;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

/**
 * @author:${author}
 * @Date:${createTime}
 */
@Data
@Command(name = "config", mixinStandardHelpOptions = true, version = "1.0",
        description = "Generate code from template")
public class ConfigCommand implements Runnable{

    @Override
    public void run() {
        //获取所有命令
        Field[] fields = ReflectUtil.getFields(DateMeta.class);
        for (Field field : fields) {
            System.out.println("字段名称:"+field.getName());
            System.out.println("字段类型:"+field.getType());
        }
    }
}
