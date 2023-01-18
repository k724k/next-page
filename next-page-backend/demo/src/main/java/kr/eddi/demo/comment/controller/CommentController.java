package kr.eddi.demo.comment.controller;

import kr.eddi.demo.comment.response.CommentResponse;
import kr.eddi.demo.comment.service.CommentService;
import kr.eddi.demo.comment.request.CommentModifyRequest;
import kr.eddi.demo.comment.request.CommentWriteRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
public class CommentController {

    @Autowired
    CommentService commentService;


    @PostMapping("/write/{novelEpisodeId}")
    public void commentWrite(@PathVariable("novelEpisodeId") Long novelEpisodeId, @RequestBody CommentWriteRequest commentWriteRequest) {
        log.info("writeComment()");

        commentService.commentWrite(commentWriteRequest, novelEpisodeId);
    }

    @PostMapping("/write-for-qna/{qnaId}")
    public void commentWriteForQna(@PathVariable("qnaId") Long qnaId, @RequestBody CommentWriteRequest commentWriteRequest) {
        log.info("commentWriteForQna()");

        commentService.commentWrite(commentWriteRequest, qnaId);
    }

    @DeleteMapping("/delete/{commentNo}")
    public void commentDelete (@PathVariable("commentNo") Long commentNo) {
        log.info("commentDelete()" + commentNo);

        commentService.commentDelete(commentNo);
    }

    @PutMapping("/modify/{commentNo}")
    public void commentModify (@PathVariable("commentNo") Long commentNo, @RequestBody CommentModifyRequest commentModifyRequest) {
        log.info("commentModifyRequest()");

        commentService.commentModify(commentNo, commentModifyRequest);
    }

    @GetMapping("/episode/{episodeId}")
    public List<CommentResponse> getCommentListByEpisodeId(@PathVariable("episodeId") Long episodeId) {
        log.info("getCommentListByEpisodeId(): " + episodeId);
        return commentService.getCommentListByEpisodeId(episodeId);
    }

    @GetMapping("/novel-comment-list/{novelInfoId}")
    public List<CommentResponse> getCommentListByNovelId(@PathVariable("novelInfoId") Long novelInfoId) {
        log.info("getCommentListByNovelId(): " + novelInfoId);
        return commentService.getCommentListByNovelId(novelInfoId);
    }
}
