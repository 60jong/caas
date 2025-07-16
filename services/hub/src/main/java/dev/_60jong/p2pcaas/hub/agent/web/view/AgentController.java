package dev._60jong.p2pcaas.hub.agent.web.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/agent")
public class AgentController {

    @GetMapping("/register/view")
    public String getAgentRegisterPage() {
        return "agent/register";
    }
}
