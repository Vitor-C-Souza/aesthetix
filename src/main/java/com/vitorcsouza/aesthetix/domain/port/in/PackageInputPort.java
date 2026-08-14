package com.vitorcsouza.aesthetix.domain.port.in;

import com.vitorcsouza.aesthetix.domain.model.Package;

import java.util.List;
import java.util.UUID;

public interface PackageInputPort {
    Package create(Package sessionPackage);

    Package update(UUID id, Package sessionPackage);

    Package findById(UUID id);

    List<Package> findByPatientId(UUID patientId);

    Package consumeSession(UUID id);

    void delete(UUID id);
}
