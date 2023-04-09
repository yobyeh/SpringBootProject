package com.example.CENproject.controller;

import com.example.CENproject.entity.Book;
import com.example.CENproject.Service.BookService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ShoppingCartController {
 
     @Autowired
    private ShoppingCartRepo shoppingCartRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private ShoppingKartService shoppingKartService;
    @Autowired
    private BookService bookService;

    @Autowired
    public ShoppingCartController(final ShoppingCartRepo shoppingCartRepo){
        this.shoppingCartRepo = shoppingCartRepo;
    };
    //adding to userid cart
    @PostMapping("cart/add/{userId}/{bookId}")
    public ResponseEntity<Map<String, Object>> addItemToCart(@PathVariable long userId, @PathVariable int bookId) {

        // Find the user by their id
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User not found"));
        }

        // Find the book by its id
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Book not found"));
        }

        // Check if the item is already in the cart
        Optional<ShoppingCart> cartItem = shoppingCartRepo.findByUserIdAndBookId(userId, bookId);
        if (cartItem.isPresent()) {
            // Item already exists, update the number of books
            ShoppingCart existingItem = cartItem.get();
            shoppingCartRepo.save(existingItem);
        } else {
            // Item not in cart, create a new item
            ShoppingCart newItem = new ShoppingCart();
            Optional<Book> tempbook;
            tempbook = bookRepo.findById(bookId);
            newItem.setUserId(userId);
            newItem.setBookId(tempbook.get().getId());
            newItem.setPrice(tempbook.get().getPrice());
            newItem.setUserName(user.get().getUsername());
            shoppingCartRepo.save(newItem);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Item added to cart"));
    }

    @GetMapping("/cart/{userId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable("userId") long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User not found"));
        }

        List<Map<String, Object>> items = new ArrayList<>();



        List<ShoppingCart> cartItems = shoppingCartRepo.findByUserId(userId);
        for (ShoppingCart cartItem : cartItems) {
            Book book = bookRepo.findById(cartItem.getBookId()).orElse(null);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("book", book);
                items.add(item);

            }
        }

        Map<String, Object> responseBody = new HashMap<>();

        if (items.isEmpty()) {
            responseBody.put("message", "No books in cart");
        } else {

            responseBody.put("items", items);
            responseBody.put("Hello", user.get().getUsername());
        }

        return ResponseEntity.ok(responseBody);
    }

    //showing cart by ID
    @GetMapping("/cart/total/{userId}")
    public ResponseEntity<Map<String, Object>> getCartTotal(@PathVariable("userId") long userId) {
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User not found"));
        }

        List<Map<String, Object>> items = new ArrayList<>();
        double totalPrice = 0.0;


        List<ShoppingCart> cartItems = shoppingCartRepo.findByUserId(userId);
        for (ShoppingCart cartItem : cartItems) {
            Book book = bookRepo.findById(cartItem.getBookId()).orElse(null);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("book", book);
                item.put("price", book.getPrice() );
                //item.put("amount",cartItem.getAmount());
                items.add(item);

                totalPrice += book.getPrice() ;
            }
        }

        Map<String, Object> responseBody = new HashMap<>();

        if (items.isEmpty()) {
            responseBody.put("message", "No books in cart");
        } else {

            responseBody.put("items", items);
            responseBody.put("Hello", user.get().getUsername());
            responseBody.put("totalPrice", totalPrice);
        }

        return ResponseEntity.ok(responseBody);
    }


    @PostMapping("cart/del/{userId}/{bookId}")
    public ResponseEntity<Map<String, Object>> delItemToCart(@PathVariable long userId, @PathVariable int bookId) {
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User not found"));
        }
        Optional<ShoppingCart> bookcheck = shoppingCartRepo.findByBookId(bookId);
        if (!bookcheck.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Book not found in Cart"));
        }
        List<Map<String, Object>> items = new ArrayList<>();
        List<ShoppingCart> cartItems = shoppingCartRepo.findByUserId(userId);
        for (ShoppingCart cartItem : cartItems) {

            if (cartItem.getBookId() == bookId) {
                shoppingCartRepo.delete(cartItem);
            }
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Item deleted cart"));
    }






}
