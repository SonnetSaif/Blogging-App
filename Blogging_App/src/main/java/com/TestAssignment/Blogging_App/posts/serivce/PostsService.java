package com.TestAssignment.Blogging_App.posts.serivce;


import com.TestAssignment.Blogging_App.posts.dto.PostsRequestDTO;
import com.TestAssignment.Blogging_App.posts.dto.PostsResponseDTO;
import com.TestAssignment.Blogging_App.posts.entity.PostsEntity;
import com.TestAssignment.Blogging_App.posts.repository.PostsRepository;
import com.TestAssignment.Blogging_App.utils.ExceptionHandlerUtil;
import com.TestAssignment.Blogging_App.utils.Messages;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostsService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostsRepository postsRepository;

    public List<PostsEntity> getAllPosts() throws ExceptionHandlerUtil {
        List<PostsEntity> allList = postsRepository.findAll();

        if (allList == null || allList.isEmpty())
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "data not found");
        return allList;
    }

    public PostsEntity getPostByOid(String oid) throws ExceptionHandlerUtil {
        PostsEntity entity = postsRepository.findByOid(oid);

        if (entity == null)
            throw new ExceptionHandlerUtil(HttpStatus.NOT_FOUND, "data not found");
        return entity;
    }
    
    public PostsResponseDTO savePost(PostsRequestDTO requestDTO) throws ExceptionHandlerUtil {
        PostsEntity entity = new PostsEntity();
        BeanUtils.copyProperties(requestDTO, entity);
        entity.setCreatedOn(new Timestamp(new Date().getTime()));

        entity = postsRepository.save(entity);

        if (entity.getOid() == null)
            throw new ExceptionHandlerUtil(HttpStatus.INTERNAL_SERVER_ERROR, "data not saved");

        return PostsResponseDTO.builder()
                .oid(entity.getOid())
                .message("Data saved successfully")
                .build();
    }

    public PostsResponseDTO updatePost(PostsRequestDTO requestDTO, String oid) throws ExceptionHandlerUtil {
        PostsEntity entity = getPostByOid(oid);

        BeanUtils.copyProperties(requestDTO, entity, "oid");
        entity.setUpdatedOn(new Timestamp(new Date().getTime()));

        entity = this.postsRepository.save(entity);

        return PostsResponseDTO.builder()
                .oid(entity.getOid())
                .message("Data updated successfully")
                .build();
    }

    public ResponseEntity<Map<String, String>> deletePost(String oid) throws ExceptionHandlerUtil {
        PostsEntity entity = getPostByOid(oid);

        postsRepository.deleteById(entity.getOid());
        log.info("Removed Office information for: {}", oid);
        Map<String, String> response = new HashMap<String, String>();
        response.put("userMessage", Messages.OFFICE_REMOVED);
        return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);

    }
}
