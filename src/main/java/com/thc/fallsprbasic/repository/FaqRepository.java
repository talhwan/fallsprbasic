package com.thc.fallsprbasic.repository;

import com.thc.fallsprbasic.domain.Faq;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long>{
}
