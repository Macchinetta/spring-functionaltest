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
package jp.co.ntt.fw.spring.functionaltest.domain.service.djpa;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.model.JPABook;
import jp.co.ntt.fw.spring.functionaltest.domain.model.JPACategory;
import jp.co.ntt.fw.spring.functionaltest.domain.repository.djpa.JPABookRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(value = "jpaTransactionManager")
@Service
public class JPABookBookInitializerServiceImpl implements
                                               JPABookInitializerService {

    @Inject
    JPABookRepository jaBookRepository;

    public void initByBatch() {
        if (0 == jaBookRepository.count()) {
            List<JPABook> books = new ArrayList<JPABook>();

            JPABook book = null;

            Calendar cal = Calendar.getInstance();
            cal.set(2013, 11, 24);
            Date utilDate = cal.getTime();
            java.sql.Date releaseDate = new java.sql.Date(utilDate.getTime());
            book = new JPABook();
            book.setBookId("0000000001");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000001");
            book.setTitle("TitleA011");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA011");
            book.setBlobCode("CodeA011".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000002");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000001");
            book.setTitle("TitleA012");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA012");
            book.setBlobCode("CodeA012".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000003");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000001");
            book.setTitle("TitleA013");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA013");
            book.setBlobCode("CodeA013".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000004");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000002");
            book.setTitle("TitleB011");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB011");
            book.setBlobCode("CodeB011".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000005");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000002");
            book.setTitle("TitleB012");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB012");
            book.setBlobCode("CodeB012".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000006");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000002");
            book.setTitle("TitleB013");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB013");
            book.setBlobCode("CodeB013".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000007");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000003");
            book.setTitle("TitleC011");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC011");
            book.setBlobCode("CodeC011".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000008");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000003");
            book.setTitle("TitleC012");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC012");
            book.setBlobCode("CodeC012".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000009");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000003");
            book.setTitle("TitleC013");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC013");
            book.setBlobCode("CodeC013".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000010");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000004");
            book.setTitle("TitleA021");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA021");
            book.setBlobCode("CodeA021".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000011");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000004");
            book.setTitle("TitleA022");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA022");
            book.setBlobCode("CodeA022".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000012");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000004");
            book.setTitle("TitleA023");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeA023");
            book.setBlobCode("CodeA023".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000013");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000005");
            book.setTitle("TitleB021");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB021");
            book.setBlobCode("CodeB021".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000014");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000005");
            book.setTitle("TitleB022");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB022");
            book.setBlobCode("CodeB022".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000015");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000005");
            book.setTitle("TitleB023");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeB023");
            book.setBlobCode("CodeB023".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000016");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000006");
            book.setTitle("TitleC021");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC021");
            book.setBlobCode("CodeC021".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000017");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000006");
            book.setTitle("TitleC022");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC022");
            book.setBlobCode("CodeC022".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000018");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000006");
            book.setTitle("TitleC023");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeC023");
            book.setBlobCode("CodeC023".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000019");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000007");
            book.setTitle("TitleZ1");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeZ1");
            book.setBlobCode("CodeZ1".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000020");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000007");
            book.setTitle("TitleZ2");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeZ2");
            book.setBlobCode("CodeZ2".getBytes());
            books.add(book);

            book = new JPABook();
            book.setBookId("0000000021");
            book.setCategory(new JPACategory());
            book.getCategory().setCategoryId("0000000007");
            book.setTitle("TitleZ3");
            book.setPrice(10000);
            book.setReleaseDate(releaseDate);
            book.setClobCode("CodeZ3");
            book.setBlobCode("CodeZ3".getBytes());
            books.add(book);

            for (JPABook bookTmp : books) {
                jaBookRepository.save(bookTmp);
            }
        }

        return;
    }

    public void clear() {
        jaBookRepository.deleteAll();
    }

}
