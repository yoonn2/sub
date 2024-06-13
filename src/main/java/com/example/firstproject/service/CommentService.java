package com.example.firstproject.service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service //서비스로 선언
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository; // 댓글 리파지터리 객체 주입
    @Autowired
    private ArticleRepository articleRepository; // 게시글 리파지터리 객체 주입

    public List<CommentDto> comments(Long articleId) {
        /*// 1. 댓글 조회
        List<Comment> comments = commentRepository.findByArticleId(articleId);
        // 2. 엔티티 -> DTO 변환
        List<CommentDto> dtos = new ArrayList<CommentDto>();
        for (int i = 0; i < comments.size(); i++){ // 조회한 댓글 엔티티 수만큼 반복하기
            Comment c = comments.get(i); // 조회한 댓글 엔티티 하나씩 가져오기
            CommentDto dto = CommentDto.createCommentDto(c); // 엔티티를 DTO로 변환
            dtos.add(dto); // 변환한 DTO를 dtos 리스트에 삽입
        }*/
        // 3. 결과 반환
        return commentRepository.findByArticleId(articleId)// 댓글 엔티티 목록 조회
                .stream()// 댓글 엔티티 목록을 스트림으로 변환
                .map(comment -> CommentDto.createCommentDto(comment)) // 엔티티를 DTO로 매핑
                .collect(Collectors.toList()); // 스트림을 리스트로 변환
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto dto) {
        log.info("입력값 => {}", articleId);
        log.info("입력값 => {}", dto);
        // 1. 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleId) // 부모 게시글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패!" +
                        "대상 게시글이 없습니다.")); // 없으면 에러 메서지 출력
        // 2. 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 3. 댓글 엔티티를 DB에 저장
        Comment created  = commentRepository.save(comment);
        // 4. DTO로 변환해 반환
        //return CommentDto.createCommentDto(created);
        CommentDto createdDto = CommentDto.createCommentDto(created);
        //log.info("반환값 => {}", createdDto);
        return createdDto;
    }
    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id) // 수정할 댓글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패!" +
                        "대상 댓글이 없습니다.")); // 없으면 에러 메세지 출력
        // 2. 댓글 수정
        target.patch(dto);
        // 3. DB로 갱신
        Comment updated = commentRepository.save(target);
        // 4. 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 1. 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id) // 삭제할 댓글 가져오기
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패" +
                        "대상이 없습니다")); // 없으면 에러 메세지 출력
        // 2. 댓글 삭제
        commentRepository.delete(target);
        // 3. 삭제 댓글을 DTO로 변환 및 반환
        return CommentDto.createCommentDto(target);
    }
}
