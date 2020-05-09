/**
 * FileName: CodeGenerator
 * Author:   10418
 * Date:     2019-08-01 20:00
 * Description: MyBatis-Plus代码生成器
 * History:
 * <author>          <time>          <version>          <desc>
 * 牛楠楠
 */
package cn.nicenan.meeting.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 〈一句话功能简述〉<br>
 * 〈MyBatis-Plus代码生成器〉
 *
 * @author 10418
 * @create 2019-08-01
 * @since 1.0.0
 */
public class CodeGenerator {
    private static String projectPath = "D:\\Dev\\github\\meeting\\MeetingServer";
    private static String packetParent = "cn.nicenan.meeting";
    private static String author = "Nannan";

    private static String DriverName = "com.mysql.cj.jdbc.Driver";
    private static String Url = "jdbc:mysql://192.168.2.110:3306/Meeting?serverTimezone=Asia/Shanghai";
    private static String Username = "root";
    private static String Password = "123456";

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入" + tip + "：");
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {

        //包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(packetParent)
                .setMapper("mapper")
                .setService("service")
                //.setController("controller")
                .setEntity("model")
                .setXml("mapper");


        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        //全局配置
        GlobalConfig config = new GlobalConfig();
        //是否支持AR模式
        config.setActiveRecord(false)
                //作者
                .setAuthor(author)
                //生成路径
                .setOutputDir(projectPath + "/src/main/java")
                //文件覆盖
                .setFileOverride(true)
                //主键策略
                .setIdType(IdType.AUTO)
                //设置生成的service接口名称
                .setServiceName("%sService")
                //生成基本mapper
                .setBaseResultMap(true)
                //生成基本列
                .setBaseColumnList(true);

        //数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
        // 设置数据库类型
        dsConfig.setDbType(DbType.MYSQL)
                .setDriverName(DriverName)
                .setUrl(Url)
                .setUsername(Username)
                .setPassword(Password);

        //策略配置
        StrategyConfig stConfig = new StrategyConfig();
        //全局大写命名
        stConfig
                //数据库表映射到实体的命名策略
                .setNaming(NamingStrategy.no_change)
                // 数据库表字段映射到实体的命名策略
                .setColumnNaming(NamingStrategy.no_change)
                //生成 <code>@RestController</code> 控制器
                .setRestControllerStyle(true)
                .setInclude(scanner("表名，多个英文逗号分割").split(","));
        //整合配置
        AutoGenerator ag = new AutoGenerator();

        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setCfg(cfg)
                .setTemplate(templateConfig);

        //执行
        ag.execute();
    }
}
