/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.exhn;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Employee;

public interface EmployeeService {

    Employee findEmployee(int employeeId);

    void updateEmployee(Employee employee);

}
