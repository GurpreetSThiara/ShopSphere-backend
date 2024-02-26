package com.ecom.controller;

import com.ecom.exception.UserException;
import com.ecom.modal.OrderItem;
import com.ecom.modal.Seller;
import com.ecom.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;


@RestController
@RequestMapping("/seller/shop")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/")
    public ResponseEntity<Seller> getShop(@RequestHeader String Authorization) {
        try {
            Seller seller = sellerService.findSellerProfileByJwt(Authorization);
            if (seller != null) {
                return new ResponseEntity<>(seller, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (UserException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{sellerShopId}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long sellerShopId, @RequestBody Seller updatedSeller) {
        Seller updated = sellerService.updateSeller(sellerShopId, updatedSeller);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

// .


    @GetMapping("/{sellerShopId}/orders")
    public ResponseEntity<Page<OrderItem>> getOrders(@PathVariable Long sellerShopId,
                                                     @RequestHeader String Authorization,
                                                     @RequestParam int pageNumber,
                                                     @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrders(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{sellerShopId}/orders/all")
    public ResponseEntity<Page<OrderItem>> getAllOrdersForAllTime(@PathVariable Long sellerShopId,
                                                                  @RequestHeader String Authorization,
                                                                  @RequestParam int pageNumber,
                                                                  @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrdersForAllTime(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{sellerShopId}/orders/last-month")
    public ResponseEntity<Page<OrderItem>> getOrdersForLastMonth(@PathVariable Long sellerShopId,
                                                                 @RequestHeader String Authorization,
                                                                 @RequestParam int pageNumber,
                                                                 @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrdersForLastMonth(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{sellerShopId}/orders/last-week")
    public ResponseEntity<Page<OrderItem>> getOrdersForLastWeek(@PathVariable Long sellerShopId,
                                                                @RequestHeader String Authorization,
                                                                @RequestParam int pageNumber,
                                                                @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrdersForLastWeek(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{sellerShopId}/orders/last-24-hours")
    public ResponseEntity<Page<OrderItem>> getOrdersForLast24Hours(@PathVariable Long sellerShopId,
                                                                   @RequestHeader String Authorization,
                                                                   @RequestParam int pageNumber,
                                                                   @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrdersForLast24Hours(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{sellerShopId}/orders/last-year")
    public ResponseEntity<Page<OrderItem>> getOrdersForLastYear(@PathVariable Long sellerShopId,
                                                                @RequestHeader String Authorization,
                                                                @RequestParam int pageNumber,
                                                                @RequestParam int pageSize) {
        Page<OrderItem> orders = sellerService.getAllShopOrdersForLastYear(sellerShopId, pageNumber, pageSize);
        if (orders != null) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/orders/graph")
    public ResponseEntity<Map<String, List<OrderItem>>> getShopOrdersByMonth(
            @RequestParam Long shopId,
            @RequestParam String lastYear// Assuming lastYear is passed as a string in format "yyyy-MM-dd HH:mm:ss"

    ) {
        LocalDateTime lastYearDateTime = LocalDateTime.parse(lastYear, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String, List<OrderItem>> ordersByMonth = sellerService.getShopOrdersByMonth(shopId, lastYearDateTime);
        return new ResponseEntity<>(ordersByMonth, HttpStatus.OK);
    }
}
