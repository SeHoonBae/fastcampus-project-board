package com.fastcampus.projectboard.repository.querydsl;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

// ArticleRepositoryCustom의 queryDsl을 쓰기위해 뒤에 Impl을 붙이는건 약속이다 해당부분을 바꾸고 싶으면 설정을 바꿔야함.
public class ArticleRepositoryCustomImpl extends QuerydslRepositorySupport implements ArticleRepositoryCustom {

    public ArticleRepositoryCustomImpl() {
        super(Article.class);
    }

    @Override
    public List<String> findAllDistinctHashtags() {

        QArticle article = QArticle.article;

        List<String> query = from(article)
                .distinct()
                .select(article.hashtag)
                .where(article.hashtag.isNotNull())
                .fetch();

        return query;
    }
}
