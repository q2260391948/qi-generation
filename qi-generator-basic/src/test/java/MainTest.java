import com.xiaozhang.model.Config;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:22603
 * @Date:2024/1/16 23:47
 */
public class MainTest {

    @Test
    public void test1() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("myweb.html.ftl");

        Map<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", "testyear");
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItem1 = new HashMap<>();
        menuItem1.put("url", "https://www.baidu.com");
        menuItem1.put("label", "百度");
        menuItems.add(menuItem1);
        dataModel.put("menuItems", menuItems);
        dataModel.put("user", "小张");
//        dataModel.put("userName", "小张");
        // 生成数据和模板的合并结果
        Writer out = new FileWriter("myweb.html");
        template.process(dataModel, out);

        // 生成文件后别忘了关闭
        out.close();
    }

    @Test
    public void test2() throws IOException, TemplateException {
        // new 出 Configuration 对象，参数为 FreeMarker 版本号
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);

        // 指定模板文件所在的路径
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));

        // 设置模板文件使用的字符集
        configuration.setDefaultEncoding("utf-8");

        // 创建模板对象，加载指定模板
        Template template = configuration.getTemplate("MainTemplate.java.ftl");

        Config config=new Config();
        config.setAuthor("小张");
        config.setOutputText("输出啦");
        config.setLoop(false);

        // 生成数据和模板的合并结果
        Writer out = new FileWriter("MainTemplate.java");
        template.process(config, out);

        // 生成文件后别忘了关闭
        out.close();

    }


}
