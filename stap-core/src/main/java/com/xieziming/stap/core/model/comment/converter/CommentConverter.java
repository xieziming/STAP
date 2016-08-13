package com.xieziming.stap.core.model.comment.converter;

import com.xieziming.stap.core.model.comment.dto.CommentDto;
import com.xieziming.stap.core.model.comment.pojo.Comment;
import com.xieziming.stap.core.model.user.converter.UserConverter;
import com.xieziming.stap.core.model.user.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Suny on 8/13/16.
 */
@Component
public class CommentConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserDao userDao;

    public List<CommentDto> convertAll(List<Comment> commentList){
        List<CommentDto> commentDtoList = new ArrayList<CommentDto>();
        for (Comment comment : commentList){
            commentDtoList.add(convert(comment));
        }
        return commentDtoList;
    }

    public CommentDto convert(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setUserDto(userConverter.convert(userDao.findById(comment.getUserId())));
        commentDto.setTime(comment.getTime());
        return commentDto;
    }
}
