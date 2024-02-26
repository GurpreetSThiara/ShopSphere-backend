package com.ecom.service;

import com.ecom.config.JwtTokenProvider;
import com.ecom.exception.UserException;
import com.ecom.modal.*;
import com.ecom.repository.OrderItemRepository;
import com.ecom.repository.OrderRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.format.TextStyle;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import java.util.Map;

import java.util.Locale;



@Service
public class SellerServiceImplementation implements SellerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired

    private OrderItemRepository orderItemRepository;

    @Override
    public Seller findSellerProfileByJwt(String jwt) throws UserException {
        System.out.println("user service");
        String email=jwtTokenProvider.getEmailFromJwtToken(jwt);

        System.out.println("email"+email);

        Seller seller = sellerRepository.findByEmail(email);



        if(seller==null) {
            throw new UserException("user not exist with email "+email);
        }
        System.out.println("email user"+seller.getEmail());
        return seller;
    }

    @Override
    public List<Product> getAllShopProducts(Long SellerShopId , int pageNumber ,int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<Product> productPage= productRepository.findBySellerShopId(SellerShopId ,pageRequest);

        return productPage.getContent();

    }

    @Override
    public List<Shop> getAllShops( int pageNumber ,int pageSize) {
        // Replace with the actual sellerShopId
        int page = 0; // Page number (0-indexed)
        int size = 5; // Number of items per page



       List<Shop> allShops= new ArrayList<Shop>();
        PageRequest pageReq = PageRequest.of(pageNumber, pageSize);
      Page<Seller> sellers =  sellerRepository.findAllSellers( pageReq);
        List<Seller> allSellers = sellers.getContent();
        for (Seller seller : allSellers) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Product> productPage = productRepository.findBySellerShopId(seller.getSellerShopId(), pageRequest);

            List<Product> products = productPage.getContent();

            Shop shop = new Shop();
            shop.setId(seller.getSellerShopId());
            shop.setShopName(seller.getShopName());
            shop.setDescription(seller.getDescription());
            shop.setEmail(seller.getEmail());
            shop.setOwner(seller.getFirstName()+' '+seller.getLastName());
            shop.setPhone(seller.getMobile());
            shop.setProducts(products);
            allShops.add(shop);

        }

        return allShops;


    }

    @Override
    public Page<OrderItem> getAllShopOrders(Long sellerShopId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);


       return orderItemRepository.getShopOrders(sellerShopId,pageable);

        // Create a pageable object with the given page number and page size

//        Page<Order> orders = orderRepository.getAllShopOrders(sellerShopId, pageable);
//        System.out.println("getallshopOrders");
//        System.out.println(orders);
//        Page<Order> filteredOrders = orders.map(order -> {
//            System.out.println(order);
//            System.out.println("order");
//            // Filter order items by sellerShopId
//            List<OrderItem> filteredOrderItems = order.getOrderItems().stream()
//                    .filter(item -> item.getSellerId().equals(sellerShopId))
//                    .collect(Collectors.toList());
//
//            // Create a new order with filtered order items
//            Order filteredOrder = new Order();
//            filteredOrder.setId(order.getId());
//            filteredOrder.setShippingAddress(order.getShippingAddress());
//            filteredOrder.setOrderStatus(order.getOrderStatus());
//            filteredOrder.setOrderDate(order.getOrderDate());
//            filteredOrder.setCreatedAt(order.getCreatedAt());
//            filteredOrder.setPaymentDetails(order.getPaymentDetails());
//            filteredOrder.setOrderId(order.getOrderId());
//            filteredOrder.setUser(order.getUser());
//            filteredOrder.setSellerIds(order.getSellerIds());
//            filteredOrder.setOrderItems(filteredOrderItems);
//            // Set other fields as needed
//
//            return filteredOrder;
//        });
//        System.out.println("filteredOrders");
//        System.out.println(filteredOrders);
//
//        return filteredOrders;




    }

    @Override
    public Page<OrderItem> getAllShopOrdersForAllTime(Long SellerShopId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.getShopOrders(SellerShopId, pageable);
    }

    @Override
    public Page<OrderItem> getAllShopOrdersForLastMonth(Long SellerShopId, int pageNumber, int pageSize) {
        LocalDateTime lastMonth = LocalDateTime.now().minusMonths(1);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.getShopOrdersForLastMonth(SellerShopId, lastMonth, pageable);
    }

    @Override
    public Page<OrderItem> getAllShopOrdersForLastWeek(Long SellerShopId, int pageNumber, int pageSize) {
        LocalDateTime lastWeek = LocalDateTime.now().minusWeeks(1);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.getShopOrdersForLastWeek(SellerShopId, lastWeek, pageable);
    }

    @Override
    public Page<OrderItem> getAllShopOrdersForLast24Hours(Long SellerShopId, int pageNumber, int pageSize) {
        LocalDateTime last24Hours = LocalDateTime.now().minusHours(24);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.getShopOrdersForLast24Hours(SellerShopId, last24Hours, pageable);
    }

    @Override
    public Page<OrderItem> getAllShopOrdersForLastYear(Long SellerShopId, int pageNumber, int pageSize) {
        LocalDateTime lastYear = LocalDateTime.now().minusYears(1);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return orderItemRepository.getShopOrdersForLastYear(SellerShopId, lastYear, pageable);
    }

    @Override
    public Seller updateSeller(Long sellerShopId, Seller updatedSeller) {
        Optional<Seller> optionalSeller = sellerRepository.findById(sellerShopId);
        if (optionalSeller.isPresent()) {
            Seller existingSeller = optionalSeller.get();
            // Update fields of existingSeller with values from updatedSeller
            existingSeller.setFirstName(updatedSeller.getFirstName());
            existingSeller.setLastName(updatedSeller.getLastName());
            existingSeller.setEmail(updatedSeller.getEmail());
            existingSeller.setMobile(updatedSeller.getMobile());
            existingSeller.setShopName(updatedSeller.getShopName());
            existingSeller.setDescription(updatedSeller.getDescription());
            existingSeller.setWebsiteUrl(updatedSeller.getWebsiteUrl());
            existingSeller.setLogoImageUrl(updatedSeller.getLogoImageUrl());
            existingSeller.setStreetAddress(updatedSeller.getStreetAddress());
            existingSeller.setCity(updatedSeller.getCity());
            existingSeller.setState(updatedSeller.getState());
            existingSeller.setZipCode(updatedSeller.getZipCode());
            existingSeller.setCountry(updatedSeller.getCountry());
            existingSeller.setPhoneNumber(updatedSeller.getPhoneNumber());
            existingSeller.setAlternatePhoneNumber(updatedSeller.getAlternatePhoneNumber());
            existingSeller.setFaxNumber(updatedSeller.getFaxNumber());
            existingSeller.setBusinessRegistrationNumber(updatedSeller.getBusinessRegistrationNumber());
            existingSeller.setTaxId(updatedSeller.getTaxId());
            existingSeller.setFacebookProfile(updatedSeller.getFacebookProfile());
            existingSeller.setTwitterProfile(updatedSeller.getTwitterProfile());
            existingSeller.setInstagramProfile(updatedSeller.getInstagramProfile());
            existingSeller.setBankAccountDetails(updatedSeller.getBankAccountDetails());
            existingSeller.setPaypalEmail(updatedSeller.getPaypalEmail());
            existingSeller.setOpeningTime(updatedSeller.getOpeningTime());
            existingSeller.setClosingTime(updatedSeller.getClosingTime());
            existingSeller.setDaysOfOperation(updatedSeller.getDaysOfOperation());
            existingSeller.setLatitude(updatedSeller.getLatitude());
            existingSeller.setLongitude(updatedSeller.getLongitude());

            // Save the updated seller entity
            return sellerRepository.save(existingSeller);
        } else {
            throw new IllegalArgumentException("Seller not found with id: " + sellerShopId);
        }
    }



    @Override
    public Map<String, List<OrderItem>> getShopOrdersByMonth(Long shopId, LocalDateTime lastYear) {

        List<OrderItem> allOrders = orderItemRepository.getShopOrdersForLastYearForGraph(shopId, lastYear);


        // Initialize a map to hold orders for each month
        Map<String, List<OrderItem>> ordersByMonth = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            LocalDateTime monthStart = LocalDateTime.of(lastYear.getYear(), i, 1, 0, 0);
            String monthName = monthStart.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            ordersByMonth.put(monthName, new ArrayList<>());
        }
        System.out.println(ordersByMonth);



        // Group orders by month
        for (OrderItem orderItem : allOrders) {
            LocalDateTime createdAt = orderItem.getCreatedAt();
            String monthName = createdAt.getMonth().getDisplayName(TextStyle.FULL, Locale.getDefault());
            ordersByMonth.get(monthName).add(orderItem);
        }
        System.out.println(ordersByMonth);

        return ordersByMonth;
    }
}
