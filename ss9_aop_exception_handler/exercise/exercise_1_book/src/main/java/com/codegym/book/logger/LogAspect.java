package com.codegym.book.logger;

import com.codegym.book.model.Book;
import com.codegym.book.services.IBookService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
@Aspect
public class LogAspect {
    @Autowired
    IBookService bookService;

    @AfterReturning("execution(* com.codegym.book.controller.BookController.*(..))")
    public void afterReturningMethod(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().getName();
        if (method.equals("borrowBook")) {
            Object[] objects = joinPoint.getArgs();
            Integer id = (Integer) objects[0];

            String log = "Time: " + LocalDateTime.now() + " " + method + " success book: " + bookService.findById(id).getName();
            System.out.println(log);
        } else if (method.equals("restoreBook")) {
            Object[] objects = joinPoint.getArgs();
            Long id = (Long) objects[0];

            String log = "Time: " + LocalDateTime.now() + " " + method + " success id book: " + id;
            System.out.println(log);
//        }

        } else {
            String log = "Time: " + LocalDateTime.now() + " " + method;
            System.out.println(log);
        }
    }
}
