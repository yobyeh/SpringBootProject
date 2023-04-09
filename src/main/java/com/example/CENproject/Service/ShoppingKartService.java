package com.example.CENproject.Service;

import com.example.CENproject.entity.Book;
import com.example.CENproject.entity.ShoppingCart;
import com.example.CENproject.repository.ShoppingCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class ShoppingKartService {
    @Autowired
    private ShoppingCartRepo shoppingCartRepo;

    //public ShoppingCart GetKartByName(String userName){return shoppingCartRepo.findBy(userName);};

    public ShoppingCart saveDetails(ShoppingCart shoppingCart){
        return shoppingCartRepo.save(shoppingCart);
    }

}
