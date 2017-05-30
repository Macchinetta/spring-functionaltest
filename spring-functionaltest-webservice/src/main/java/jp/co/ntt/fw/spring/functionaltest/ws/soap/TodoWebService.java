/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
    public Todo getTodo(@WebParam(name = "todoId") String todoId) throws WebFaultException;

    @WebMethod
    @WebResult(name = "todo")
    public Todo createTodo(@WebParam(name = "todo") Todo todo) throws WebFaultException;

    @WebMethod
    @WebResult(name = "todo")
    public Todo updateTodo(@WebParam(name = "todo") Todo todo) throws WebFaultException;

    @WebMethod
    public void deleteTodo(@WebParam(name = "todoId") String todoId) throws WebFaultException;

    @WebMethod
    public void deleteTodos();

    @WebMethod
    public void handlerTest() throws WebFaultException;

    @WebMethod
    public void timeoutTest() throws WebFaultException;

    @WebMethod
    public boolean uploadFile(
            @XmlMimeType("application/octet-stream") DataHandler handler) throws WebFaultException;

}
