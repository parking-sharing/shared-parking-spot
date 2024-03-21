package com.parkingsharing.parking;

import com.parkingsharing.sql.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private final CompanyRepository companyRepository;

    public CompanyController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") int id) {
        return companyRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void addCompany(@RequestBody Company company) {
        companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCompany(@PathVariable int id, @RequestBody Company updatedCompany) {
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
    public ResponseEntity deleteCompany(@PathVariable int id) {
        Optional<Company> companyOptional = companyRepository.findById(id);
        if (!companyOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        companyRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}

