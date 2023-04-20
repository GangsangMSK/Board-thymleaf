package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Memo;
import idusw.springboot.boardkms.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memo")
//@RequestMapping("/api")
public class MemoController {

    //생성자 주입 (Constructor DI) vs IoC(Inversion of Control) 기법 중 하나가 DI, DL ...
    MemoService memoService;
    public MemoController(MemoService memoService) { // 생성자 주입 (Constructor Injection)
        this.memoService = memoService;
    }

    @GetMapping("/init")
    public String initialize(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.initailize();
        model.addAttribute("memos", result);

        return "/memo/list";
    }

    @GetMapping("/tables")
    public String goTables() {return "/memo/tables";}

    @GetMapping("/")
    public String getList(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.readList();
        model.addAttribute("memos", result);

        return "/memo/list";
    }
    @GetMapping("/{mNo}")
    public String getList(@PathVariable("mNo") Long mNo, Model model) {
        Memo memo = new Memo();
        memo.setMNo(mNo);
        Memo result = memoService.read(memo);
        model.addAttribute("memos", result);

        return "/memo/one";
    }
}
