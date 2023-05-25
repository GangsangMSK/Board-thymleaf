package idusw.springboot.boardkms.controller;


import idusw.springboot.boardkms.domain.Board;
import idusw.springboot.boardkms.domain.Member;
import idusw.springboot.boardkms.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    HttpSession session = null;

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping(value = {"/", ""})
    public String getBoardList(Model model){
        model.addAttribute("key", "value");
        return "/boards/list";
    }

    @GetMapping("/reg-form")
    public String getRegisterForm(Model model) {
        model.addAttribute("board", Board.builder().build());
        return "/boards/register";
    }

    @PostMapping("/register")
    public String registerBoard(@ModelAttribute("board") Board board, Model model, HttpServletRequest request) {
        session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        if (member != null) {
//            board.setWriterSeq(member.getSeq());
            if (boardService.registerBoard(board) > 0) {
                return "redirect:/";
            } else {
                return "redirect:/errors/404";
            }
        }
        return "redirect:/boards/reg-form";
    }
}
