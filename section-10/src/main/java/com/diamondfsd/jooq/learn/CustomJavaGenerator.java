package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.extend.AbstractExtendDAOImpl;
import com.diamondfsd.jooq.learn.extend.AbstractGenericService;
import org.jooq.Constants;
import org.jooq.Record;
import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.impl.DAOImpl;
import org.jooq.meta.ColumnDefinition;
import org.jooq.meta.TableDefinition;
import org.jooq.meta.UniqueKeyDefinition;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 自定义 Java 代码生成器
 *
 * @author Diamond
 */
public class CustomJavaGenerator extends JavaGenerator {
    private static final JooqLogger log = JooqLogger.getLogger(CustomJavaGenerator.class);

    /**
     * 重写了 generateDao， 具体的生成逻辑还是调用父级的方法，只是在生成完成后，获取文件内容，
     * 然后对文件指定的内容进行替换操作
     *
     * @param table
     */
    @Override
    protected void generateDao(TableDefinition table) {
        super.generateDao(table);
        File file = getFile(table, GeneratorStrategy.Mode.DAO);
        if (file.exists()) {
            try {
                String fileContent = new String(FileCopyUtils.copyToByteArray(file));
                String oldExtends = " extends " + DAOImpl.class.getSimpleName();
                String newExtends = " extends " + AbstractExtendDAOImpl.class.getSimpleName();
                fileContent = fileContent.replace("import org.jooq.impl.DAOImpl;\n", "");
                fileContent = fileContent.replace(oldExtends, newExtends);

                String pojoFullClass = getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.POJO);
                String pojoClassName = getStrategy().getJavaClassName(table, GeneratorStrategy.Mode.POJO);
                fileContent = fileContent.replace("import " + pojoFullClass + ";\n", "");
                fileContent = fileContent.replace(pojoClassName, getTableSimpleName(table));
                FileCopyUtils.copy(fileContent.getBytes(), file);
            } catch (IOException e) {
                log.error("generateDao error: {}", file.getAbsolutePath(), e);
            }
        }
    }

    @Override
    protected void generateDao(TableDefinition table, JavaWriter out) {
        // 用于生成 import com.diamondfsd.jooq.learn.extend.AbstractDAOExtendImpl 内容
        out.ref(AbstractExtendDAOImpl.class);
        out.ref(getPojoExtendFullClassName(table));
        super.generateDao(table, out);
    }

    @Override
    protected void generatePojo(TableDefinition table) {
        super.generatePojo(table);
        table.getPrimaryKey();
        // 在生成完POJO后，生成POJO的继承类
        generatePojoExtend(table);

        // 生成Service
        generateService(table);
    }

    private void generateService(TableDefinition table) {
        File file = getServiceFile(table);
        if (file.exists()) {
            log.info("Generating Service exists", file.getName());
            return;
        } else {
            log.info("Generating Service", file.getName());
        }
        JavaWriter out = newJavaWriter(file);
        generateService(table, out);
        closeJavaWriter(out);
    }

    private void generateService(TableDefinition table, JavaWriter out) {
        UniqueKeyDefinition key = table.getPrimaryKey();
        if (key == null) {
            log.info("Skipping Service generation", out.file().getName());
            return;
        }

        String daoFullClassName = getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.DAO);
        String daoType = out.ref(daoFullClassName);
        String abstractGenericService = out.ref(AbstractGenericService.class);
        out.ref(Service.class);
        List<ColumnDefinition> keys = key.getKeyColumns();
        String primaryKeyType = getPrimaryKeyType(keys, out);
        String serviceClassName = getServiceClassName(table);
        String pojoType = out.ref(getPojoExtendFullClassName(table));

        out.println("package %s;", getServiceTargetPackage());
        out.println();
        out.printImports();
        out.println();
        out.println("@Service");
        out.println("public class %s extends %s<%s, %s> {", serviceClassName, abstractGenericService, pojoType, primaryKeyType);
        out.println();
        out.tab(1).println("public %s(%s dao) {", serviceClassName, daoType);
        out.tab(2).println("super(dao);");
        out.tab(1).println("}");

        out.print("}");
    }

    private String getPrimaryKeyType(List<ColumnDefinition> keyColumns, JavaWriter out) {
        if (keyColumns.size() == 1) {
            return getJavaType(keyColumns.get(0).getType(resolver()), GeneratorStrategy.Mode.POJO);
        } else if (keyColumns.size() <= Constants.MAX_ROW_DEGREE) {
            String generics = "";
            String separator = "";

            for (ColumnDefinition column : keyColumns) {
                generics += separator + out.ref(getJavaType(column.getType(resolver())));
                separator = ", ";
            }

            return Record.class.getName() + keyColumns.size() + "<" + generics + ">";
        } else {
            return Record.class.getName();
        }
    }

    /**
     * 生成POJO的子类，生成的子类在jOOQ生成配置目标的父级目录，
     * 所以在每次生成的时候不会被删除，而且在代码内判断了如果文件存在，
     * 则不再创建代码，防止我们手动修改的代码被覆盖
     *
     * @param table
     */
    private void generatePojoExtend(TableDefinition table) {
        File file = getPojoExtendFile(table);
        if (file.exists()) {
            log.info("Generating POJO Extend exists", file.getName());
            return;
        } else {
            log.info("Generating POJO Extend", file.getName());
        }
        JavaWriter out = newJavaWriter(file);
        generatePojoExtend(table, out);
        closeJavaWriter(out);
    }

    private void generatePojoExtend(TableDefinition table, JavaWriter out) {
        String pType = out.ref(getStrategy().getFullJavaClassName(table, GeneratorStrategy.Mode.POJO));
        String className = getTableSimpleName(table);
        out.println("package %s;", getPojoTargetPackage());
        out.println();
        out.printImports();
        out.println();
        out.println("public class %s extends %s {", className, pType);
        out.println();
        out.print("}");
    }

    private File getPojoExtendFile(TableDefinition table) {
        String dir = getTargetDirectory();
        String pkg = getPojoTargetPackage().replaceAll("\\.", "/");
        return new File(dir + "/" + pkg, getPojoExtendFileName(table));
    }

    private String getPojoTargetPackage() {
        String targetPackage = getTargetPackage();
        String targetParentPackage = targetPackage.substring(0, targetPackage.lastIndexOf(".")) + ".pojos";
        return targetParentPackage;
    }

    private String getPojoExtendFileName(TableDefinition definition) {
        String javaClassName = getTableSimpleName(definition);
        return javaClassName + ".java";
    }

    /**
     * 将表名转换为驼峰式命名首字母也大写
     *
     * @param definition
     * @return
     */
    private String getTableSimpleName(TableDefinition definition) {
        return StringUtils.toCamelCase(
                definition.getOutputName()
                        .replace(' ', '_')
                        .replace('-', '_')
                        .replace('.', '_')
        );
    }

    private String getPojoExtendFullClassName(TableDefinition definition) {
        return getPojoTargetPackage() + "." + getTableSimpleName(definition);
    }

    private File getServiceFile(TableDefinition table) {
        String dir = getTargetDirectory();
        String pkg = getServiceTargetPackage().replaceAll("\\.", "/");
        return new File(dir + "/" + pkg, getServiceFileName(table));
    }

    String getServiceClassName(TableDefinition definition) {
        return getTableSimpleName(definition) + "Service";
    }

    String getServiceTargetPackage() {
        return getTargetPackage().substring(0, getTargetPackage().lastIndexOf(".")) + ".service";
    }

    String getServiceFileName(TableDefinition definition) {
        return getServiceClassName(definition) + ".java";
    }


}
