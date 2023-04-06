package iducs.springboot.boardthymeleaf.controller;

import iducs.springboot.boardthymeleaf.domain.Memo;
import iducs.springboot.boardthymeleaf.service.MemoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("/api")
public class MemoController {
    MemoService memoService;
    public MemoController(MemoService memoService) { // 생성자 주입 (Constructor Injection)
        this.memoService = memoService;
    }

    @GetMapping("/init")
    public String initialize(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.initailize();
        model.addAttribute("memos", result);

        return "list";
    }

    @GetMapping("/memo")
    public String getList(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.readList();
        model.addAttribute("memos", result);

        return "list";
    }
    @GetMapping("/memo/{mNo}")
    public String getList(@PathVariable("mNo") Long mNo, Model model) {
        Memo memo = new Memo();
        memo.setMNo(mNo);
        Memo result = memoService.read(memo);
        model.addAttribute("memos", result);

        return "one";
    }
}
