package com.ecom.controller;

import com.ecom.config.JwtTokenProvider;
import com.ecom.exception.ProductException;
import com.ecom.exception.UserException;
import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.repository.ProductRepository;
import com.ecom.request.CreateProductRequest;
import com.ecom.response.ApiResponse;
import com.ecom.service.ProductService;
import com.ecom.service.SellerService;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/products")
public class SellerProductContoller {

    @Autowired
    private ProductService productService;

    @Autowired
    private SellerService sellerService;


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;



    @GetMapping("/{sellerShopId}")
    public ResponseEntity<List<Product>> getAllShopProductsUsingId(@PathVariable Long sellerShopId, @RequestParam  int pageNumber ,@RequestParam int pageSize) throws  ProductException{

        List<Product> products = sellerService.getAllShopProducts(sellerShopId,pageNumber,pageSize);

        return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllShopProductsUsingJwt(@RequestHeader String Authorization, @RequestParam  int pageNumber ,@RequestParam int pageSize) throws ProductException, UserException {


       Seller seller =  sellerService.findSellerProfileByJwt(Authorization);
       if(seller != null){
        List<Product> products = sellerService.getAllShopProducts(seller.getSellerShopId(),pageNumber,pageSize);


           return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
       }
       return null;
    }

    @PostMapping("/create")
    public ResponseEntity<Product> createProductHandler(@RequestBody CreateProductRequest req ,@RequestHeader String Authorization) throws ProductException {

//        String authorizationHeader = request.getHeader("Authorization");
//
//        // Extract the token from the header
//        String jwtToken = null;
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            jwtToken = authorizationHeader.substring(7); // "Bearer " has 7 characters
//        }

        // Now jwtToken is a String containing the JWT token

            String email = jwtTokenProvider.getEmailFromJwtToken(Authorization);
        System.out.println(email);

            Product createdProduct = productService.createProduct(req , email);
        System.out.println(createdProduct);


        return new ResponseEntity<Product>(createdProduct, HttpStatus.ACCEPTED);





    }

    @DeleteMapping("/{productId}/delete")
    public ResponseEntity<ApiResponse> deleteProductHandler(@PathVariable Long productId) throws ProductException{

        System.out.println("dlete product controller .... ");
        String msg=productService.deleteProduct(productId);
        System.out.println("dlete product controller .... msg "+msg);
        ApiResponse res=new ApiResponse(msg,true);

        return new ResponseEntity<ApiResponse>(res,HttpStatus.ACCEPTED);

    }




}
