package com.dg3.forum.forum.controller;

import com.dg3.forum.forum.dto.CommentImageDTO;
import com.dg3.forum.forum.entity.Image;
import com.dg3.forum.forum.entity.Message;
import com.dg3.forum.forum.service.ImageService;
import com.dg3.forum.forum.util.GetNameExtensionsForbase64;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    /**
     * create imageFile by post through thread_pk
     * @param imageFile and thread_pk
     * @return insert object image in database
     * @throws IOException
     */
    @PostMapping("/create/posts/{thread_pk}")
    public ResponseEntity<Message> createImagePosts(@RequestParam MultipartFile imageFile, @PathVariable("thread_pk") Long thread_pk) throws IOException {
        byte[] imageArr = imageFile.getBytes();

        //Base64 that converts image as bytes to
        //base64 encoded string, and this string store in a
        //varchar column of database.
        String imageAsString= Base64.encodeBase64String(imageArr);

        /*
        * Get file extension by image
        * */
        String nameFile = imageFile.getOriginalFilename();

       String nameExtension =  GetNameExtensionsForbase64.getPartExtensions(nameFile);

        Image image_Posts = new Image();
       image_Posts.setImage_thread(nameExtension + imageAsString);
        image_Posts.setThread_pk(thread_pk);

       return ResponseEntity.status(HttpStatus.OK).body(
               new Message("OK", "Upload file image successfully", imageService.createImagePosts(image_Posts))
       );
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new Message("OK", "Upload file image successfully", nameExtension + imageAsString)
//        );
    }

    /**
     * create imageFile by comment through comment_pk
     * @param imageFile
     * @param comment_pk
     * @return insert object image in database
     * @throws IOException
     */
    @PostMapping("/create/comment/{comment_pk}")
    public ResponseEntity<Message> createImageComment(@RequestParam MultipartFile imageFile, @PathVariable("comment_pk") Long comment_pk) throws IOException {
        byte[] imageArr = imageFile.getBytes();

        //Base64 that converts image as bytes to
        //base64 encoded string, and this string store in a
        //varchar column of database.
        String imageAsString= Base64.encodeBase64String(imageArr);
        /*
         * Get file extension by image
         * */
         String nameFile = imageFile.getOriginalFilename();

        String nameExtension =  GetNameExtensionsForbase64.getPartExtensions(nameFile);
        
        Image image_Comment = new Image();
        image_Comment.setImage_comment(nameExtension + imageAsString);
        image_Comment.setComment_pk(comment_pk);

        return ResponseEntity.status(HttpStatus.OK).body(
                new Message("OK", "Upload file image successfully", imageService.createImageComment(image_Comment))
        );
    }

    /**
     * list all image posts
     * @param thread_pk
     * @return list all image posts in database
     */
    @GetMapping("/all/posts/{thread_pk}")
    public ResponseEntity<Message> listAllImagePosts(@PathVariable("thread_pk") Long thread_pk){
        List<Image> listImagePosts = imageService.listAllImagePosts(thread_pk);

        return listImagePosts.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Failed", "Can't find list information image", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "List information image successfully", listImagePosts)
                );
    }
    
    
    /**
     * get all image comment
     * @return list all image comment in database
     */
    @GetMapping("/all/imagecomment")
    public ResponseEntity<Message> listAllImageComment(){
        List<Image> listImageComment = imageService.listAllImageComment();
        List<CommentImageDTO> commentImageDTOS = new ArrayList<CommentImageDTO>();
        for(Image image : listImageComment)
        {
        	commentImageDTOS.add(new CommentImageDTO(image));
        }
        return listImageComment.isEmpty() ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Message("Failed", "Can't find list information image comment", "")
                ) :
                ResponseEntity.status(HttpStatus.OK).body(
                        new Message("OK", "List information image comment successfully", commentImageDTOS)
                );
    }

    /**
     * Delete Image Comment
     * @param image_pk
     * @return delete Image Comment in database
     */
    @DeleteMapping("/{image_pk}")
  	ResponseEntity<Message> deleteImageComment(@PathVariable Long image_pk) {
  		if (imageService.existById(image_pk)) {
  			imageService.deleteImageComment(image_pk);
  		
  			return ResponseEntity.status(HttpStatus.OK).body(new Message("success!", "deleted",""));
  		}
  		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message("Faild!", "not find image !", ""));
  	}
	
}
