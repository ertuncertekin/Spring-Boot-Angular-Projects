package com.example.recipe.services.concrates;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Comment;
import com.example.recipe.entities.Recipe;
import com.example.recipe.entities.User;
import com.example.recipe.repositories.CommentRepository;
import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.abstracts.CommentService;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.requests.AddCommentRequest;
import com.example.recipe.services.dtos.requests.UpdateCommentRequest;
import com.example.recipe.services.dtos.responses.AddCommentResponse;
import com.example.recipe.services.dtos.responses.ListCommentResponse;
import com.example.recipe.services.dtos.responses.UpdateCommentResponse;
import com.example.recipe.services.mappers.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final RecipeService recipeService;
    @Override
    public AddCommentResponse add(AddCommentRequest addCommentRequest) {
        Optional<User> user=authService.findById(addCommentRequest.getUserId());
        Optional<Recipe> recipe=recipeService.findById(addCommentRequest.getRecipeId());
        Optional<Comment> existingComment = commentRepository.findByUserIdAndRecipeId(
                addCommentRequest.getUserId(), addCommentRequest.getRecipeId());

        if(user.isEmpty()){
            throw new BusinessException("User Not Found");
        }
        else if(recipe.isEmpty()){
            throw new BusinessException("Recipe Not Found");
        }
        else if(existingComment.isPresent()){
            throw new BusinessException("User has already commented on this recipe");
        }
        Comment comment= CommentMapper.INSTANCE.commentFromAddRequest(addCommentRequest);
        comment.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        Comment savedComment=commentRepository.save(comment);
        AddCommentResponse response=CommentMapper.INSTANCE.toResponse(savedComment);
        response.setMessage("Comment added successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public UpdateCommentResponse update(UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> optionalComment=commentRepository.findById(updateCommentRequest.getId());
        if(optionalComment.isEmpty()){
            throw new BusinessException("Comment Not Found");
        }
        Comment comment=optionalComment.get();
        CommentMapper.INSTANCE.updateFromRequest(updateCommentRequest,comment);
        comment.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        Comment savedComment=commentRepository.save(comment);
        UpdateCommentResponse response=CommentMapper.INSTANCE.toUpdateResponse(savedComment);
        response.setSuccess(true);
        response.setMessage("Comment updated successfully");
        return response;
    }

    @Override
    public void delete(int id) {
    Optional<Comment> optionalComment=commentRepository.findById(id);
    if(optionalComment.isEmpty()){
        throw new BusinessException("Comment Not Found");
        }
    commentRepository.deleteById(id);
    }

    @Override
    public List<ListCommentResponse> getByRecipeId(int recipeId) {
        List<Comment> comments=commentRepository.findByRecipeId(recipeId);
        if(comments.isEmpty()){
            throw new BusinessException("Recipe Not Found!");
        }
        return comments.stream().map(this::convertToCommentResponse).collect(Collectors.toList());
    }

    @Override
    public List<ListCommentResponse> getByUserId(int userId) {
        List<Comment> comments=commentRepository.findByUserId(userId);
        if(comments.isEmpty()){
            throw new BusinessException("User Not Found!");
        }
        return comments.stream().map(this::convertToCommentResponse).collect(Collectors.toList());
    }

    private ListCommentResponse convertToCommentResponse(Comment comment){
        return new ListCommentResponse(
                comment.getId(),
                comment.getRecipe().getId(),
                comment.getRecipe().getFinishedDishPhoto().getImageUrl(),
                comment.getComment(),
                comment.getRating(),
                comment.getCreatedAt(),
                comment.getUser().getName(),
                comment.getUser().getSurname()
        );
    }
}
