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
package jp.co.ntt.fw.spring.functionaltest.app.dtac;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("dtac")
public class DTAC01Controller {

    @RequestMapping(value = "0101/001")
    public String handle0101001() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0101/002")
    public String handle0101002() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0102/001")
    public String handle0102001() {
        return "redirect:/dtac/login";
    }

    @RequestMapping(value = "0102/002")
    public String handle0102002() {
        return "redirect:/dtac/select";
    }

    @RequestMapping(value = "0102/select/dataSourceOpen")
    public String selectDataSourceA() {
        return "redirect:/dtac/user/listOpen";
    }

    @RequestMapping(value = "0102/select/dataSourceClose")
    public String selectDataSourceB() {
        return "redirect:/dtac/user/listClose";
    }
}
