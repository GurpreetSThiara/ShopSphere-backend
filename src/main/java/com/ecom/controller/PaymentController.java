package com.ecom.controller;

import com.ecom.exception.OrderException;
import com.ecom.exception.UserException;
import com.ecom.modal.Order;
import com.ecom.repository.OrderRepository;
import com.ecom.response.ApiResponse;
import com.ecom.response.PaymentLinkResponse;
import com.ecom.service.OrderService;
import com.ecom.service.UserService;

import com.ecom.user.domain.OrderStatus;
import com.ecom.user.domain.PaymentStatus;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.PaymentIntent;
//import com.stripe.param.PaymentIntentCreateParams;
//import com.ecom.exception.OrderException;
//import com.ecom.exception.UserException;
//import com.ecom.modal.Order;
//import com.ecom.repository.OrderRepository;
//import com.ecom.response.ApiResponse;
//import com.ecom.response.PaymentLinkResponse;
//import com.ecom.service.OrderService;
//import com.ecom.service.UserService;
//import com.ecom.user.domain.OrderStatus;
//import com.ecom.user.domain.PaymentStatus;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
@RestController
@RequestMapping("/api")
public class PaymentController {
     @Autowired
    	private OrderService orderService;
    @Autowired
	private UserService userService;
    @Autowired
	private OrderRepository orderRepository;

    @PostMapping("/payments/{orderId}")
    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId,
                                                                 @RequestHeader("Authorization")String jwt)
            throws RazorpayException, UserException, OrderException {

        Order order=orderService.findOrderById(orderId);
        try {
            // Instantiate a Razorpay client with your key ID and secret
            RazorpayClient razorpay = new RazorpayClient("rzp_test_zdKdiYJLUwgFTZ", "gsTIMDEzxeY3yi6MgFXKr7mU");

            // Create a JSON object with the payment link request parameters
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount",order.getTotalPrice());
            paymentLinkRequest.put("currency","INR");
//		      paymentLinkRequest.put("expire_by",1691097057);
//		      paymentLinkRequest.put("reference_id",order.getId().toString());


            // Create a JSON object with the customer details
            JSONObject customer = new JSONObject();
            customer.put("name",order.getUser().getFirstName()+" "+order.getUser().getLastName());
            customer.put("contact","8872269487");
//            customer.put("email",order.getUser().getEmail());
//            paymentLinkRequest.put("customer",customer);

            // Create a JSON object with the notification settings
//            JSONObject notify = new JSONObject();
//            notify.put("sms",true);
//            notify.put("email",true);
//            paymentLinkRequest.put("notify",notify);
//
//            // Set the reminder settings
//            paymentLinkRequest.put("reminder_enable",true);

            // Set the callback URL and method
            paymentLinkRequest.put("callback_url","http://localhost:8080/api/payments");
            paymentLinkRequest.put("callback_method","get");

            // Create the payment link using the paymentLink.create() method
            PaymentLink payment = razorpay.paymentLink.create(paymentLinkRequest);

            String paymentLinkId = payment.get("id");
            String paymentLinkUrl = payment.get("short_url");

            PaymentLinkResponse res=new PaymentLinkResponse(paymentLinkUrl,paymentLinkId);

            PaymentLink fetchedPayment = razorpay.paymentLink.fetch(paymentLinkId);

            order.setOrderId(fetchedPayment.get("order_id"));
            orderRepository.save(order);

            // Print the payment link ID and URL
            System.out.println(res);
            System.out.println("Payment link ID: " + paymentLinkId);
            System.out.println("Payment link URL: " + paymentLinkUrl);
            System.out.println("Order Id : "+fetchedPayment.get("order_id")+fetchedPayment);

            return new ResponseEntity<PaymentLinkResponse>(res, HttpStatus.ACCEPTED);

        } catch (RazorpayException e) {

            System.out.println("Error creating payment link: " + e.getMessage());
            throw new RazorpayException(e.getMessage());
        }


//
    }


    @GetMapping("/payments")
    public ResponseEntity<ApiResponse> redirect(@RequestParam(name="payment_id") String paymentId, @RequestParam("order_id")Long orderId) throws RazorpayException, OrderException {
        RazorpayClient razorpay = new RazorpayClient("rzp_test_zdKdiYJLUwgFTZ", "gsTIMDEzxeY3yi6MgFXKr7mU");
        Order order =orderService.findOrderById(orderId);

        try {


            Payment payment = razorpay.payments.fetch(paymentId);
            System.out.println("payment details --- "+payment+payment.get("status"));

            if(payment.get("status").equals("captured")) {
                System.out.println("payment details --- "+payment+payment.get("status"));

                order.getPaymentDetails().setPaymentId(paymentId);
                order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
                order.setOrderStatus(OrderStatus.PLACED);
//			order.setOrderItems(order.getOrderItems());
                System.out.println(order.getPaymentDetails().getStatus()+"payment status ");
                orderRepository.save(order);
            }
            ApiResponse res=new ApiResponse("your order get placed", true);
            return new ResponseEntity<ApiResponse>(res,HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("errrr payment -------- ");
            new RedirectView("");
            throw new RazorpayException(e.getMessage());
        }

    }

}
