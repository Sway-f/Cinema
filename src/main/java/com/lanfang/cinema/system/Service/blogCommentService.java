package com.lanfang.cinema.system.Service;

import com.lanfang.cinema.system.Domain.blogComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface blogCommentService {
    Object saveblogComment(blogComment blogcomment);

    blogComment getblogComment(Long id);

    Page<blogComment> listblogComment(Pageable pageable);

    void  deletblogComment(Long id);

    Page<blogComment> getblogCommentByblog_id(Long blog_id, Pageable pageable);
}
