package com.somos_sansa.sansa.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.somos_sansa.sansa.exception.SanSaException;
import com.somos_sansa.sansa.models.entities.Branch;
import com.somos_sansa.sansa.models.entities.Topic;
import com.somos_sansa.sansa.models.entities.User;
import com.somos_sansa.sansa.repositories.TopicRepository;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserService userService;
    private final BranchService branchService;

    public TopicService(TopicRepository topicRepository, UserService userService, BranchService branchService) {
        this.topicRepository = topicRepository;
        this.userService = userService;
        this.branchService = branchService;
    }

    public List<Topic> getAllTopics() throws SanSaException {
        List<Topic> topicList = topicRepository.findAll();
        if (topicList == null || topicList.size() == 0) {
            throw new SanSaException("Todavía no hay ningún tema creado");
        }
        return topicList;
    }

    public List<Topic> getTopicsByBranchId(int branchId) throws SanSaException {
        List<Topic> topicList = topicRepository.findByBranchId(branchId);
        if (topicList == null || topicList.size() == 0) {
            throw new SanSaException("No hay temas en esta sección", HttpStatus.NOT_FOUND);
        }
        return topicList;
    }

    public void addNewTopic(Topic topic) throws SanSaException {
        if (topic.getTitle() == null || topic.getTitle().isEmpty()) {
            throw new SanSaException("El título del tema no puede estar vacío.", HttpStatus.BAD_REQUEST);
        }

        User user = topic.getUser();
        if (user == null || !user.isIdSet()) {
            throw new SanSaException("Usuario no especificado.", HttpStatus.BAD_REQUEST);
        }

        User existingUser = userService.getUserById(user.getId());
        if (existingUser == null) {
            throw new SanSaException("Usuario no encontrado.", HttpStatus.NOT_FOUND);
        }
        topic.setUser(existingUser);

        Branch branch = topic.getBranch();
        if (branch == null) {
            throw new SanSaException("Sección no especificada.", HttpStatus.BAD_REQUEST);
        }

        Branch existingBranch = branchService.getBranchById(branch.getId());
        if (existingBranch == null) {
            throw new SanSaException("Sección no encontrada.", HttpStatus.NOT_FOUND);
        }
        topic.setBranch(existingBranch);

        topicRepository.save(topic);
    }

    public Topic getTopicById(int topicId) throws SanSaException {
        Topic topic = topicRepository.findById(topicId).orElse(null);
        if (topic == null) {
            throw new SanSaException("Tema no encontrado.", HttpStatus.NOT_FOUND);
        }
        return topic;
    }

}
