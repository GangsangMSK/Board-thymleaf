package idusw.springboot.boardkms.controller;

import idusw.springboot.boardkms.domain.Member;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RestApiController {

    @GetMapping("/")
    public String readList() {
        String result = "";

        return result;
    }
    @GetMapping("/{id}")
    public String readMember(@PathVariable Long id, Model model) {
        String result = "read";

        return result;
    }
    @GetMapping("/register-form")
    public String getCreate(Model model) { // register-form 호출
        String result = "get";

        return result;
    }
    @PostMapping("/{id}")
    public String createMember(@RequestBody Member member, Model model) { // @RequestBody
        String result = "create";

        return result;
    }
    @PutMapping("/{id}")
    public String updateMember(@PathVariable Long id, Model model) {
        String result = "update";

        return result;
    }
    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable Long id, Model model) {
        String result = "delete";

        return result;
    }
}