/*
 * Copyright(c) 2014 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.domain.service.ssmn;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Item;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.ssmn.ItemRepository;

import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;

@Transactional
@Service
public class ItemServiceImpl implements ItemService {

    @Inject
    ItemRepository itemRepository;

    public Page<Item> getItems(Pageable pageable) {
        List<Item> items = null;
        long total = itemRepository.count();
        if (0 < total) {
            RowBounds rowBounds = new RowBounds(pageable.getOffset(), pageable
                    .getPageSize());
            items = itemRepository.findPage(rowBounds);
        } else {
            items = new ArrayList<Item>();
        }
        return new PageImpl<Item>(items, pageable, total);
    }

    public Item getItem(String itemId) {
        Item item = itemRepository.findOne(itemId);
        if (item == null) {
            throw new ResourceNotFoundException(String.format(
                    "Specified item is not found. itemId is '%s'.", itemId));
        }
        return item;
    }

}
