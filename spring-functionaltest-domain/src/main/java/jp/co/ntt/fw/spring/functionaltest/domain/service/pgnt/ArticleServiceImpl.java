/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.pgnt;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Article;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleRepository;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.ArticleSearchCriteria;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.pgnt.PageableArticleSearchCriteria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleRepository articleRepository;

    @Override
    public Page<Article> getArticles(ArticleSearchCriteria criteria,
            Pageable pageable) {
        List<Article> articles = null;
        PageableArticleSearchCriteria pageableArticleSearchCriteria = new PageableArticleSearchCriteria(criteria, pageable);

        long total = articleRepository.countByCriteria(
                pageableArticleSearchCriteria);

        if (0 < total) {
            articles = articleRepository.findPage(
                    pageableArticleSearchCriteria);

        } else {
            articles = new ArrayList<Article>();
        }

        return new PageImpl<Article>(articles, pageable, total);
    }
}
