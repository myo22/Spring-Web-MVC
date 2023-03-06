package org.zerock.review3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.review3.dto.PageRequestDTO;
import org.zerock.review3.dto.TodoDTO;
import org.zerock.review3.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo") //특정한 경로의 요청(Request)을 지정하기 위해서 사용합니다. 클래스에도 사용가능하고 메소드에도 사용가능
// 보이는것처럼 여러개의 컨트롤러를 하나의 클래스로 묶을 수 있다. list와 register를 원래 각각 작성해야하지만 모아놓고 각각 메소드 단위로 설계할 수 있다.
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    /*
    @RequestMapping("/list")
    public void list(Model model){
        log.info("todo list.........");
        List<TodoDTO> dtoList = todoService.getAll(); 한번 같은 원리로 해봤는데 효율이 안나오는게 어차피 TodoService에서 return을 매핑하고 dto로 보내주기때문에
        model.addAttribute("dtoList", dtoList);       두번 반복해서 낭비할 이유가 없다.

        model.addAttribute("dtoList", todoService.getAll()); //model에 dtoList라는 이름으로 목록 데이터를 담았다.(JSP에서는 JSTL을 이용해서 목록을 출렵합니다.)
                                                                         //list.jsp처럼 view에서 dtoList를 이용해서 활용가능하다.
    }
    */

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){  //@Valid를 이용해서 잘못된 파라미터 값들이 들어오는 경우 page는 1, size는 10 으로 고정된 값으로 구성한다.
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }


    //@RequestMapping(value = "/register", method = RequestMethod.GET) //원래는 method로 GET/POST 방식을 구분해야 하지만 이젠 GetMapping @PostMapping 어노테이션 추가로 처리할 수 있다.
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register.......");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors() );
            return "redirect:/todo/register";
        }

        log.info(todoDTO); //지금 한글문제가 안되는것은 브라우저에서 서버로 데이터를 전송할 때 한글이 깨지는지 서버에서 데이터를 수집할 때 깨지는지를 먼저 알아야 합니다.
        //브라우저 에서 보내는 데이터는 개발자 도구 네트워크에서 register 항목을 이용해서 확인이 가능합니다. 여기서 문제가 없다면 서버에서 문제가 있는것이다.(web.xml에 필터 설정 추가하면 간단)

        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"})  //todo/modify?tno=xx의 경로를 처리하도록 수정.
    public void read(Long tno, Model model, PageRequestDTO pageRequestDTO){ //예상으론 여기에 파라미터로 PageRequestDTO를 넣어줌으로써 JSP에서 PageRequestDTO를 사용가능하게 보임.

        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);

        model.addAttribute("dto", todoDTO);
    }


//    @PostMapping("/remove")
//    public String remove(Long tno, RedirectAttributes redirectAttributes){
//
//        log.info("-------------remove------------------");
//        log.info("tno: " + tno);
//
//        todoService.remove(tno);
//
//        return "redirect:/todo/list";
//    }


    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes, PageRequestDTO pageRequestDTO){

        log.info("----------------remove--------------------");
        log.info("tno : " + tno);

        todoService.remove(tno);

        /*
        redirectAttributes.addAttribute("page", 1); //이건 삭제한거니까 size만 알려주고 페이지는 초기화시켜준다.
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/list";
        */

        return "redirect:/todo/list?" + pageRequestDTO.getLink(); //위에 세개 지우고 이거 한줄로도 가능하다. 부가로 이건 이제 검색조건 필터까지 추가한것.

    }

    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO, //Valid를 이용해서 필요한 내용들을 검증하고 문제가 있는 경우에는 다시 'todo/modify' 로 이동시키는 방식을 이용
                       BindingResult bindingResult,
                       PageRequestDTO pageRequestDTO,
                       RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno()); //modify로 이동할 때에는 tno파라미터가 필요하므로 사용
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);

        todoService.modify(todoDTO);

        //redirectAttributes.addAttribute("page", pageRequestDTO.getPage());
        //redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read";
    }

}
