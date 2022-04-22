package com.example.firebase.springbootfirebasedemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.firebase.springbootfirebasedemo.entity.Product;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class ProductService {

	private static final String COLLECTION_NAME = "products";

	public String saveProduct(Product product) {
		ApiFuture<WriteResult> collectionApiFuture=null;
		try {
		Firestore dbFireStore=FirestoreClient.getFirestore();
			collectionApiFuture=dbFireStore.collection(COLLECTION_NAME).document(product.getName()).set(product);
			return collectionApiFuture.get().getUpdateTime().toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Product getProductDefault(String name) {
		DocumentReference documentReference;
		ApiFuture<DocumentSnapshot> collectionApiFuture=null;
		DocumentSnapshot documentSnapshot=null;
		Product product=null;
		try {
		Firestore dbFireStore=FirestoreClient.getFirestore();
		documentReference=dbFireStore.collection(COLLECTION_NAME).document(name);
		collectionApiFuture=documentReference.get();
		documentSnapshot=collectionApiFuture.get();
		if(documentSnapshot.exists()) {
			product=documentSnapshot.toObject(Product.class);
			return product;
		}else {
			
		}
		return null;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String updateProduct(Product product) {
		ApiFuture<WriteResult> collectionApiFuture=null;
		try {
		Firestore dbFireStore=FirestoreClient.getFirestore();
			collectionApiFuture=dbFireStore.collection(COLLECTION_NAME).document(product.getName()).set(product);
			return collectionApiFuture.get().getUpdateTime().toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteProduct(String name) {
		ApiFuture<WriteResult> collectionApiFuture=null;
		try {
		Firestore dbFireStore=FirestoreClient.getFirestore();
			collectionApiFuture=dbFireStore.collection(COLLECTION_NAME).document(name).delete();
			return "Document with Product ID "+name+" has been deleted successfully";
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Product> getProductList() {
		Iterable<DocumentReference> documentReference;
		Iterator<DocumentReference> iterator;
		ApiFuture<DocumentSnapshot> collectionApiFuture=null;
		DocumentSnapshot documentSnapshot=null;
		Product product=null;
		List<Product> productList=null;
		try {
		productList=new ArrayList<Product>();
		Firestore dbFireStore=FirestoreClient.getFirestore();
		documentReference=dbFireStore.collection(COLLECTION_NAME).listDocuments();
		iterator=documentReference.iterator();
		
		
		while(iterator.hasNext()) {
			DocumentReference doc=iterator.next();
			collectionApiFuture=doc.get();
			documentSnapshot=collectionApiFuture.get();
			Product prod =documentSnapshot.toObject(Product.class); 
			productList.add(prod);
		}
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return productList;
		
	}
}
