package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
public class MemberController {
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String getLoginForm() {
//        memberService.toString();
        return "/members/login";
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        // Memeber 형의 객체를  생성하고,
        model.addAttribute("member", Member.builder().build());
        return "/members/register";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member m, Model model) {
        if (memberService.create(m) > 0)
             return "redirect:/members/login";
        else
            return "redirect:/members/register";
    }

    @GetMapping("/forgot")
    public String getForgotForm() {
        return "/members/forgot";
    }



}
