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
package jp.co.ntt.fw.spring.functionaltest.app.djpa;

import java.text.ParseException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPABookInitializerService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.djpa.JPADeliveryOrderInitializerService;

/**
 * DAM3用のテストデータをクリア・作成するためのWEB APIを提供するクラス。
 */
@RequestMapping("djpa/testdata")
@Controller
public class DJPATestDataController {

    @Inject
    JPABookInitializerService bookInitializerService;

    @Inject
    JPADeliveryOrderInitializerService jpaDeliveryOrderInitializerService;

    @PostMapping(value = "books")
    @ResponseBody
    public ResponseEntity<Void> createBooks() {
        bookInitializerService.clear();
        bookInitializerService.initByBatch();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "books")
    @ResponseBody
    public ResponseEntity<Void> deleteBooks() throws ParseException {
        bookInitializerService.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "order")
    @ResponseBody
    public ResponseEntity<Void> createOrders() {
        jpaDeliveryOrderInitializerService.clear();
        jpaDeliveryOrderInitializerService.initByBatch();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "order")
    @ResponseBody
    public ResponseEntity<Void> deleteOrders() throws ParseException {
        jpaDeliveryOrderInitializerService.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
