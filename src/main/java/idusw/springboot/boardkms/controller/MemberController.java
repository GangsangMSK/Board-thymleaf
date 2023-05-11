package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.domain.PageRequestDTO;
import idusw.springboot.boardkms.domain.PageResultDTO;
import idusw.springboot.boardkms.entity.MemberEntity;
import idusw.springboot.boardkms.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return  "/errors/message";
        }
    }
    @GetMapping(value = {"", "/{page}/{size}"}) // /?page=&size= 사용시 RequestParam으로 받아야 함
    public String getMemberListByPage(@PathVariable("page")int page, @PathVariable("size") int size, Model model){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(page)
                .size(size)
                .build();
        PageResultDTO<Member, MemberEntity> resultDTO = memberService.getList(pageRequestDTO);
        System.out.println(resultDTO);
        List<Member> result = resultDTO.getDtoList();
        if(result != null){
            model.addAttribute("memberList", result);
            model.addAttribute("pageResult", resultDTO);
            return "/members/list";
        } else {
            return  "/errors/404";
        }
    }
    @GetMapping("/{seq}")
    public String getMember(@PathVariable("seq") Long seq, Model model) {
        Member result = new Member(); // 반환
        Member m = new Member(); // 매개변수로 전달
        m.setSeq(seq);
        result = memberService.read(m);
        // MemberService가 MemberRepository에게 전달
        // MemberRepository는 JpaRepository 인터페이스의 구현체를 활용할 수 있음
        model.addAttribute("member", result);
        return "/members/detail";
    }

    @PutMapping("/{seq}")
    public String updateMember(@ModelAttribute("member") Member member, Model model) { // 수정 처리 -> service -> repository -> service -> controller
        if(memberService.update(member) > 0 ) {
            session.setAttribute("mb", member);
            return "redirect:/";
        }
        else
            return "/errors/404";
    }
    @DeleteMapping("/{seq}")
    public String deleteMember(@ModelAttribute("member") Member member) { // 삭제 처리 -> service -> repository -> service -> controller
        if(memberService.delete(member) > 0) {
            session.invalidate();
            return "redirect:/";
        }
        else
            return "/errors/404";
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
