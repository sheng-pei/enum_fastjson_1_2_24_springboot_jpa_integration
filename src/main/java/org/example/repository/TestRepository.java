package org.example.repository;

import org.example.po.TestPo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<TestPo, Long> {
}
