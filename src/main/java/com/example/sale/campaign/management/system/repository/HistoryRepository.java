package com.example.sale.campaign.management.system.repository;

import com.example.sale.campaign.management.system.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;

public interface HistoryRepository extends JpaRepository<History,Integer> {

    @Query(value = "select * from history where date=?1 and p_id=?2",nativeQuery = true)
    History getHistory(Date date, int pId);
}
