package com.diamondfsd.jooq.learn;

import com.diamondfsd.jooq.learn.extend.AbstractDAOExtendImpl;
import org.jooq.codegen.GeneratorStrategy;
import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.TableDefinition;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * 自定义 Java 代码生成器
 * @author Diamond
 */
public class CustomJavaGenerator extends JavaGenerator {

    @Override
    protected void generateDao(TableDefinition table) {
        super.generateDao(table);
        File file = getFile(table, GeneratorStrategy.Mode.DAO);
        try {
            String fileContent = new String(FileCopyUtils.copyToByteArray(file));
            fileContent = fileContent.replace("import org.jooq.impl.DAOImpl;\n", "");
            String changeExtend = " extends DAOImpl";
            String changeAbstract = " extends AbstractDAOExtendImpl";
            fileContent = fileContent.replace(changeExtend, changeAbstract);
            FileCopyUtils.copy(fileContent.getBytes(), file);
        } catch (IOException ignore) {
        }

    }

    @Override
    protected void generateDao(TableDefinition table, JavaWriter out) {
        out.ref(AbstractDAOExtendImpl.class);
        super.generateDao(table, out);

    }
}
