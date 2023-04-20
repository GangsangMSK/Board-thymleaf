package idusw.springboot.boardkms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    /*
    Field Injection (필드 주입) : Spring Framework 에게 MemoService형 객체를 주입
    @Autowired
    MemoService memoService;
     */
    // localhost:8080/ 요청이 들어오면 getAdmin() 메소드를 호출하여 처리하고, /admin/index view에게 전달
    @GetMapping("/")
    public String getAdmin() {return "/admin/index";}

}
