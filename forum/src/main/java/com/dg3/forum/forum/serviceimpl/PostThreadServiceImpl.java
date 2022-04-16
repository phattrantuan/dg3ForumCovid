package com.dg3.forum.forum.serviceimpl;

import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.entity.Users;
import com.dg3.forum.forum.repository.PostThreadRepository;
import com.dg3.forum.forum.repository.UserstRepository;
import com.dg3.forum.forum.service.PostThreadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostThreadServiceImpl implements PostThreadService {
    @Autowired
    private UserstRepository userstRepository;

    @Autowired
    private PostThreadRepository postThreadRepository;

    /*
    * List all post
    * */
    @Override
    public List<PostThread> listAllPost (String username){
        Users users = userstRepository.getByUsername(username);
        return postThreadRepository.findAllDealer(users.getUser_pk());
    }

    @Override
    public PostThread savePosts (PostThread postThread){
        return postThreadRepository.save(postThread);
    }

    @Override
    public void updatePosts (PostThread postThread){
        String title_thread = postThread.getTitle_thread();
        String content_of_thread = postThread.getContent_of_thread();
        Long post_topic_pk = postThread.getPost_topic_pk();
        boolean enable_post_thread = postThread.isEnable_post_thread();
        Long thread_pk = postThread.getThread_pk();

        postThreadRepository.updateByPostsDealer(title_thread,content_of_thread,post_topic_pk,enable_post_thread,thread_pk);
    }

    @Override
    public void deletePosts (Long thread_pk){
        postThreadRepository.deleteByPostsDealer(thread_pk);
    }

    @Override
    public PostThread checkExistByThread_pk (Long thread_pk){
        return postThreadRepository.existByThread_pk(thread_pk);
    }
}