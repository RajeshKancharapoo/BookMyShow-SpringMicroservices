package com.example.userservice.repository;

import com.example.userservice.entity.BookingIDS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepo extends JpaRepository<BookingIDS, Long>  {

}
