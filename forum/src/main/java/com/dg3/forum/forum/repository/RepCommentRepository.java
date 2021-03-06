package com.dg3.forum.forum.repository;

import com.dg3.forum.forum.entity.RepComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface RepCommentRepository extends JpaRepository<RepComment, Long> {
    /*
    *   Update repcomment
    * @param content_comment_rep and comment_rep_pk
    *
    * */
    @Modifying
    @Transactional
    @Query(value = "update rep_comment set content_comment_rep = :content_comment_rep where comment_rep_pk = :comment_rep_pk", nativeQuery = true)
    void updateRepcomment(@Param("content_comment_rep") String content_comment_rep, @Param("comment_rep_pk") Long comment_rep_pk);

    /*
     *   Delete repcomment
     *  @param commnet_rep_pk
     * */
    @Modifying
    @Transactional
    @Query(value = "delete from rep_comment where comment_rep_pk = :comment_rep_pk", nativeQuery = true)
    void deleteRepcomment(@Param("comment_rep_pk") Long comment_rep_pk);

    /*
    * Find information repcomment
    * @param comment_rep_pk
    * @return object repcomment
    * */
    @Transactional
    @Query(value = "select * from rep_comment where comment_rep_pk = :comment_rep_pk", nativeQuery = true)
    RepComment findByComment_rep_pk(@Param("comment_rep_pk") Long comment_rep_pk);

    /*
     * Find information repcomment
     * @param comment_pk
     * @return list object repcomment
     * */
    @Transactional
    @Query(value = "select * from rep_comment where comment_pk = :comment_pk", nativeQuery = true)
    List<RepComment> getAllByRepcomment(@Param("comment_pk") Long comment_pk);

    /*
    * Sum repcomment by comment posts
    * @param comment_pk
    * @return total number of objects found
    * */
    @Transactional
    @Query(value = "select count(*) from rep_comment where comment_pk = :comment_pk", nativeQuery = true)
    String sumRep_comment(@Param("comment_pk") Long comment_pk);
}
