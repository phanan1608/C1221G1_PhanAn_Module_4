package com.codegym.product_validate.controller;

import com.codegym.product_validate.dto.ProductDto;
import com.codegym.product_validate.service.IProductService;
import com.codegym.product_validate.model.Product;
import com.codegym.product_validate.utils.CustomPageable;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    IProductService productService;

    @GetMapping("/list")
    public String goToProductList(Model model, @PageableDefault(value = 5) Pageable pageable,
                                  @RequestParam Optional<String> keyword,
                                  @RequestParam Optional<String> sort,
                                  @RequestParam Optional<String> dir){
        String name = keyword.orElse("");
        String sortBy = sort.orElse("");
        String direction = dir.orElse("");
        pageable = CustomPageable.customPageable(pageable,sortBy,direction);
        model.addAttribute("name_keyword",name);
        model.addAttribute("sort",sortBy);
        model.addAttribute("dir",direction);
        model.addAttribute("products",this.productService.findAll(pageable,name));
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("productDto", new ProductDto());
        return "/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute @Validated ProductDto productDto, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes,Model model) {

        if (bindingResult.hasFieldErrors()){
            return "create";
        }
        else {
            Product product = new Product();
            BeanUtils.copyProperties(productDto,product);
            productService.save(product);
            redirectAttributes.addFlashAttribute("success","Create successfully");
            return "redirect:/product/list";
        }

    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        Product product =this.productService.findById(id);
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        model.addAttribute("productDto",productDto);
        return "/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute @Validated ProductDto productDto, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasFieldErrors()){
            return "edit";
        }
        else {
            Product product = new Product();
            BeanUtils.copyProperties(productDto,product);
            productService.save(product);
            redirectAttributes.addFlashAttribute("success","Update successfully");
            return "redirect:/product/list";
        }
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable int id, Model model) {
        model.addAttribute("product", this.productService.findById(id));
        return "/delete";
    }

    @PostMapping("/delete")
    public String delete(Product product, RedirectAttributes redirect) {
        this.productService.remove(product.getId());
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/product/list";
    }
    @GetMapping("/{id}/view")
    public String view(@PathVariable int id, Model model) {
        model.addAttribute("product", this.productService.findById(id));
        return "/view";
    }

}
