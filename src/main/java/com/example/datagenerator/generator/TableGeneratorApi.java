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
    private final AdditionalRoomTypeGenerator additionalRoomTypeGenerator;
    private final AdditionalRoomsGenerator additionalRoomsGenerator;
    private final ComplainGenerator complainGenerator;
    private final ModuleTypeGenerator moduleTypeGenerator;
    private final ModuleGenerator moduleGenerator;
    private final FacultyGenerator facultyGenerator;
    private final StatuteGenerator statuteGenerator;
    private final RoomTypeGenerator roomTypeGenerator;
    private final DormitoryGenerator dormitoryGenerator;
    private final FloorGenerator floorGenerator;
    private final RoomGenerator roomGenerator;
    private final ContractGenerator contractGenerator;
    private final PaymentStatusGenerator paymentStatusGenerator;
    private final PaymentGenerator paymentGenerator;
    private final ContractPaymentGenerator contractPaymentGenerator;
    private final ApplicationTypeGenerator applicationTypeGenerator;
    private final ApplicationStatusGenerator applicationStatusGenerator;
    private final AdditionalServicesGenerator additionalServicesGenerator;
    private final RequirementsGenerator requirementsGenerator;
    private final FieldOfStudyGenerator fieldOfStudyGenerator;
    private final DormitoryRequirementsGenerator dormitoryRequirementsGenerator;
    private final DormitorySecurityGenerator dormitorySecurityGenerator;
    private final DormitoryServicesGenerator dormitoryServicesGenerator;

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

    public AdditionalRoomTypeGenerator additionalRoomType() {
        return additionalRoomTypeGenerator;
    }

    public AdditionalRoomsGenerator additionalRoom() {
        return additionalRoomsGenerator;
    }

    public ComplainGenerator complain() {
        return complainGenerator;
    }

    public ModuleTypeGenerator moduleType() {
        return moduleTypeGenerator;
    }

    public ModuleGenerator module() {
        return moduleGenerator;
    }


    public FacultyGenerator facultyGenerator() {
        return facultyGenerator;
    }

    public StatuteGenerator statute() {
        return statuteGenerator;
    }

    public RoomTypeGenerator roomType() {
        return roomTypeGenerator;
    }

    public DormitoryGenerator dormitory() {
        return dormitoryGenerator;
    }

    public FloorGenerator floor() {
        return floorGenerator;
    }

    public RoomGenerator room() {
        return roomGenerator;
    }

    public ContractGenerator contract() {
        return contractGenerator;
    }

    public PaymentStatusGenerator paymentStatus() {
        return paymentStatusGenerator;
    }

    public PaymentGenerator payment() {
        return paymentGenerator;
    }

    public ContractPaymentGenerator contractPayment() {
        return contractPaymentGenerator;
    }

    public ApplicationTypeGenerator applicationType() {
        return applicationTypeGenerator;
    }

    public ApplicationStatusGenerator applicationStatus() {
        return applicationStatusGenerator;
    }

    public AdditionalServicesGenerator additionalServices() {
        return additionalServicesGenerator;
    }

    public RequirementsGenerator requirements() {
        return requirementsGenerator;
    }

    public FieldOfStudyGenerator fieldOfStudy() {
        return fieldOfStudyGenerator;
    }

    public DormitoryRequirementsGenerator dormitoryRequirementsGenerator() {
        return dormitoryRequirementsGenerator;
    }

    public DormitorySecurityGenerator dormitorySecurityGenerator() {
        return dormitorySecurityGenerator;
    }

    public DormitoryServicesGenerator dormitoryServicesGenerator() {
        return dormitoryServicesGenerator;
    }

}
