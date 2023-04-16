package com.example.CENproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.CENproject.entity.User;
import com.example.CENproject.repository.UserRepo;
import com.example.CENproject.entity.CreditCard;
import com.example.CENproject.repository.CreditCardRepo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@RestController
public class CreditCardApiController {

    @Autowired
    private CreditCardRepo creditCardRepo;
    @Autowired
    private UserRepo userRepo;


    @GetMapping(value = "/creditCards")
    public List<User> getCreditCards() {
        return userRepo.findAll();
    }

    @GetMapping("/creditCards/{id}")
    public Optional<CreditCard> getCreditCardById(@PathVariable("id") Integer id) {
        return this.creditCardRepo.findById(id);
    }


    @PostMapping(value = "/createCreditCard")
    public String saveCreditCard (@RequestBody CreditCard creditCard) {
        creditCardRepo.save(creditCard);
        return "saved";
    }

    // @PutMapping(value = "/updateCreditCard/{id}")
    // public String updateCreditCard(@PathVariable long id, @RequestBody CreditCard creditCard) {
    //     User updatUser = userRepo.findById(id).get();
    //     updatUser.setPassword(user.getPassword());
    //     updatUser.setFirstName(user.getFirstName());
    //     updatUser.setLastname(user.getLastname());
    //     updatUser.setHomeAddress(user.getHomeAddress());
    //     userRepo.save(updatUser);
    //     return "Updated";
    // }

    @DeleteMapping(value = "/deleteCreditCard/{id}")
    public String deleteCreditCard(@PathVariable Integer id){
        CreditCard deleteCreditCard = creditCardRepo.findById(id).get();
        creditCardRepo.delete(deleteCreditCard);
        return "delet credit card id:" + id;
    }

}
