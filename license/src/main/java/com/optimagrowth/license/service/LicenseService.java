package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ServiceConfig config;
    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findLicenseByOrganizationIdAndLicenceId(organizationId, licenseId);
        if(license == null) {
            throw new IllegalArgumentException(
                    String.format(messageSource.getMessage(
                                    "license.search.error.message", null, null),
                            licenseId, organizationId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenceId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        License license = licenseRepository.findLicenseByLicenceId(licenseId);
        if(license == null){
            throw new IllegalArgumentException(
                    String.format(messageSource.getMessage(
                            "license.search.error.message", null, null
                    ))
            );
        }
        return String.format(messageSource.getMessage(
                "license.delete.message", null, null
        ));
    }

}
