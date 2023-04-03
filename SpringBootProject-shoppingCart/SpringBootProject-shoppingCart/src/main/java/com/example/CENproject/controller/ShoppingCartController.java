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
    private BookService bookService;

    @PostMapping("/addToCart/{bookId}")
    public ResponseEntity<String> addToCart(@PathVariable int bookId, HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);
        Book book = bookService.getBookDetailById(bookId);

        if (book != null) {
            // Check if there are any copies of the book left
            if (book.getQuantities() > 0) {
                // Add the book to the cart
                cart.put(bookId, cart.getOrDefault(bookId, 0) + 1);

                // Update the session with the new cart
                session.setAttribute("cart", cart);

                // Update the book quantity in the server
                book.setQuantities(book.getQuantities() - 1);
                bookService.saveDetails(book);

                return ResponseEntity.ok("Book with ID:" + bookId + " has been added to cart");
            } else {
                return ResponseEntity.badRequest().body("Book with ID:" + bookId + " is out of stock");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/deleteFromCart/{bookId}")
    public ResponseEntity<String> removeFromCart(@PathVariable int bookId, HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);
        Book book = bookService.getBookDetailById(bookId);

        if (book != null) {
            // Check if the book is already in the cart
            if (cart.containsKey(bookId)) {
                // Increment the book quantity in the server
                book.setQuantities(book.getQuantities() + 1);
                bookService.saveDetails(book);

                // Remove the book from the cart
                int quantity = cart.get(bookId) - 1;
                if (quantity > 0) {
                    cart.put(bookId, quantity);
                } else {
                    cart.remove(bookId);
                }

                // Update the session with the new cart
                session.setAttribute("cart", cart);

                return ResponseEntity.ok("Book with ID:" + bookId + " has been removed from cart");
            } else {
                return ResponseEntity.badRequest().body("Book with ID:" + bookId + " is not in cart");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cart")
    public ResponseEntity<Map<String, Object>> getCart(HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);
        List<Map<String, Object>> items = new ArrayList<>();
        int numBooks = 0;

        // Retrieve the books from the cart
        for (Integer bookId : cart.keySet()) {
            Book book = bookService.getBookDetailById(bookId);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("book", book);
                item.put("quantity", cart.get(bookId));
                items.add(item);
                numBooks += cart.get(bookId);
            }
        }

        // Create a map with the JSON response body
        Map<String, Object> responseBody = new HashMap<>();

        if (items.isEmpty()) {
            responseBody.put("message", "No books in cart");
        } else {
            responseBody.put("items", items);
            responseBody.put("message", "You have " + numBooks + " books in cart");
        }
        return ResponseEntity.ok(responseBody);
    }
    private Map<Integer, Integer> getCartFromSession(HttpSession session) {
        Map<Integer, Integer> cart = (Map<Integer, Integer>) session.getAttribute("cart");
        if (cart == null) {
            cart = new HashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
    @PostMapping("/cancel")
    public ResponseEntity<String> clearCart(HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);

        // Restore the quantity of each book in the cart
        for (Integer bookId : cart.keySet()) {
            Book book = bookService.getBookDetailById(bookId);
            if (book != null) {
                book.setQuantities(book.getQuantities() + cart.get(bookId));
                bookService.saveDetails(book);
            }
        }

        // Clear the shopping cart and update the session
        cart.clear();
        session.setAttribute("cart", cart);

        return ResponseEntity.ok("Shopping cart has been cleared");
    }
    @PostMapping("/confirm")
    public ResponseEntity<String> confirm(HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);

        // Clear the shopping cart and update the session
        cart.clear();
        session.setAttribute("cart", cart);

        return ResponseEntity.ok("Order confirmed. Shopping cart has been cleared");
    }
    @GetMapping("/checkout")
    public ResponseEntity<Map<String, Object>> checkout(HttpSession session) {
        Map<Integer, Integer> cart = getCartFromSession(session);
        List<Map<String, Object>> items = new ArrayList<>();
        double total = 0;
        int numBooks=0;

        // Retrieve the books from the cart and calculate the total amount to pay
        for (Integer bookId : cart.keySet()) {
            Book book = bookService.getBookDetailById(bookId);
            if (book != null) {
                Map<String, Object> item = new HashMap<>();
                item.put("book", book);
                item.put("quantity", cart.get(bookId));
                items.add(item);
                total += book.getPrice() * cart.get(bookId);
                numBooks += cart.get(bookId);
            }
        }


        // Create a map with the JSON response body
        Map<String, Object> responseBody = new HashMap<>();

        if (items.isEmpty()) {
            responseBody.put("message", "No books in cart");
        } else {
            responseBody.put("items", items);
            responseBody.put("numBooks", numBooks);
            responseBody.put("totalAmount", total);
            responseBody.put("message", "You have " + numBooks + " books in cart, the total amount to pay is $" + total);
        }

        // Create a JSON response entity with the response body and HTTP status code
        return ResponseEntity.ok(responseBody);
    }


}
