package iducs.springboot.boardkms.controller;

import iducs.springboot.boardkms.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class HomeController {

    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/")
    public String goMemo() {return "admin/index";}

    @GetMapping("/buttons")
    public String goButtons() {return "admin/buttons";}

    @GetMapping("/cards")
    public String goCards() {return "admin/cards";}
}
