import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyBatisGenarator {

    // 全局配置
    private final static String OUTPUT_DIR = "/project-wx-minishop/src/main/java";// 生成文件的输出目录
    private final static String AUTHOR = "gaosc";// 开发人员
    // 数据源配置
    private final static String DATABASE_IP = "develop132.mysql.rds.aliyuncs.com";// 数据库id
    private final static String DATABASE_NAME = "ecosystem_client";// 数据库名称
    // 包配置
    private final static String PARENT = "com";// 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
    private final static String MODULE_NAME = "shop";// 父包模块名
//    // 自定义基类
//    private final static String SuperEntity = "com.baomidou.mybatisplus.samples.generator.common.BaseEntity";// 所有实体的基类(全类名)
//    private final static String SuperController = "com.baomidou.mybatisplus.samples.generator.common.BaseController";// 所有控制器的基类(全类名)

    /**
     * <p>读取控制台内容</p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    /**
     * RUN THIS
     */
    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + OUTPUT_DIR);
        gc.setAuthor(AUTHOR);
        gc.setOpen(false);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+DATABASE_IP+":3306/"+DATABASE_NAME+"?useUnicode=true&useSSL=false&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        // dsc.setDriverName("com.mysql.jdbc.Driver");// JDK7
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");// JDK8
        dsc.setUsername("user_ecosys_api");
        dsc.setPassword("UduaC#$3u4");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setModuleName(MODULE_NAME);
        pc.setParent(PARENT);
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(com.baomidou.mybatisplus.generator.config.po.TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + OUTPUT_DIR + "/" + PARENT.replaceAll("\\.", "/") + "/" + MODULE_NAME + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass(SuperEntity);
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型
//        strategy.setSuperControllerClass(SuperController);
//        strategy.setInclude(scanner("t_wx_user_info"));
        strategy.setInclude("lg_product_info", "lg_product_category_info", "lg_product_car", "lg_product_collect");
//        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
