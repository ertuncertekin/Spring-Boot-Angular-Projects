package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.CommentService;
import com.example.recipe.services.dtos.requests.AddCommentRequest;
import com.example.recipe.services.dtos.requests.UpdateCommentRequest;
import com.example.recipe.services.dtos.responses.AddCommentResponse;
import com.example.recipe.services.dtos.responses.ListCommentResponse;
import com.example.recipe.services.dtos.responses.UpdateCommentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("addcomment")
    @ResponseStatus(HttpStatus.CREATED)
    public AddCommentResponse add(@Valid @RequestBody AddCommentRequest request){
        return commentService.add(request);
    }

    @PutMapping("updatecomment")
    public UpdateCommentResponse update(@Valid @RequestBody UpdateCommentRequest updateCommentRequest){
        return  commentService.update(updateCommentRequest);
    }

    @DeleteMapping("deletecomment")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@RequestParam int id){
        commentService.delete(id);
    }

    @GetMapping("/getbyrecipeid/{recipeId}")
    public List<ListCommentResponse> getByRecipe(@PathVariable int recipeId){
        return commentService.getByRecipeId(recipeId);
    }

    @GetMapping("/getbyuserid/{userId}")
    public List<ListCommentResponse> getByUser(@PathVariable int userId){return commentService.getByUserId(userId);}
}

