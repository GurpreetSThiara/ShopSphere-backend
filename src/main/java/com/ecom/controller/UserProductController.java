package com.ecom.controller;

import java.util.List;

import com.ecom.modal.Product;
import com.ecom.modal.Shop;
import com.ecom.service.ProductService;
import com.ecom.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.exception.ProductException;

@RestController
@RequestMapping("/api")
public class UserProductController {
	

	private ProductService productService;

	@Autowired
	private SellerService sellerService;
	
	public UserProductController(ProductService productService) {
		this.productService=productService;
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(@RequestParam String category,
																	  @RequestParam List<String>color, @RequestParam List<String> size, @RequestParam Integer minPrice,
																	  @RequestParam Integer maxPrice, @RequestParam Integer minDiscount, @RequestParam String sort,
																	  @RequestParam String stock, @RequestParam Integer pageNumber, @RequestParam Integer pageSize){

		
		Page<Product> res= productService.getAllProduct(category, color, size, minPrice, maxPrice, minDiscount, sort,stock,pageNumber,pageSize);
		
		System.out.println("complete products");
		return new ResponseEntity<>(res,HttpStatus.ACCEPTED);
		
	}
	

	
	@GetMapping("/products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException{
		System.out.println("product id:");
		System.out.println(productId);
		Product product=productService.findProductById(productId);
		
		return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}

	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q){
		
		List<Product> products=productService.searchProduct(q);
		
		return new ResponseEntity<List<Product>>(products,HttpStatus.OK);
		
	}

	@GetMapping("/shops")
	public ResponseEntity<List<Shop>> getAllShopsHandler(@RequestParam int pageNumber, @RequestParam int pageSize){

		List<Shop> shops=sellerService.getAllShops(  pageNumber , pageSize);
		System.out.println("ppppppppppppppppppppppppppppppppppppppppppppppppppppp");
		System.out.println(pageNumber);

		return new ResponseEntity<List<Shop>>(shops,HttpStatus.OK);

	}
}
