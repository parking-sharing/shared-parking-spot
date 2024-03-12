package com.parkingsharing.parking;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private List<Company> companies = new ArrayList<>(Arrays.asList(new Company(1, "IBM", "example company 1"), new Company(2, "Intel", "example company 2")));
    @GetMapping
    public List<Company> getAllCompanies() {
        return companies;
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") int id) {
        for(Company company : companies){
            if(company.getId() == id){
                return company;
            }
        }
        return null;
    }

    @PostMapping("")
    public void addCompany(@RequestBody Company company) {
        companies.add(company);
    }

    @PutMapping("/{id}")
    public void updateCompany(@PathVariable("id") int id, @RequestBody Company updatedCompany) {
        Company company = getCompanyById(id);
        if (company != null) {
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") int id) {
        Company companyToDelete = getCompanyById(id);
        companies.remove(companyToDelete);
    }
}

