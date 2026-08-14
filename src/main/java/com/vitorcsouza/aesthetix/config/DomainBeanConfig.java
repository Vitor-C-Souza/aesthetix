package com.vitorcsouza.aesthetix.config;

import com.vitorcsouza.aesthetix.domain.port.in.*;
import com.vitorcsouza.aesthetix.domain.port.out.*;
import com.vitorcsouza.aesthetix.domain.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfig {

    @Bean
    public PatientInputPort patientInputPort(PatientOutputPort patientOutputPort) {
        return new PatientService(patientOutputPort);
    }

    @Bean
    public AppointmentInputPort appointmentInputPort(AppointmentOutputPort appointmentOutputPort,
                                                     PatientOutputPort patientOutputPort,
                                                     ProfessionalOutputPort professionalOutputPort,
                                                     ProcedureOutputPort procedureOutputPort) {
        return new AppointmentService(appointmentOutputPort, patientOutputPort, professionalOutputPort, procedureOutputPort);
    }

    @Bean
    public ProfessionalInputPort professionalInputPort(ProfessionalOutputPort professionalOutputPort) {
        return new ProfessionalService(professionalOutputPort);
    }

    @Bean
    public ProcedureInputPort procedureInputPort(ProcedureOutputPort procedureOutputPort) {
        return new ProcedureService(procedureOutputPort);
    }

    @Bean
    public PackageInputPort packageInputPort(PackageOutputPort packageOutputPort, PatientOutputPort patientOutputPort, ProcedureOutputPort procedureOutputPort) {
        return new PackageService(packageOutputPort, patientOutputPort, procedureOutputPort);
    }

    @Bean
    public EquipmentInputPort equipmentInputPort(EquipmentOutputPort equipmentOutputPort) {
        return new EquipmentService(equipmentOutputPort);
    }

    @Bean
    public EvolutionPhotoInputPort evolutionPhotoInputPort(EvolutionPhotoOutputPort photoOutputPort, PatientOutputPort patientOutputPort, AppointmentOutputPort appointmentOutputPort) {
        return new EvolutionPhotoService(photoOutputPort, patientOutputPort, appointmentOutputPort);
    }

    @Bean
    public AnamnesisInputPort anamnesisInputPort(AnamnesisOutputPort anamnesisOutputPort, PatientOutputPort patientOutputPort, ProfessionalOutputPort professionalOutputPort) {
        return new AnamnesisService(anamnesisOutputPort, patientOutputPort, professionalOutputPort);
    }

    @Bean
    public FinancialRecordInputPort financialRecordInputPort(FinancialRecordOutputPort financialOutputPort, PatientOutputPort patientOutputPort, AppointmentOutputPort appointmentOutputPort, ProfessionalOutputPort professionalOutputPort) {
        return new FinancialRecordService(financialOutputPort, patientOutputPort, appointmentOutputPort, professionalOutputPort);
    }
}
