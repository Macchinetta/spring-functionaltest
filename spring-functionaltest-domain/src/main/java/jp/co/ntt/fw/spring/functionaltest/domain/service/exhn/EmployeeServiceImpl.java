/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.BusinessException;
import org.terasoluna.gfw.common.message.ResultMessages;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.exhn.EmployeeRepository;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Inject
    private EmployeeRepository repository;

    @Override
    public Employee findEmployee(int employeeId) {

        return repository.findOneById(employeeId);
    }

    @Override
    public void updateEmployee(Employee employee) {

        long count = repository.countByEmployeeName(employee);

        if (0 < count) {
            ResultMessages messages = ResultMessages.danger();
            messages.add("e.sf.exhn.8002");
            throw new BusinessException(messages);
        }

        repository.update(employee);

    }

}
