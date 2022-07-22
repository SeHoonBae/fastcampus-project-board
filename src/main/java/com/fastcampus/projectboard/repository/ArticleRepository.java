package com.fastcampus.projectboard.repository;

import com.fastcampus.projectboard.domain.Article;
import com.fastcampus.projectboard.domain.QArticle;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>, // QuerydslPredicateExecutor를 쓰는것만으로도 Article에 정의된 필드들에 대한 검색기능이 추가된다.
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root){
        bindings.excludeUnlistedProperties(true); // 기본값은 false이며 Article의 모든 필드들을 검색하도록 되어 있는 것을 닫는 역할
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
//        bindings.bind(root.content).first(StringExpression::likeIgnoreCase); // like '%s${v}'
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // like '%s${v}%'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // like '%s${v}%'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); // like '%s${v}%'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);

    }

}