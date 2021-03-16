package com.yaowk.common.generator;

import com.jfinal.kit.PathKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.BaseModelGenerator;
import com.jfinal.plugin.activerecord.generator.ModelGenerator;
import com.yaowk.common.util.DataPluginKit;

import javax.sql.DataSource;
import java.util.List;

/**
 * model生成工具抽象类
 *
 * @authc yaowk
 * 2017/6/27
 */
public abstract class AbstractGenerator {

    protected List<String> tableNames;
    protected String baseModelPackageName;
    protected String baseModelOutputDir;
    protected String modelPackageName;
    protected String modelOutputDir;
    protected String tableNamePrefix;

    public AbstractGenerator(List tableNames, String tableNamePrefix) {
        this.tableNames = tableNames;
        this.modelPackageName = this.getClass().getPackage().getName().replace("common", "model");
        this.modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPackageName.replaceAll("[.]", "/");
        this.baseModelPackageName = modelPackageName + ".base";
        this.baseModelOutputDir = modelOutputDir + "/base";
        this.tableNamePrefix = tableNamePrefix;
    }

    public AbstractGenerator(List tableNames) {
        this(tableNames, null);
    }

    public void generator() {
        // 创建生成器
        DataSource dataSource = DataPluginKit.getDruidDataSource();
        ModelGenerator modelGenerator = new MyModelGenerator(modelPackageName, baseModelPackageName, modelOutputDir, tableNamePrefix);
        BaseModelGenerator baseModelGenerator = new BaseModelGenerator(baseModelPackageName, baseModelOutputDir);
        com.jfinal.plugin.activerecord.generator.Generator generator = new com.jfinal.plugin.activerecord.generator.Generator(dataSource, baseModelGenerator, modelGenerator);
        generator.setDialect(new MysqlDialect());
        generator.setMetaBuilder(new BaseMetaBuilder(dataSource, tableNames));
        generator.setGenerateChainSetter(true);
        // 设置是否在 Model 中生成 dao 对象
        generator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        generator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
        if (StrKit.notBlank(tableNamePrefix)) {
            generator.setRemovedTableNamePrefixes(tableNamePrefix);
        }

        // 生成
        generator.generate();
    }
}
