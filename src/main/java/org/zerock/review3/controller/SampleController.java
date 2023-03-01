package org.zerock.review3.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.review3.dto.TodoDTO;
import sun.tools.jinfo.JInfo;

import java.time.LocalDate;

@Controller  //해당 클래스가 스프링 MVC에서 컨트롤러 역할을 한다는 것을 의미, 스프링의 빈으로 처리
@Log4j2
public class SampleController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello...........");
    }

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1...........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name,  //@RequestParam은 말그대로 기본값을 지정할 수 있습니다.
                    @RequestParam(name = "age", defaultValue = "20")int age){
        log.info("ex2..........");
        log.info("name: " + name);
        log.info("age: " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3.........");
        log.info("dueDate: " + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){ //서블릿 방식에서는 request.setAttribute를 이용해서 데이터를 담아 JSP까지 전달했지만 스프링에서는 Model이라는 객체를 이용해서 처리할 수 있습니다.

        log.info("--------------------");
        model.addAttribute("message", "Hello world"); //내부적으로는 HttpServletRequest의 setAttribute와 동일한 동작을 수행
    }

    /*
    @GetMapping("/ex4_1")
    public void ex4Extra(TodoDTO todoDTO, Model model){
        log.info(todoDTO); //원래라면 위에처럼 model.addArrtibute를 이용해야 jsp에서 ${}를 이용할 수 있는데
                           // getter/setter을 이용하는 Java Beans의 형식인 경우에는 자동으로 화면까지 객체를 전달해준다.
    }
    */

    @GetMapping("/ex4_1")
    public void ex4Extra(@ModelAttribute("dto") TodoDTO todoDTO, Model model){ // 자동생성된 변수명 todoDTO외에 다른 이름을 사용하고 싶다면 @ModelAttribute를 이용하여 dto로 사용가능
        log.info(todoDTO);
    }

    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes){ //Model과 마찬가지로 파라미터로 추가해주기만 하면 자동으로 생성되는 방식

        redirectAttributes.addAttribute("name", "ABC"); //이건 name=ABC라는 쿼리스트링으로 들어간다.
        redirectAttributes.addFlashAttribute("result", "success"); //일회성이다

        return "redirect:/ex6";

    }

    @GetMapping("/ex6")
    public void ex6(){

    }

    //void나 문자열을 화면이 따로 있는경우 사용하고 JSON을 이용할때는 객체나 ResponseEntity를 사용합니다

    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
        log.info("p1.........."+p1);
        log.info("p2.........."+p2);
    }
}
