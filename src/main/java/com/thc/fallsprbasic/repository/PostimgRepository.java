package com.thc.fallsprbasic.repository;

import com.thc.fallsprbasic.domain.Postimg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostimgRepository extends JpaRepository<Postimg, Long>{
}
