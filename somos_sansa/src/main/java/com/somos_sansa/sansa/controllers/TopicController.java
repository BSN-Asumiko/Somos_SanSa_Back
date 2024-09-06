package com.somos_sansa.sansa.controllers;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.mapper.EntityToDTOMapper;
import com.somos_sansa.sansa.models.dto.TopicDTO;
import com.somos_sansa.sansa.models.entities.Topic;
import com.somos_sansa.sansa.services.TopicService;

@RestController
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping(GET_TOPICS_BY_BRANCH_URL)
    public List<TopicDTO> getTopicsByBranch(@PathVariable int branchId) throws SanSaException {
        List<Topic> topics = topicService.getTopicsByBranchId(branchId);
        return topics.stream()
                .map(EntityToDTOMapper::convertToTopicDTO)
                .collect(Collectors.toList());
    }

    @PostMapping(ADD_NEW_TOPIC_URL)
    public ResponseEntity<TopicDTO> addNeWTopic(@RequestBody Topic topic)
            throws SanSaException {
        topicService.addNewTopic(topic);
        TopicDTO resultDTO = EntityToDTOMapper.convertToTopicDTO(topic);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }
}
