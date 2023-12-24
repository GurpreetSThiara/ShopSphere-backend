package com.ecom.service;

import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.modal.Shop;
import com.ecom.repository.ProductRepository;
import com.ecom.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Product> getAllShopProducts(Long SellerShopId) {

        return productRepository.findBySellerShopId(SellerShopId);

    }

    @Override
    public List<Shop> getAllShops() {

       List<Shop> allShops= new ArrayList<Shop>();
      List<Seller> allSellers =  sellerRepository.findAllSellers();
        for (Seller seller : allSellers) {
            List<Product> products = productRepository.findBySellerShopId(seller.getSellerShopId());
            Shop shop = new Shop();
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
