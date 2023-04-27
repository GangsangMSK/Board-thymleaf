package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/members")
public class MemberController {

    private HttpSession session;
    MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "/members/login";
    }

    @PostMapping("/login")
    public String loginMember(@ModelAttribute("member") Member member, Model model, HttpServletRequest request) {
        // @ModelAttribute : 요청으로 전달된 객체 (폼에서 입력한 정보를 갖는)
        Member result = null;
        if((result = memberService.login(member)) != null) {

            session = request.getSession();
            session.setAttribute("member", result);
            return "redirect:/";
        } else {
            System.out.println(result);
            return "redirect:/members/login";
        }
    }

    @GetMapping("/logout")
    public String logoutMember() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/")
    public String getMemberList(Model model) {
        List<Member> memberList = memberService.readList();
        if(memberList != null){
            model.addAttribute("memberList", memberList);
            return "/members/list";
        } else {
            model.addAttribute("error message", "목록 조회 실패");
            return  "/erroe/message";
        }
    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        // Memeber 형의 객체를  생성하고,
        model.addAttribute("member", Member.builder().build());
        return "/members/register";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member member, Model model) {
        if (memberService.create(member) > 0) {
            return "redirect:/members/login";
        } else {
            return "redirect:/members/register";
        }
    }

    @GetMapping("/forgot")
    public String getForgotForm() {
        return "/members/forgot";
    }



}
