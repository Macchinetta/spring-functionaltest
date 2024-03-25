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

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Controller
@RequestMapping("jsp")
public class EXHN_JSP_07Controller {

    @Value("${app.base.web.path}")
    private String baseWeb;

    @PostMapping(value = "0701/001")
    public String handle0701001() {

        // HttpRequestMethodNotSupportedExceptionを発生させるため、当returnは呼ばれない
        return "dummy";
    }

    @GetMapping(value = "0701/002")
    public String handle0701002() throws HttpMediaTypeNotSupportedException {

        throw new HttpMediaTypeNotSupportedException("");

    }

    @GetMapping(value = "0701/003")
    public String handle0701003() throws HttpMediaTypeNotAcceptableException {

        throw new HttpMediaTypeNotAcceptableException("");

    }

    @GetMapping(value = "0701/004")
    public String handle0701004() throws MissingPathVariableException {

        throw new MissingPathVariableException(null, null);

    }

    @GetMapping(value = "0701/005")
    public String handle0701005() throws MissingServletRequestParameterException {

        throw new MissingServletRequestParameterException("", "");

    }

    @GetMapping(value = "0701/006")
    public String handle0701006() throws ServletRequestBindingException {

        throw new ServletRequestBindingException("");

    }

    @GetMapping(value = "0701/007")
    public String handle0701007() throws ConversionNotSupportedException {

        throw new ConversionNotSupportedException("", String.class, new Throwable());

    }

    @GetMapping(value = "0701/008")
    public String handle0701008(@RequestParam("id") int id) {

        // TypeMismatchExceptionを発生させるため、当returnは呼ばれない
        return "dummy";
    }

    @GetMapping(value = "0701/009")
    public String handle0701009() throws HttpMessageNotReadableException {

        throw new HttpMessageNotReadableException("", (HttpInputMessage) null);
    }

    @GetMapping(value = "0701/010")
    public String handle0701010() throws HttpMessageNotWritableException {

        throw new HttpMessageNotWritableException("");
    }

    @GetMapping(value = "0701/011")
    public String handle0701011(@Validated EmployeeForm form,
            BindingResult result) throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {

        throw new MethodArgumentNotValidException(new MethodParameter(this
                .getClass().getMethod("handle0701011", EmployeeForm.class,
                        BindingResult.class), 0), result);
    }

    @GetMapping(value = "0701/012")
    public String handle0701012() throws MissingServletRequestPartException {

        throw new MissingServletRequestPartException("");
    }

    @GetMapping(value = "0701/013")
    public String handle0701013(
            @Validated EmployeeForm form) throws BindException {

        // BeanValidationを発生させるため、当returnは呼ばれない
        return "dummy";
    }

    @GetMapping(value = "0701/015")
    public String handle0701015() throws AsyncRequestTimeoutException {

        throw new AsyncRequestTimeoutException();

    }

    @GetMapping(value = "0701/016")
    public String handle0701016(Model model) {
        model.addAttribute("baseWeb", this.baseWeb);
        return "index";
    }
}
