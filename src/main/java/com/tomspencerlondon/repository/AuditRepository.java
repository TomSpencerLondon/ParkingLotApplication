package com.tomspencerlondon.repository;

import com.tomspencerlondon.entity.AuditLog;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditLog, Integer> {


  List<AuditLog> findAllByCreateDate(LocalDate createDate);
}

