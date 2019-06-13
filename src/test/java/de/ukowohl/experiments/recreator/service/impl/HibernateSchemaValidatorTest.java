package de.ukowohl.experiments.recreator.service.impl;

import de.ukowohl.experiments.recreator.factory.SchemaValidatorFactory;
import org.hibernate.HibernateException;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.hibernate.tool.hbm2ddl.SchemaValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HibernateSchemaValidatorTest {

    @Mock
    private SchemaValidatorFactory factory;

    @InjectMocks
    private HibernateSchemaValidator validator;

    @Test
    void shouldHaveValidSchema(
            @Mock Metadata metadata,
            @Mock SessionFactoryImplementor sessionFactoryImplementor,
            @Mock SessionFactoryServiceRegistry sessionFactoryServiceRegistry,
            @Mock SchemaValidator schemaValidator
    ) {
        executeTest(metadata, sessionFactoryImplementor, sessionFactoryServiceRegistry, schemaValidator, true);
    }

    @Test
    void shouldHaveNoValidSchema(
            @Mock Metadata metadata,
            @Mock SessionFactoryImplementor sessionFactoryImplementor,
            @Mock SessionFactoryServiceRegistry sessionFactoryServiceRegistry,
            @Mock SchemaValidator schemaValidator
    ) {
        doThrow(new HibernateException("something")).when(schemaValidator).validate(any());
        executeTest(metadata, sessionFactoryImplementor, sessionFactoryServiceRegistry, schemaValidator, false);
    }

    private void executeTest(
            @Mock Metadata metadata,
            @Mock SessionFactoryImplementor sessionFactoryImplementor,
            @Mock SessionFactoryServiceRegistry sessionFactoryServiceRegistry,
            @Mock SchemaValidator schemaValidator,
            boolean expected
    ) {
        doReturn(schemaValidator).when(factory).get();

        validator.integrate(metadata, sessionFactoryImplementor, sessionFactoryServiceRegistry);

        boolean actual = validator.dbSchemaIsValid();

        assertThat(actual).isEqualTo(expected);
        verify(factory).get();
        verify(schemaValidator).validate(metadata);
    }
}
