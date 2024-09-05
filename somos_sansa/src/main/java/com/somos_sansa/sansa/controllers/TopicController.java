package com.somos_sansa.sansa.controllers;
import static com.somos_sansa.sansa.config.security.ConstantsSecurity.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(GET_ALL_TOPICS)
    public List<TopicDTO> getAllBranches () throws SanSaException {
        List<Topic> topics = topicService.getAllTopics();
        List<TopicDTO> topicsDTO = topics.stream().map(EntityToDTOMapper::convertToTopicDTO)
                .collect(Collectors.toList());
        return topicsDTO;
    }

    @PostMapping(ADD_NEW_TOPIC)
    public ResponseEntity<TopicDTO> addNeWTopic(@RequestBody Topic topic)
            throws SanSaException {
        topicService.addNewTopic(topic);
        TopicDTO resultDTO = EntityToDTOMapper.convertToTopicDTO(topic);
        return new ResponseEntity<>(resultDTO, HttpStatus.CREATED);
    }
}
