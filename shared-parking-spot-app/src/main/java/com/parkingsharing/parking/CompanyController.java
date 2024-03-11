package com.parkingsharing.parking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping
    public ResponseEntity getAllCompanies() {
        return ResponseEntity.ok(this.companyRepository.findAll());
    }


    @PostMapping("")
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }


    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyRepository.findById(id).orElse(null);
    }


    @PutMapping("/{id}")
    public ResponseEntity updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Company existingCompany = companyOptional.get();
        existingCompany.setName(updatedCompany.getName());
        existingCompany.setDescription(updatedCompany.getDescription());

        companyRepository.save(existingCompany);

        return ResponseEntity.ok(existingCompany);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCompany(@PathVariable Long id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        companyRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }

}

