package com.example.firstproject.controller;

import com.example.firstproject.dto.MemberForm;
import com.example.firstproject.entity.Member;
import com.example.firstproject.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {
    @Autowired
    MemberRepository memberRepository;

    @GetMapping("/members/new")
    public String newMemberForm() {
        log.info("members에서 회원가입을 누르면 members/new로 넘어감");
        return "members/new";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        log.info("위 /members/new와 같음");
        return "members/new";
    }

    @PostMapping("/join")
    public String join(MemberForm memberForm) {
        log.info("사용자가 회원등록 폼을 제출하면, " +
                "이 메서드가 호출되어 회원정보를 받아 DB에 저장 후 새 회원정보 페이지로 리다이렉트");
        log.info(memberForm.toString());

        Member member = memberForm.toEntity();
        log.info(member.toString());

        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "redirect:/members/" + saved.getId();
    }

    @GetMapping("/members/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("특정 회원의 상세정보를 보여주는 페이지를 보여준다.");
        Member member = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", member);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        log.info("모든 회원의 목록을 보여주는 페이지");

        Iterable<Member> members = memberRepository.findAll();

        model.addAttribute("members", members);
        return "members/index";
    }

    @GetMapping("/members/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        log.info("특정 회원의 정보를 수정하는 페이지");
        Member memberEntity = memberRepository.findById(id).orElse(null);
        model.addAttribute("member", memberEntity);
        return "members/edit";
    }

    @PostMapping("/members/update")
    public String update(MemberForm form) {
        log.info("회원 정보 수정 폼을 제출하면, 회원정보를 업데이트 한 후" +
                " 해당 회원의 상세정보 페이지로 리다이렉트");
        log.info(form.toString());
        Member memberEntity = form.toEntity();
        Member target = memberRepository.findById(memberEntity.getId()).orElse(null);
        //2-2. 기존 데이터 값을 갱신하기
        if (target != null) {
            memberRepository.save(memberEntity);
        }
        //3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/members/" + memberEntity.getId();
    }
    @GetMapping("/members/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        log.info("삭제 요청이 들어왔습니다!!");
        //1. 삭제할 대상 가져오기
        Member target = memberRepository.findById(id).orElse(null);
        log.info(target.toString());
        //2. 대상 엔티티 삭제하기
        if(target != null) {
            memberRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }
        //3. 결과 페이지로 리다이렉트하기
        return "redirect:/members";
    }

}
