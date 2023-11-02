package com.example.datagenerator.generator;

import com.example.datagenerator.generator.generators.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TableGeneratorApi {
    private final UserGenerator userGenerator;
    private final ManagerWorkerGenerator managerWorkerGenerator;
    private final OfficeWorkerGenerator officeWorkerGenerator;
    private final SecurityWorkerGenerator securityWorkerGenerator;
    private final StudentGenerator studentGenerator;

    public UserGenerator user() {
        return userGenerator;
    }

    public ManagerWorkerGenerator manager() {
        return managerWorkerGenerator;
    }

    public OfficeWorkerGenerator officeWorker() {
        return officeWorkerGenerator;
    }

    public SecurityWorkerGenerator securityWorker() {
        return securityWorkerGenerator;
    }

    public StudentGenerator student() {
        return studentGenerator;
    }

}
