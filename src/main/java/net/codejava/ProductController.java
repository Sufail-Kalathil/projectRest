package net.codejava;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Product")
public class ProductController {

	@Autowired
	private ProductService service;
	@Autowired
	ProductRepository repo;
	
	@GetMapping("/list")
	public List<Product> list() {
		return repo.findAll();
	}
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Product>  get(@PathVariable Integer id) throws ResolutionException {
	
		try {
			Product product = service.get(id);
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
			
		}
//		return service.get(id);
	}
	
	@PostMapping("/add")
	public Product add(@RequestBody Product product) {
		System.out.println("Adding Product");
		return repo.save(product);
	}
	
	
}
