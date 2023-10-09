package com.example.admin;

import com.example.Student.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    @Query("SELECT a FROM Admin a WHERE a.id = :id AND a.password = :password")
    Admin findByIdAndPassword(@Param("id") Long id, @Param("password") String password);

}
