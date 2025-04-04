/*
 * Copyright(c) 2024 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.domain.service.dam3;

import org.mapstruct.Mapper;
import jp.co.ntt.fw.spring.functionaltest.domain.model.AutoMapTodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;

@Mapper
public interface TodoMB3ForJSR310BeanMapper {

    TodoMB3 map(AutoMapTodoMB3 todoMB3Auto);
}
