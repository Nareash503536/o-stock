package com.optimagrowth.license.repository;

import com.optimagrowth.license.model.License;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LicenseRepository extends CrudRepository<License, String> {
    public License findLicenseByLicenseId(String licenseId);
    public License findLicenseByOrganizationIdAndLicenseId(String organizationId, String licenseId);
}
