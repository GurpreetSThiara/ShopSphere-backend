package com.ecom.controller;


import com.ecom.exception.ProductException;
import com.ecom.exception.UserException;
import com.ecom.modal.Order;
import com.ecom.modal.OrderItem;
import com.ecom.modal.Seller;
import com.ecom.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/shop")
public class SellerController {
    @Autowired
    private SellerService sellerService;

    @GetMapping("/")
    public ResponseEntity<Seller> getShop(@RequestHeader String Authorization) throws ProductException, UserException {

        Seller seller =  sellerService.findSellerProfileByJwt(Authorization);
        if(seller != null){
//            List<Product> products = sellerService.getAllShopProducts(seller.getSellerShopId(),pageNumber,pageSize);

            return new ResponseEntity<Seller>(seller, HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/{sellerShopId}/orders")
    public ResponseEntity<Page<OrderItem>> getOrders(@PathVariable Long sellerShopId ,@RequestHeader String Authorization , @RequestParam  int pageNumber ,@RequestParam int pageSize) throws UserException {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        Page<OrderItem> orders = sellerService.getAllShopOrders(sellerShopId,pageNumber,pageSize);
        System.out.println("yyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

        return  new ResponseEntity<Page<OrderItem>>(orders, HttpStatus.OK);
    }
}
