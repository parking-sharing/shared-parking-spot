package com.parkingsharing.parking;

import com.parkingsharing.sql.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") int id) {
        return companyRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    public Company addCompany(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable("id") int id, @RequestBody Company updatedCompany) {
        return companyRepository.findById(id)
                .map(company -> {
                    company.setName(updatedCompany.getName());
                    company.setDescription(updatedCompany.getDescription());
                    return companyRepository.save(company);
                }).orElseGet(() -> {
                    updatedCompany.setId(id);
                    return companyRepository.save(updatedCompany);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") int id) {
        companyRepository.deleteById(id);
    }
}

