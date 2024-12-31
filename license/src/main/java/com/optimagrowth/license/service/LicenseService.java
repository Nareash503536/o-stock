package com.optimagrowth.license.service;

import com.optimagrowth.license.config.ServiceConfig;
import com.optimagrowth.license.model.License;
import com.optimagrowth.license.repository.LicenseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class LicenseService {
    Logger logger = LoggerFactory.getLogger(LicenseService.class);
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    ServiceConfig config;
    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findLicenseByOrganizationIdAndLicenseId(organizationId, licenseId);
        if(license == null) {
            throw new IllegalArgumentException(
                    String.format("Unable to find license with License id " + licenseId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        return licenseRepository.save(license);
    }

    public License updateLicense(License license){
        return licenseRepository.save(license);
    }

    public String deleteLicense(String licenseId){
        License license = licenseRepository.findLicenseByLicenseId(licenseId);
        if(license == null){
            throw new IllegalArgumentException(
                    String.format("Unable to find license with License id " + licenseId)
            );
        }
        licenseRepository.delete(license);
        return String.format("Deleting license with id" + licenseId);
    }

}
