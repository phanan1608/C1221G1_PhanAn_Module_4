package com.codegym.shopping_cart.controller;


import com.codegym.shopping_cart.model.Cart;
import com.codegym.shopping_cart.model.Product;
import com.codegym.shopping_cart.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShoppingCartController {
    @Autowired
    IProductService productService;

    @ModelAttribute("cart")
    public Cart setupCart() {
        return new Cart();
    }


    @GetMapping("/shopping-cart")
    public ModelAndView showCart(@SessionAttribute("cart") Cart cart) {
        ModelAndView modelAndView = new ModelAndView("/cart");
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteCart(@PathVariable Long id,@SessionAttribute("cart")Cart cart){
        Product product = productService.findById(id).orElse(null);
        ModelAndView modelAndView = new ModelAndView("/cart");
        cart.removeProduct(product);
        modelAndView.addObject("cart", cart);
        return modelAndView;
    }

}
