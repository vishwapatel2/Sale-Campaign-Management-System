package com.example.sale.campaign.management.system.repository;

import com.example.sale.campaign.management.system.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CampaignRepository extends JpaRepository<Campaign,Integer> {

    @Query(nativeQuery = true,value = "select * from campaign where start_date=current_date()")
    List<Object[]> getStringCampaignDiscountList();

    @Query(nativeQuery = true,value = "select * from campaign where end_date=current_date()")
    List<Object[]> getEndingCampaignDiscountList();

}
