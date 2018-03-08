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
package jp.co.ntt.fw.spring.functionaltest.ws.soap;

import java.util.List;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Todo;
import jp.co.ntt.fw.spring.functionaltest.ws.webfault.WebFaultException;

@WebService(targetNamespace = "http://functionaltest.spring.fw.ntt.co.jp/todo")
public interface TodoWebService {

    @WebMethod
    @WebResult(name = "todos")
    public List<Todo> getTodos();

    @WebMethod
    @WebResult(name = "todo")
    public Todo getTodo(
            @WebParam(name = "todoId") String todoId) throws WebFaultException;

    @WebMethod
    @WebResult(name = "todo")
    public Todo createTodo(
            @WebParam(name = "todo") Todo todo) throws WebFaultException;

    @WebMethod
    @WebResult(name = "todo")
    public Todo updateTodo(
            @WebParam(name = "todo") Todo todo) throws WebFaultException;

    @WebMethod
    public void deleteTodo(
            @WebParam(name = "todoId") String todoId) throws WebFaultException;

    @WebMethod
    public void deleteTodos();

    @WebMethod
    public void handlerTest() throws WebFaultException;

    @WebMethod
    public void timeoutTest() throws WebFaultException, InterruptedException;

    @WebMethod
    public boolean uploadFile(
            @XmlMimeType("application/octet-stream") DataHandler handler) throws WebFaultException;

}
