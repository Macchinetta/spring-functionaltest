/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class MyProjectRepositoryImpl<T, ID extends Serializable>
                                                                 extends
                                                                 SimpleJpaRepository<T, ID>
                                                                                           implements
                                                                                           MyProjectRepository<T, ID> {

    private JpaEntityInformation<T, ID> entityInformation;

    Method versionMethod;

    public MyProjectRepositoryImpl(
            JpaEntityInformation<T, ID> entityInformation,
            EntityManager entityManager) {

        super(entityInformation, entityManager);
        this.entityInformation = entityInformation;
        try {
            versionMethod = entityInformation.getJavaType().getMethod(
                    "getVersion");
        } catch (NoSuchMethodException | SecurityException e) {
        }
    }

    public T findOneWithValidVersion(ID id, Long version) {

        if (versionMethod == null) {
            throw new UnsupportedOperationException(String
                    .format("Does not found version field in entity class. class is '%s'.",
                            entityInformation.getJavaType().getName()));
        }

        T entity = findOne(id);

        if (entity != null && version != null) {
            Long currentVersion;
            try {
                currentVersion = (Long) versionMethod.invoke(entity);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                throw new IllegalStateException(e);
            }
            if (!version.equals(currentVersion)) {
                throw new ObjectOptimisticLockingFailureException(entityInformation
                        .getJavaType().getName(), id);
            }
        }
        return entity;
    }

}
