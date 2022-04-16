package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.entity.PostThread;
import com.dg3.forum.forum.service.PostThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dealer")
public class DealerControllers {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private PostThreadService postThreadService;

    /*
     * Showing a list posts
     * */
    @GetMapping("/all/{username}")
    public ResponseEntity<Message> showAllPostDealer(@PathVariable String username) {
        List<PostThread> listAllPost = postThreadService.listAllPost(username);

        return listAllPost.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Fail", "There is no list of posts by" + username, "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "List all posts dealer", listAllPost)
                );
    }

    /*
    * Create posts
    * */
    @PostMapping("/create/posts")
    public ResponseEntity<Message> createPosts(@RequestBody PostThread postThread){
        /*
        * Get the current date and time when the article was posted.
        * */
        Date dateNow = new Date();
        postThread.setTime_post_thread(dateNow);

        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Create posts successfully", postThreadService.savePosts(postThread))
        );
    }

    /*
    * Update posts
    * */
    @PutMapping("/update/posts/{thread_pk}")
    public ResponseEntity<Message> updatePosts(@RequestBody PostThread postThread, @PathVariable("thread_pk") Long thread_pk){
        postThread.setThread_pk(thread_pk);

        postThreadService.updatePosts(postThread);

        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Update posts successfully", "")
        );
    }

    /*
    * Delete posts
    * */
    @DeleteMapping("/delete/posts/{thread_pk}")
    public void deletePosts(@PathVariable("thread_pk") Long thread_pk){
        /*
        * Check exist by posts through primary key thread_pk
        * */
        if(postThreadService.checkExistByThread_pk(thread_pk) != null){
            postThreadService.deletePosts(thread_pk);

            if(postThreadService.checkExistByThread_pk(thread_pk) != null){
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "Delete posts successfully", "")
                );
            }else {
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Failed", "Delete posts unsuccessfully", "")
                );
            }
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Message("Failed", "Cannot find posts to delete", "")
            );
        }
    }

}