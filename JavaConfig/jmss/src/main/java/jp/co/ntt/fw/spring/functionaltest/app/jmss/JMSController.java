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
package jp.co.ntt.fw.spring.functionaltest.app.jmss;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import jakarta.inject.Inject;
import jp.co.ntt.fw.spring.functionaltest.domain.service.jmss.JmsAsyncReceiveSynchronizingService;

@Controller
public class JMSController {

    @Inject
    JmsAsyncReceiveSynchronizingService jmsAsyncReceiveSynchronizingService;

    @GetMapping
    public String index(Model model) {
        return "index";
    }

    @GetMapping(value = "jmss/await")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void awaitUntilReceivedMessages(@RequestParam("jmsTodoId") String jmsTodoId) {
        this.jmsAsyncReceiveSynchronizingService.await(jmsTodoId);
    }

    @GetMapping(value = "jmss/clear")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void clear() throws IOException {
        this.jmsAsyncReceiveSynchronizingService.deleteTempFilesAndClearMap();
    }

}
