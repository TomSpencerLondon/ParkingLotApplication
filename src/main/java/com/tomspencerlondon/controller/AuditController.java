package com.tomspencerlondon.controller;

import com.tomspencerlondon.entity.AuditLog;
import com.tomspencerlondon.repository.AuditRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audit")
public class AuditController {

  @Autowired
  AuditRepository auditRepository;

  @GetMapping("/logs")
  public List<AuditLog> getRecords() {
    return auditRepository.findAll();
  }

}

