package com.example.SpringLaptop.model;

import com.example.SpringLaptop.entities.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaptopRepoitory extends JpaRepository<Laptop, Long> {
}
