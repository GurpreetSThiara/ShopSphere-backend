package com.ecom.service;

import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.modal.Shop;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class SellerServiceImplementation implements SellerService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;

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
}
