package com.parkingsharing.parking;

import com.parkingsharing.sql.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}

