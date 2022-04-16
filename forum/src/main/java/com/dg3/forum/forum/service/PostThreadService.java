package com.dg3.forum.forum.service;

import com.dg3.forum.forum.entity.PostThread;
import java.util.List;

public interface PostThreadService {
    /*
    * List posts through username
    * Request is String username
    * Respone is List of dealer posts
    * */
    List<PostThread> listAllPost (String username);

    /*
    * Save information posts
    * Request is Object PostThread postThread
    * Respone is information is saved
    * */
    PostThread savePosts(PostThread postThread);

    /*
    * Update information posts
    * Request is Object PostThread postThread
    * Respone is information is update
    * */
    void updatePosts(PostThread postThread);

    /*
    * Delete information posts
    * Request is Long threak_pk (primary key of table PostDealer)
    * Respone is information is delete from database
    * */
    void deletePosts(Long thread_pk);

    /*
    *   Check the information of the post exists or not through thread_pk
    * */
    PostThread checkExistByThread_pk(Long thread_pk);
}