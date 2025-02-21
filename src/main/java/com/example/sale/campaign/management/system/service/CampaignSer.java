package com.example.sale.campaign.management.system.service;

import com.example.sale.campaign.management.system.model.Campaign;
import com.example.sale.campaign.management.system.model.CampaignDiscount;
import com.example.sale.campaign.management.system.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CampaignSer {

    @Autowired
    private CampaignRepository campaignRepository;


    public ResponseEntity<?> addCampaign(Campaign campaign) {
        try {

            List<CampaignDiscount> campaignDiscountList = campaign.getCampaignDiscountList();

            for (CampaignDiscount campaignDiscount : campaignDiscountList) {
                campaignDiscount.setCampaign(campaign);
            }
            Campaign campaignObj = campaignRepository.save(campaign);
            System.out.println(new Date());
            return new ResponseEntity<>(campaignObj, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed to add campaign", HttpStatus.BAD_REQUEST);
    }


}
