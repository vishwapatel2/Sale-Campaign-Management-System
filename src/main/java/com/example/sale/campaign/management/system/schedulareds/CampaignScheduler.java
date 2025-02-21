package com.example.sale.campaign.management.system.schedulareds;

import com.example.sale.campaign.management.system.model.Campaign;
import com.example.sale.campaign.management.system.model.CampaignDiscount;
import com.example.sale.campaign.management.system.model.History;
import com.example.sale.campaign.management.system.model.Product;
import com.example.sale.campaign.management.system.repository.CampaignRepository;
import com.example.sale.campaign.management.system.repository.HistoryRepository;
import com.example.sale.campaign.management.system.repository.ProductRepository;
import com.example.sale.campaign.management.system.service.CampaignApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.sql.Date;
import java.util.List;

@Configuration
@EnableScheduling
public class CampaignScheduler {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private CampaignApplyService campaignApplyService;

    @Scheduled(cron = "0 0 0 * * *")
    public void setCampaignDiscount() {
        System.out.println(new java.util.Date());
//        List<Object[]> list=campaignRepository.getStringCampaignDiscountList();
//
//        for(Object[] objects:list){
//            Campaign campaign=campaignRepository.findById((int)objects[0]).get();
//            for(CampaignDiscount campaignDiscount:campaign.getCampaignDiscountList()){
//                Product product=productRepository.findById(campaignDiscount.getProductId().getpId()).get();
//
//                //make history of product
//                History history=new History();
//                history.setDate(new Date(System.currentTimeMillis()));
//                history.setProduct(product);
//                history.setDiscount(product.getDiscount());
//                history.setPrice(product.getCurrentPrice());
//
//                //change current price pf product
//
//                double discount=campaignDiscount.getDiscount();
//                long currentPrice=product.getCurrentPrice();
//                long calculateCurrentPrice=(long)(currentPrice-((discount*currentPrice)/100));
//
//                product.setCurrentPrice(calculateCurrentPrice);
//                product.setDiscount(discount+product.getDiscount());
//
//                productRepository.save(product);
//                historyRepository.save(history);
//            }
//        }

        campaignApplyService.applyCampaign();
        System.out.println(new java.util.Date());
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void removeCampaignDiscount() {

        List<Object[]> list = campaignRepository.getEndingCampaignDiscountList();

        for (Object[] objects : list) {
            Campaign campaign = campaignRepository.findById((int) objects[0]).get();
            for (CampaignDiscount campaignDiscount : campaign.getCampaignDiscountList()) {
                History oldHistory = historyRepository.getHistory(campaign.getStartDate(), campaignDiscount.getProductId().getpId());

                //find product
             //
                    Product product = productRepository.findById(campaignDiscount.getProductId().getpId()).get();
                History newHistory = new History();
                newHistory.setProduct(product);
                newHistory.setDiscount(product.getDiscount());
                newHistory.setPrice(product.getCurrentPrice());
                newHistory.setDate(new Date(System.currentTimeMillis()));

                // make change in product to remove campaign

                product.setCurrentPrice(oldHistory.getPrice());
                product.setDiscount(oldHistory.getDiscount());

                productRepository.save(product);
                historyRepository.save(newHistory);

            }
        }
    }
}
