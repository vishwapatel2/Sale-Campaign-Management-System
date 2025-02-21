package com.example.sale.campaign.management.system.controller;

import com.example.sale.campaign.management.system.model.Product;
import com.example.sale.campaign.management.system.service.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductSer productSer;

    @PostMapping("/save-all-product")
    public ResponseEntity<?> saveAllProduct(@RequestBody List<Product> products){
        productSer.saveAllProduct(products);
        return ResponseEntity.ok("Record added");
    }

    @GetMapping("/sort-by-filed")
    public ResponseEntity<?> sortByField(@RequestParam String filed){
       return ResponseEntity.ok( productSer.sort(filed));
    }

    @GetMapping("/get-product")
    public ResponseEntity<?>  getProduct(@RequestParam int pageNumber,@RequestParam int pageSize){
            return ResponseEntity.ok(productSer.getProduct(pageNumber,pageSize));
    }

    @GetMapping("/get-product-sort-by")
    public ResponseEntity<?>  getProductAndSort(@RequestParam int pageNumber,@RequestParam int pageSize,@RequestParam String field){
        return ResponseEntity.ok(productSer.getProductAndSort(pageNumber,pageSize,field));
    }
}
