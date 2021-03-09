package com.yaowk.common.generator;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.yaowk.common.util.DateSourceKit;

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

    public AbstractGenerator(List tableNames) {
        this.tableNames = tableNames;
        this.modelPackageName = this.getClass().getPackage().getName();
        this.modelOutputDir = PathKit.getWebRootPath() + "/src/main/java/" + modelPackageName.replaceAll("[.]", "/");
        this.baseModelPackageName = modelPackageName + ".base";
        this.baseModelOutputDir = modelOutputDir + "/base";
    }

    public void generator() {
        // 创建生成器
        DataSource dataSource = DateSourceKit.getDruidDataSource();
        com.jfinal.plugin.activerecord.generator.Generator gernerator = new com.jfinal.plugin.activerecord.generator.Generator(dataSource, baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
        gernerator.setDialect(new MysqlDialect());
        gernerator.setMetaBuilder(new BaseMetaBuilder(dataSource, tableNames));
        gernerator.setGenerateChainSetter(true);
        // 设置是否在 Model 中生成 dao 对象
        gernerator.setGenerateDaoInModel(true);
        // 设置是否生成字典文件
        gernerator.setGenerateDataDictionary(false);
        // 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
//		gernerator.setRemovedTableNamePrefixes("t_","sys_");
        // 生成
        gernerator.generate();
    }
}
