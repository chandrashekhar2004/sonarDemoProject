package com.simplifyvms.config.repository;



import com.simplifyvms.config.entity.GlobalConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlobalConfigRepository extends JpaRepository<GlobalConfig, Long> {

}
