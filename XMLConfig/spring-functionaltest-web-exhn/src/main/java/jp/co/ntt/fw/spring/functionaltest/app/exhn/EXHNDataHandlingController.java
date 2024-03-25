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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.inject.Inject;

@Controller
@RequestMapping
public class EXHNDataHandlingController {

    @Value("${app.base.web.path}")
    private String baseWeb;

    @Inject
    ArticleFileHelper articleFileHelper;

    @GetMapping(value = "{view}/delete/view")
    public String deleteTemporaryFileView(@PathVariable("view") String view) {
        return view + "/exhn/articleTemporaryFileDelete";
    }

    @GetMapping(value = "delete")
    public String deleteTemporaryFile(Model model,
            @RequestParam("temporaryFileName") String temporaryFileName) throws IOException {
        articleFileHelper.deleteTemporaryFile(temporaryFileName);
        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }

    @GetMapping(value = "delete/article")
    public String deleteArticleFileBeforeTest(Model model) throws IOException {
        deleteArticleFile();
        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }

    @GetMapping(value = "delete/all")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void deleteArticleFile() throws IOException {
        articleFileHelper.deleteAll();
    }

    @GetMapping(value = "copy/temporaryFile")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void copyTemporaryFile(
            @RequestParam("temporaryFileName") String temporaryFileName) throws IOException {
        articleFileHelper.copyTemporaryFile(temporaryFileName);
    }
}
