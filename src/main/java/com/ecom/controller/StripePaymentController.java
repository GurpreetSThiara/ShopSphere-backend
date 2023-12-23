//package com.ecom.controller;
//
//import com.ecom.request.PaymentRequest;
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.Token;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/payment")
//public class StripePaymentController {
//
//
//    private String stripeSecretKey="sk_test_51OPPxMSBPkknLY63rkcv150MWsdt2zFZsy4kQVW7q0C1PARw3ectZE6x4kBKn4zdRR7McljMFYMf1ZBkb0dUosCV00xmVoyTse";
//
//    @PostMapping("/charge")
//    public ResponseEntity<String> charge(@RequestBody PaymentRequest paymentRequest) {
//        Stripe.apiKey = stripeSecretKey;
//
//        try {
//            Map<String, Object> tokenParams = new HashMap<>();
//            Map<String, Object> cardParams = new HashMap<>();
//            cardParams.put("number", paymentRequest.getCardNumber());
//            cardParams.put("exp_month", paymentRequest.getExpMonth());
//            cardParams.put("exp_year", paymentRequest.getExpYear());
//            cardParams.put("cvc", paymentRequest.getCvc());
//
//            tokenParams.put("card", cardParams);
//
//            Token token = Token.create(tokenParams);
//
//            // Now you have the token, and you can use it for charging or other purposes
//            String tokenId = token.getId();
//
//            // Add your payment processing logic here
//
//            return ResponseEntity.ok("Payment successful. Token ID: " + tokenId);
//        } catch (StripeException e) {
//            // Handle payment failure
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Payment failed: " + e.getMessage());
//        }
//    }
//}
