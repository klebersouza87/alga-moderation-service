package com.ems.algacomments.moderation.service.api.service;

import com.ems.algacomments.moderation.service.api.model.ModerationInput;
import com.ems.algacomments.moderation.service.api.model.ModerationOutput;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Log4j2
@Service
public class ModerationService {

    private static final List<String> WORDS_NOT_ALLOWED = List.of("ódio", "xingamento", "spam", "advertisement", "offensive", "drug");
    private static final String REASON = "Comment contains allowed words.";
    private static final String BLOCKED_REASON = "Comment contains not allowed words";

    public ModerationOutput moderate(ModerationInput moderationInput) {
        if (WORDS_NOT_ALLOWED.stream().anyMatch(word -> moderationInput.getText().toLowerCase().contains(word))) {
            log.error(BLOCKED_REASON + ": {}", moderationInput.getText());
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, BLOCKED_REASON);
        }
        log.info("Comment approved. Comment: {}, ID: {}", moderationInput.getText(), moderationInput.getCommentId());
        return ModerationOutput.builder().approved(true).reason(REASON).build();
    }

}
