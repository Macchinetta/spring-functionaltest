/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
