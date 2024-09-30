package com.thc.fallsprbasic.repository;

import com.thc.fallsprbasic.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{
}
