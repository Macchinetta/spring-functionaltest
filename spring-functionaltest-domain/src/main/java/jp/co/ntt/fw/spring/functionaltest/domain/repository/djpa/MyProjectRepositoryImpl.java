/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class MyProjectRepositoryImpl<T, ID extends Serializable> extends
                                    SimpleJpaRepository<T, ID> implements
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
            throw new UnsupportedOperationException(String.format(
                    "Does not found version field in entity class. class is '%s'.",
                    entityInformation.getJavaType().getName()));
        }

        T entity = findById(id).orElse(null);

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
