/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
