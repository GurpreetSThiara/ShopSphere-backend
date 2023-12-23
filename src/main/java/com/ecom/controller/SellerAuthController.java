package com.ecom.controller;


import com.ecom.config.JwtTokenProvider;
import com.ecom.exception.UserException;
import com.ecom.modal.Seller;

import com.ecom.modal.User;
import com.ecom.repository.SellerRepository;
import com.ecom.request.LoginRequest;
import com.ecom.response.AuthResponse;
import com.ecom.service.CustomSellerDetails;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller/auth")
public class SellerAuthController {


    private SellerRepository sellerRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenProvider jwtTokenProvider;

    private CustomSellerDetails customSellerDetails;

    public SellerAuthController(SellerRepository sellerRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, CustomSellerDetails customSellerDetails) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.customSellerDetails = customSellerDetails;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createSellerHandler(@Valid @RequestBody Seller seller) throws UserException{

        String email = seller.getEmail();
        String password = seller.getPassword();
        String firstName=seller.getFirstName();
        String lastName=seller.getLastName();

        Seller isEmailExist=sellerRepository.findByEmail(email);
        if (isEmailExist!=null) {
            // System.out.println("--------- exist "+isEmailExist).getEmail());

            throw new UserException("Email Is Already Used With Another Account");
        }
        Seller createdSeller= new Seller();
        createdSeller.setEmail(email);
        createdSeller.setFirstName(firstName);
        createdSeller.setLastName(lastName);
        createdSeller.setPassword(passwordEncoder.encode(password));



        Seller savedUser= sellerRepository.save(createdSeller);
        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        AuthResponse authResponse= new AuthResponse(token,true);

        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);


    }

    @PostMapping("/signin")
    public  ResponseEntity<AuthResponse> signinSellerHandler(@RequestBody LoginRequest loginRequest){
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        System.out.println(username +" ----- "+password);

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtTokenProvider.generateToken(authentication);
        AuthResponse authResponse= new AuthResponse();

        authResponse.setStatus(true);
        authResponse.setJwt(token);

        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.OK);
    }
    private Authentication authenticate(String username, String password) {

        UserDetails userDetails = customSellerDetails.loadSellerByUsername(username);

        System.out.println("sign in userDetails - "+userDetails);

        if (userDetails == null) {
            System.out.println("sign in userDetails - null " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            System.out.println("sign in userDetails - password not match " + userDetails);
            throw new BadCredentialsException("Invalid username or password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
