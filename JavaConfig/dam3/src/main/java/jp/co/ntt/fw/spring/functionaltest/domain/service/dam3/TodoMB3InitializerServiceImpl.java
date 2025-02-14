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

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.model.CategoryMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.model.TodoMB3;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.dam3.TodoRepository;

@Service
public class TodoMB3InitializerServiceImpl implements TodoMB3InitializerService {

    @Inject
    TodoRepository todoRepositry;

    @Override
    public void initByBatch() {

        List<TodoMB3> todoList = new ArrayList<TodoMB3>();

        TodoMB3 todoMB3 = new TodoMB3();
        CategoryMB3 cat = new CategoryMB3();
        cat.setCategoryId("0000000001");
        InputStream desc1 =
                new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc11").array());
        Reader desc2 = new StringReader("desc21");
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 24));
        todoMB3.setFinished(false);
        // todoMB3.setTodoCategory("0000000001");
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000001");
        todoMB3.setTodoTitle("Todo 1");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000002");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc12").array());
        desc2 = new StringReader("desc22");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 24));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000002");
        todoMB3.setTodoTitle("Todo 2");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000003");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc13").array());
        desc2 = new StringReader("desc23");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 25));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000003");
        todoMB3.setTodoTitle("Todo 3");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000004");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc14").array());
        desc2 = new StringReader("desc24");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 25));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000004");
        todoMB3.setTodoTitle("Todo 4");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000005");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc15").array());
        desc2 = new StringReader("desc25");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 26));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000005");
        todoMB3.setTodoTitle("Todo 5");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000001");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc16").array());
        desc2 = new StringReader("desc26");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 26));
        todoMB3.setFinished(true);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000006");
        todoMB3.setTodoTitle("Todo 6");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoMB3.setCompleteAt(LocalDate.of(2016, 12, 30));
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000002");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc17").array());
        desc2 = new StringReader("desc27");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 27));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000007");
        todoMB3.setTodoTitle("Todo 7");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000003");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc18").array());
        desc2 = new StringReader("desc28");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 28));
        todoMB3.setFinished(true);
        todoMB3.setCompleteAt(LocalDate.of(2016, 12, 30));
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000008");
        todoMB3.setTodoTitle("Todo 8");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000004");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc19").array());
        desc2 = new StringReader("desc29");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 29));
        todoMB3.setFinished(false);
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000009");
        todoMB3.setTodoTitle("Todo 9");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        cat = new CategoryMB3();
        cat.setCategoryId("0000000005");
        desc1 = new ByteArrayInputStream(Charset.forName("UTF-8").encode("desc110").array());
        desc2 = new StringReader("desc210");
        todoMB3 = new TodoMB3();
        todoMB3.setCreatedAt(LocalDate.of(2016, 12, 29));
        todoMB3.setFinished(true);
        todoMB3.setCompleteAt(LocalDate.of(2016, 12, 30));
        todoMB3.setCategory(cat);
        todoMB3.setTodoId("0000000010");
        todoMB3.setTodoTitle("Todo 10");
        todoMB3.setVersion(1);
        todoMB3.setDesc1(desc1);
        todoMB3.setDesc2(desc2);
        todoList.add(todoMB3);

        for (TodoMB3 todoMB3Obj : todoList) {
            todoRepositry.insert(todoMB3Obj);
        }

    }

    @Override
    public void clear() {
        todoRepositry.deleteAll();
    }

}
