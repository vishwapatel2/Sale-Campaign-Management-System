package com.example.sale.campaign.management.system.controller;


import com.example.sale.campaign.management.system.model.Campaign;
import com.example.sale.campaign.management.system.service.CampaignSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/campaign")
public class CampaignController {

    @Autowired
    private CampaignSer campaignSer;

    @PostMapping("/add-campaign")
    public ResponseEntity<?> addCampaign(@RequestBody Campaign campaign){
        return ResponseEntity.ok(campaignSer.addCampaign(campaign));
    }

}
