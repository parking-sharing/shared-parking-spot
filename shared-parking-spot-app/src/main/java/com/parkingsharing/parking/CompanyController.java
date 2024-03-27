package com.parkingsharing.parking;

import com.parkingsharing.sql.Company;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return new ResponseEntity<>(companies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable("id") int id) {
        Company company = companyRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Company not found with id: " + id));
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody Company company) {
        try {
            Company savedCompany = companyRepository.save(company);
            return new ResponseEntity<>(savedCompany, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("{\"status_code\": 409, \"error\": \"Company must be unique\"}");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable("id") int id, @RequestBody Company updatedCompany) {
        Company company = companyRepository.findById(id)
                .map(existingCompany -> {
                    existingCompany.setName(updatedCompany.getName());
                    existingCompany.setDescription(updatedCompany.getDescription());
                    return companyRepository.save(existingCompany);
                }).orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + id));

        return new ResponseEntity<>(company, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") int id) {
        return companyRepository.findById(id).map(company -> {
            companyRepository.deleteById(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }).orElseThrow(() -> new EntityNotFoundException("Company not found with id: " + id));
    }
}

