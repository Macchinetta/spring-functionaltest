/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.repository.exhn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;

public interface EmployeeRepository {

    Employee findOneById(int employeeId);

    long countByEmployeeName(Employee employee);

    void update(Employee employee);

}
