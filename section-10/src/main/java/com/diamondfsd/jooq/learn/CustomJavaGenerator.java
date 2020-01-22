package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.extend.AbstractExtendDAOImpl;
import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.impl.DAOImpl;
import org.jooq.meta.TableDefinition;
import org.jooq.tools.JooqLogger;
import org.jooq.tools.StringUtils;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

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
                fileContent = fileContent.replace(pojoClassName, getPojoExtendClassName(table));
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
        // 在生成完POJO后，生成POJO的继承类
        generatePojoExtend(table);
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
        String className = getPojoExtendClassName(table);
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
        String javaClassName = getPojoExtendClassName(definition);
        return javaClassName + ".java";
    }

    private String getPojoExtendClassName(TableDefinition definition) {
        return StringUtils.toCamelCase(
                definition.getOutputName()
                        .replace(' ', '_')
                        .replace('-', '_')
                        .replace('.', '_')
        );
    }

    private String getPojoExtendFullClassName(TableDefinition definition) {
        return getPojoTargetPackage() + "." + getPojoExtendClassName(definition);
    }
}
