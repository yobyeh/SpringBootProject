package com.example.CENproject.controller;

import com.example.CENproject.entity.Book;
import com.example.CENproject.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.example.CENproject.entity.User;
import com.example.CENproject.entity.ShoppingCart;
import com.example.CENproject.repository.ShoppingCartRepo;
import com.example.CENproject.repository.UserRepo;
import com.example.CENproject.repository.BookRepo;
import com.example.CENproject.Service.ShoppingKartService;

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

        // check if user exist
        Optional<User> user = userRepo.findById(userId);
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with ID " + userId + " not found"));
        }

        // Check if book exist
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Book with ID " + bookId + " not found"));
        }
        if(book.isPresent()&&book.get().getQty()==0){
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Book with ID " + bookId + " is out of stock"));
        }

        // Check if a shopping-cart with that userId and bookId is already on cart
        Optional<ShoppingCart> cartItem = shoppingCartRepo.findByUserIdAndBookId(userId, bookId);
        if (cartItem.isPresent()) {
            // If book already is in cart, update the quantity the ShoppingCart item
            ShoppingCart existingItem = cartItem.get();
            existingItem.setAmount(existingItem.getAmount() + 1);


            //update quantity of book available to purchase database
            Optional<Book> tempbook = bookRepo.findById(bookId);;
            tempbook.get().setQty(tempbook.get().getQty() - 1);
            bookRepo.save(tempbook.get());
            shoppingCartRepo.save(existingItem);
        } else {
            // add new shopping-cart object to the database
            ShoppingCart newItem = new ShoppingCart();
            Optional<Book> tempbook = bookRepo.findById(bookId);;

            //update quantity of book available to purchase database
            tempbook.get().setQty(tempbook.get().getQty() - 1);
            bookRepo.save(tempbook.get());
            //add item to shopping cart
            newItem.setUserId(userId);
            newItem.setBookId(tempbook.get().getId());
            newItem.setPrice(tempbook.get().getPrice());
            newItem.setUserName(user.get().getUsername());
            newItem.setAmount(1);
            shoppingCartRepo.save(newItem);
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Book with ID " + bookId+ " added to "+ user.get().getUsername() +"'s cart"));
    }
    //show all shopping-cart objects with that userId
    @GetMapping("/cart/{userId}")
    public ResponseEntity<Map<String, Object>> getCart(@PathVariable("userId") long userId) {
        Optional<User> user = userRepo.findById(userId);
        // check if user exist
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with ID " + userId + " not found"));
        }

        List<Map<String, Object>> items = new ArrayList<>();
        List<ShoppingCart> cartItems = shoppingCartRepo.findByUserId(userId);
        //add all items in shopping-cart database with that userID
        for (ShoppingCart cartItem : cartItems) {
            Book book = bookRepo.findById(cartItem.getBookId()).orElse(null);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("book", book);
                item.put("quantity", cartItem.getAmount());
                items.add(item);

            }
        }

        Map<String, Object> responseBody = new HashMap<>();
        //if nothing was added that userID shopping cart is empty
        if (items.isEmpty()) {
            responseBody.put("message","User with ID " + userId + " don't have any books in his cart");
        } else {

            responseBody.put("items", items);
            responseBody.put("Hello", user.get().getUsername());
        }

        return ResponseEntity.ok(responseBody);
    }

    //showing total to pay cart by ID
    @GetMapping("/cart/total/{userId}")
    public ResponseEntity<Map<String, Object>> getCartTotal(@PathVariable("userId") long userId) {
        Optional<User> user = userRepo.findById(userId);
        // check if user exist
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with ID " + userId + " not found"));
        }

        List<Map<String, Object>> items = new ArrayList<>();
        double totalPrice = 0.0;

        //add all items in shopping-cart database with that userID
        List<ShoppingCart> cartItems = shoppingCartRepo.findByUserId(userId);
        for (ShoppingCart cartItem : cartItems) {
            Book book = bookRepo.findById(cartItem.getBookId()).orElse(null);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("Name", book.getBookName());
                item.put("price", book.getPrice() );
                //item.put("amount",cartItem.getAmount());
                items.add(item);

                totalPrice += (book.getPrice() * cartItem.getAmount());
            }
        }

        Map<String, Object> responseBody = new HashMap<>();
        //if nothing was added that userID shopping cart is empty
        if (items.isEmpty()) {
            responseBody.put("message", "User with ID " + userId + " not found");
        } else {


            responseBody.put("Hello", user.get().getUsername());
            responseBody.put("Total to Pay", "$" + totalPrice);
        }
        //linkedHashMap implemented to ensure correct output, greeting first
        LinkedHashMap<String, Object> orderedResponseBody = new LinkedHashMap<>();
        orderedResponseBody.put("Hello", responseBody.get("Hello"));
        orderedResponseBody.put("Total to Pay", responseBody.get("Total to Pay"));

        return ResponseEntity.ok(orderedResponseBody);
    }

    //Deleting shopping-cart objects using userID and bookID
    @DeleteMapping("cart/del/{userId}/{bookId}")
    public ResponseEntity<Map<String, Object>> delItemToCart(@PathVariable long userId, @PathVariable int bookId) {
        Optional<User> user = userRepo.findById(userId);
        Optional<Book> tempbook = bookRepo.findById(bookId);
        Optional<ShoppingCart> bookcheck = shoppingCartRepo.findByBookId(bookId);
        // check if user exist
        if (!user.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "User with ID " + userId + " not found"));
        }
        // check if book exist
        if (!bookcheck.isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Book with ID " + bookId + " not found"));
        }
        ShoppingCart cartItem = bookcheck.get();
        //if there is only one item delete else reduce amount
        if (cartItem.getAmount() == 1) {
            shoppingCartRepo.delete(cartItem);

            //update quantity of book available to purchase database
            tempbook.get().setQty(tempbook.get().getQty() + 1);
            bookRepo.save(tempbook.get());
        } else {
            cartItem.setAmount(cartItem.getAmount() - 1);
            shoppingCartRepo.save(cartItem);

            //update quantity of book available to purchase database
            tempbook.get().setQty(tempbook.get().getQty() + 1);
            bookRepo.save(tempbook.get());
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "Item " + bookId+ " deleted from cart"));
    }






}
