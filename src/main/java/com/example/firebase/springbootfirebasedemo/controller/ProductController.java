package com.example.firebase.springbootfirebasedemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firebase.springbootfirebasedemo.ProductService;
import com.example.firebase.springbootfirebasedemo.entity.Product;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveproduct")
	public ResponseEntity<String> saveProduct(@RequestBody Product product){
		String response=null;
		try {
			response=productService.saveProduct(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/products/{name}")
	public ResponseEntity<Product> getProduct(@PathVariable String name){
		Product response=null;
		try {
			response=productService.getProductDefault(name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Product>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateproduct")
	public ResponseEntity<String> updateproduct(@RequestBody Product product){
		String response=null;
		try {
			response=productService.updateProduct(product);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteproduct/{name}")
	public ResponseEntity<String> deleteproduct(@PathVariable String name){
		String response=null;
		try {
			response=productService.deleteProduct(name);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts(){
		
		return new ResponseEntity<>(productService.getProductList(),HttpStatus.OK);
	}
}
