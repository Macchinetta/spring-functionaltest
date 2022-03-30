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
package jp.co.ntt.fw.spring.functionaltest.api.o2sp.resource;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/messages")
@RestController
public class MessagesController {

    // Controlled by intercept-url

    @GetMapping("/intercept")
    @ResponseStatus(HttpStatus.OK)
    public String getMessagesUsingInterceptUrl() {
        return "SUCCESS(intercept-url, SCOPE_READ)";
    }

    @PostMapping("/intercept")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMessagesUsingInterceptUrl() {
        return "SUCCESS(intercept-url, SCOPE_CREATE)";
    }

    @PutMapping("/intercept")
    @ResponseStatus(HttpStatus.OK)
    public String updateMessagesUsingInterceptUrl() {
        return "SUCCESS(intercept-url, SCOPE_UPDATE)";
    }

    @DeleteMapping("/intercept")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMessagesUsingInterceptUrl() {
        return "SUCCESS(intercept-url, SCOPE_DELETE)";
    }

    // Controlled by @PreAuthorize

    @GetMapping("/annotation")
    @PreAuthorize("hasAuthority('SCOPE_READ')")
    @ResponseStatus(HttpStatus.OK)
    public String getMessagesUsingPreAuthorize() {
        return "SUCCESS(PreAuthorize, SCOPE_READ)";
    }

    @PostMapping("/annotation")
    @PreAuthorize("hasAuthority('SCOPE_CREATE')")
    @ResponseStatus(HttpStatus.CREATED)
    public String createMessagesUsingAnnotationPattern() {
        return "SUCCESS(PreAuthorize, SCOPE_CREATE)";
    }

    @PutMapping("/annotation")
    @PreAuthorize("hasAuthority('SCOPE_UPDATE')")
    @ResponseStatus(HttpStatus.OK)
    public String updateMessagesUsingAnnotationPattern() {
        return "SUCCESS(PreAuthorize, SCOPE_UPDATE)";
    }

    @DeleteMapping("/annotation")
    @PreAuthorize("hasAuthority('SCOPE_DELETE')")
    @ResponseStatus(HttpStatus.OK)
    public String deleteMessagesUsingPreAuthorize() {
        return "SUCCESS(PreAuthorize, SCOPE_DELETE)";
    }
}
