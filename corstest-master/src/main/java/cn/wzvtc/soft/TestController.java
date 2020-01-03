package cn.wzvtc.soft;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://127.0.0.1:5500")
@RestController("/")
public class TestController {

    @Autowired
    LvliRepository lvliRepository;

    @RequestMapping(value="/addlvli")
     public  void  addivli(String lvli){
        Lvli lvli1=new Lvli("16",lvli);

        this.lvliRepository.save(lvli1);
    }

    @RequestMapping(value = "/lvlilist")
    public List<Lvli> getlvlilist(){
        return this.lvliRepository.findAll();
    }


    @RequestMapping(value="/userinfo")
    public Map<String,String> userinfo(HttpServletRequest httpServletRequest){
        Map<String,String> resultMap=new HashMap<>();
        if(httpServletRequest.getSession().getAttribute("loginnumber")!=null) {
            resultMap.put("myname", (String) httpServletRequest.getSession(true).getAttribute("loginnumber"));
            resultMap.put("mynumber", (String) httpServletRequest.getSession(true).getAttribute("username"));
        }
        return resultMap;
    }

    @RequestMapping(value="/login")
    public Map<String,String> login(@RequestBody Map<String,String> map,HttpServletRequest httpServletRequest) {
        String password=map.get("password");
        String number=map.get("number");
        Map<String,String> resultMap=new HashMap<>();
        if (number!=null && number.equals(password)) {
            httpServletRequest.getSession().setAttribute("loginnumber",number);
            httpServletRequest.getSession().setAttribute("username",number);
            resultMap.put("result", "success");
        }
        return resultMap;
    }
    @RequestMapping(value="/logout")
    public void login(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("loginnumber");
    }

}