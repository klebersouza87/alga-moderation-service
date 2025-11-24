package com.ems.algacomments.moderation.service.api.controller;

import com.ems.algacomments.moderation.service.api.model.ModerationInput;
import com.ems.algacomments.moderation.service.api.model.ModerationOutput;
import com.ems.algacomments.moderation.service.api.service.ModerationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moderate")
@Log4j2
public class ModerationController {

    private final ModerationService moderationService;

    public ModerationController(ModerationService moderationService) {
        this.moderationService = moderationService;
    }

    @PostMapping
    public ModerationOutput moderate(@RequestBody ModerationInput moderationInput) {
        log.info("Moderating comment: {}", moderationInput);
        return moderationService.moderate(moderationInput);
    }

}
