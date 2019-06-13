package de.ukowohl.experiments.recreator.factory;


import org.hibernate.tool.hbm2ddl.SchemaValidator;

import java.util.function.Supplier;

public class SchemaValidatorFactory implements Supplier<SchemaValidator> {

    @Override
    public SchemaValidator get() {
        return new SchemaValidator();
    }
}
