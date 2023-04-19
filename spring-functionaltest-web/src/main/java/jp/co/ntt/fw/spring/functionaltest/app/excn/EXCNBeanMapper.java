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
package jp.co.ntt.fw.spring.functionaltest.app.excn;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPAStock;
import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;

@Mapper
public interface EXCNBeanMapper {

    StockForm map(Stock stock);

    Stock map(StockForm stockForm);

    void map(Stock stock, @MappingTarget StockForm stockForm);

    StockForm mapJPA(JPAStock jpaStock);

    JPAStock mapJPA(StockForm stockForm);

    void mapJPA(JPAStock jpaStock, @MappingTarget StockForm stockForm);

}