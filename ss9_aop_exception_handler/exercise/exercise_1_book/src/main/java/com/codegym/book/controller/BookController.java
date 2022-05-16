package com.codegym.book.controller;

import com.codegym.book.model.Book;
import com.codegym.book.model.RentId;
import com.codegym.book.repository.IRentIdRepository;
import com.codegym.book.services.IBookService;
import com.codegym.book.services.IRentIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    IBookService bookService;

    @Autowired
    IRentIdService rentIdService;

    @GetMapping("/list")
    public String goToBookList(Model model, @PageableDefault(value = 5) Pageable pageable,
                                  @RequestParam Optional<String> keyword){
        String name = keyword.orElse("");
        model.addAttribute("bookList",this.bookService.findAll(pageable,name));
        return "index";
    }

    @GetMapping("/{id}/view")
    public String viewBook(@PathVariable int id, Model model) {
        model.addAttribute("book", this.bookService.findById(id));
        return "/view";
    }
    @PostMapping("/borrow")
    public String borrowBook(@RequestParam Integer id, Model model){
        Book bookBorrow = bookService.findById(id);
        System.err.println(bookBorrow.getQuantity());
        if (bookBorrow.getQuantity()>0){
            Integer quantity = bookBorrow.getQuantity()-1;
            bookBorrow.setQuantity(quantity);
            RentId rentId = new RentId();
            rentId.setBookId(bookBorrow);
            rentIdService.save(rentId);
            bookService.save(bookBorrow);
            model.addAttribute("bookBorrow",bookBorrow);
            model.addAttribute("rentId",rentId);
            return "borrow";
        } else {
            return "404";
        }
    }
    @PostMapping("/restore")
    public String restoreBook(@RequestParam Long rentId, Model model){

        if (rentIdService.findById(rentId)!=null){
            RentId rentId1 = rentIdService.findById(rentId);
            Book book = rentId1.getBookId();
            Integer quantity = book.getQuantity()+1;
            book.setQuantity(quantity);
            rentIdService.remove(rentId);
            bookService.save(book);
            return "redirect:/book/list";
        } else {
            return "404";
        }
    }
}
