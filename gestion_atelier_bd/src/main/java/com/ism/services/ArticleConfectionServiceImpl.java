package com.ism.services;

import java.util.ArrayList;

import com.ism.entities.ArticleConfection;
import com.ism.repository.ITables;

public class ArticleConfectionServiceImpl implements ArticleConfectionService {

    private ITables<ArticleConfection> articleRepository;

    public ArticleConfectionServiceImpl(ITables<ArticleConfection> articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void setArticleRepository(ITables<ArticleConfection> articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public int add(ArticleConfection article) {
        return articleRepository.insert(article);
    }

    @Override
    public ArrayList<ArticleConfection> getAll() {
        return articleRepository.findAll();
    }

    @Override
    public int update(ArticleConfection article) {
        return articleRepository.update(article);
    }

    @Override
    public ArticleConfection show(int id) {
        return articleRepository.findById(id);
    }

    @Override
    public int remove(int id) {
        return articleRepository.delete(id);
    }

    @Override
    public int[] remove(int[] ids) {
        int[] idsNotDeleted = new int[ids.length];
        int nbre = 0;
        for (int id : ids) {
            if (articleRepository.delete(id) == 0) {
                idsNotDeleted[nbre++] = id;
            }
        }
        return idsNotDeleted;
    }
}
