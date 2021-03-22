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
package jp.co.ntt.fw.spring.functionaltest.app.prmn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("prmn")
public class PRMN03Controller {

    @Value("${item.upload.title}")
    private String uploadTitle;

    @Value("${item.upload.dir}")
    private String uploadDir;

    @Value("${item.upload.maxUpdateFileNum}")
    private int maxUpdateFileNum;

    @Value("${i.sf.prmn.0300:4000}")
    private String prmnDefaultValue;

    @RequestMapping(value = "0301/001")
    public String handle0301001(Model model) {
        model.addAttribute("uploadTitle", uploadTitle);
        model.addAttribute("uploadDir", uploadDir);
        model.addAttribute("maxUpdateFileNum", maxUpdateFileNum);
        return "prmn/controllerPropertiesDsp";
    }

    @RequestMapping(value = "0302/001")
    public String handle0302001(Model model) {
        model.addAttribute("prmnDefaultValue", prmnDefaultValue);
        return "prmn/controllerPropertiesDefaultValueDsp";
    }

}
