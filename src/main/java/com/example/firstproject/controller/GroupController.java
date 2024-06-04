/*package com.example.firstproject.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/groups/{groupId}")
    public String show(@PathVariable Long groupId, Model model){
        //해당 메서드 작성
        GroupDto groupDto = groupService.getGroup(groupId);
        List<TeamDto> teamDtos = teamService.getTeamsOnGroup(groupId);
        model.addAttribute("groupname", groupDto.getName());
        model.addAttribute("teamDtoList", teamDtos);
        return "groups/show";
    }
}
*/