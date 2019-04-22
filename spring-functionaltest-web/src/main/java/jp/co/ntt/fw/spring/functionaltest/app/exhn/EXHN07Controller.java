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
package jp.co.ntt.fw.spring.functionaltest.app.exhn;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@Controller
@RequestMapping("exhn")
public class EXHN07Controller {

    // NoSuchRequestHandlingMethodExceptionは廃止予定であり、
    // @SuppressWarningsの影響をメソッドレベルにするため、FQCNで指定している。
    @SuppressWarnings("deprecation")
    @RequestMapping(value = "0701/001")
    public String handle0701001(
            HttpServletRequest request) throws org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException {

        throw new org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException(request);

    }

    @RequestMapping(value = "0701/002", method = RequestMethod.POST)
    public String handle0701002() {

        return "cspr/exhn";
    }

    @RequestMapping(value = "0701/003")
    public String handle0701003() throws HttpMediaTypeNotSupportedException {

        throw new HttpMediaTypeNotSupportedException("");

    }

    @RequestMapping(value = "0701/004")
    public String handle0701004() throws HttpMediaTypeNotAcceptableException {

        throw new HttpMediaTypeNotAcceptableException("");

    }

    @RequestMapping(value = "0701/005")
    public String handle0701005() throws MissingPathVariableException {

        throw new MissingPathVariableException(null, null);

    }

    @RequestMapping(value = "0701/006")
    public String handle0701006() throws MissingServletRequestParameterException {

        throw new MissingServletRequestParameterException("", "");

    }

    @RequestMapping(value = "0701/007")
    public String handle0701007() throws ServletRequestBindingException {

        throw new ServletRequestBindingException("");

    }

    @RequestMapping(value = "0701/008")
    public String handle0701008() throws ConversionNotSupportedException {

        throw new ConversionNotSupportedException("", String.class, new Throwable());

    }

    @RequestMapping(value = "0701/009")
    public String handle0701009(@RequestParam("id") int id) {

        return "cspr/exhn";
    }

    @RequestMapping(value = "0701/010")
    public String handle0701010() throws HttpMessageNotReadableException {

        throw new HttpMessageNotReadableException("");
    }

    @RequestMapping(value = "0701/011")
    public String handle0701011() throws HttpMessageNotWritableException {

        throw new HttpMessageNotWritableException("");
    }

    @RequestMapping(value = "0701/012")
    public String handle0701012(@Validated EmployeeForm form,
            BindingResult result) throws MethodArgumentNotValidException, NoSuchMethodException, SecurityException {

        throw new MethodArgumentNotValidException(new MethodParameter(this
                .getClass().getMethod("handle0701012", EmployeeForm.class,
                        BindingResult.class), 0), result);
    }

    @RequestMapping(value = "0701/013")
    public String handle0701013() throws MissingServletRequestPartException {

        throw new MissingServletRequestPartException("");
    }

    @RequestMapping(value = "0701/014")
    public String handle0701014(
            @Validated EmployeeForm form) throws BindException {

        return "cspr/exhn";
    }

    @RequestMapping(value = "0701/016")
    public String handle0701016() throws AsyncRequestTimeoutException {

        throw new AsyncRequestTimeoutException();
    }

    @RequestMapping(value = "0701/017")
    public String handle0701017() {

        return "exhn/index";
    }
}
