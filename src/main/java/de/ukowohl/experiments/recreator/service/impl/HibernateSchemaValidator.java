package de.ukowohl.experiments.recreator.service.impl;

import de.ukowohl.experiments.recreator.factory.SchemaValidatorFactory;
import de.ukowohl.experiments.recreator.service.CallableSchemaValidator;
import org.hibernate.HibernateException;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;


public class HibernateSchemaValidator implements CallableSchemaValidator {

    @Autowired
    private SchemaValidatorFactory schemaValidatorFactory;

    private Metadata metadata;

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        this.metadata = metadata;
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {

    }

    @Override
    public boolean dbSchemaIsValid() {
        try {
            schemaValidatorFactory.get().validate(metadata);
            return true;
        } catch (HibernateException ex) {
            return false;
        }
    }
}
