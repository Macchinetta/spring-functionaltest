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

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("prmn")
@Controller
public class PRMN02Controller {

    @Inject
    Prmn0201BeanDefine prmn0201BeanDefine;

    @Inject
    Prmn0202BeanDefine prmn0202BeanDefine;

    @RequestMapping(value = "0201/001")
    public String handle0201001(Model model) {
        model.addAttribute("prmn0201BeanDefine", prmn0201BeanDefine);
        return "prmn/beanDefineDsp";
    }

    @RequestMapping(value = "0202/001")
    public String handle0202001(Model model) {
        model.addAttribute("prmn0202BeanDefine", prmn0202BeanDefine);
        return "prmn/beanDefineDefaultValueDsp";
    }

}
