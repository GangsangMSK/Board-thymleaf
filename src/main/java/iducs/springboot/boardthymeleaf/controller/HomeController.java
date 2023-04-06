package iducs.springboot.boardthymeleaf.controller;

import iducs.springboot.boardthymeleaf.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/")
    public String goMemo() {

        return "index";
    }
}
