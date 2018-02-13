/*
 * Copyright 2014-2017 NTT Corporation.
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
 *
 */
package jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java element interface generated in the
 * jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the Java representation for XML content. The Java
 * representation of XML content can consist of schema derived interfaces and classes representing the binding of schema type
 * definitions, element declarations and model groups. Factory methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeleteTodos_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "deleteTodos");

    private final static QName _UploadFile_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "uploadFile");

    private final static QName _DeleteTodosResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "deleteTodosResponse");

    private final static QName _GetTodos_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "getTodos");

    private final static QName _TimeoutTest_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "timeoutTest");

    private final static QName _TimeoutTestResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "timeoutTestResponse");

    private final static QName _GetTodoResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "getTodoResponse");

    private final static QName _WebFault_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "WebFault");

    private final static QName _UploadFileResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "uploadFileResponse");

    private final static QName _CreateTodoResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "createTodoResponse");

    private final static QName _UpdateTodo_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "updateTodo");

    private final static QName _DeleteTodo_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "deleteTodo");

    private final static QName _UpdateTodoResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "updateTodoResponse");

    private final static QName _CreateTodo_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "createTodo");

    private final static QName _DeleteTodoResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "deleteTodoResponse");

    private final static QName _GetTodo_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "getTodo");

    private final static QName _GetTodosResponse_QNAME = new QName("http://functionaltest.spring.fw.ntt.co.jp/todo", "getTodosResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package:
     * jp.co.ntt.fw.spring.functionaltest.ws.soap.wsimport
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UploadFile }
     */
    public UploadFile createUploadFile() {
        return new UploadFile();
    }

    /**
     * Create an instance of {@link DeleteTodosResponse }
     */
    public DeleteTodosResponse createDeleteTodosResponse() {
        return new DeleteTodosResponse();
    }

    /**
     * Create an instance of {@link GetTodos }
     */
    public GetTodos createGetTodos() {
        return new GetTodos();
    }

    /**
     * Create an instance of {@link DeleteTodos }
     */
    public DeleteTodos createDeleteTodos() {
        return new DeleteTodos();
    }

    /**
     * Create an instance of {@link TimeoutTestResponse }
     */
    public TimeoutTestResponse createTimeoutTestResponse() {
        return new TimeoutTestResponse();
    }

    /**
     * Create an instance of {@link TimeoutTest }
     */
    public TimeoutTest createTimeoutTest() {
        return new TimeoutTest();
    }

    /**
     * Create an instance of {@link WebFaultBean }
     */
    public WebFaultBean createWebFaultBean() {
        return new WebFaultBean();
    }

    /**
     * Create an instance of {@link UploadFileResponse }
     */
    public UploadFileResponse createUploadFileResponse() {
        return new UploadFileResponse();
    }

    /**
     * Create an instance of {@link CreateTodoResponse }
     */
    public CreateTodoResponse createCreateTodoResponse() {
        return new CreateTodoResponse();
    }

    /**
     * Create an instance of {@link UpdateTodo }
     */
    public UpdateTodo createUpdateTodo() {
        return new UpdateTodo();
    }

    /**
     * Create an instance of {@link GetTodoResponse }
     */
    public GetTodoResponse createGetTodoResponse() {
        return new GetTodoResponse();
    }

    /**
     * Create an instance of {@link GetTodo }
     */
    public GetTodo createGetTodo() {
        return new GetTodo();
    }

    /**
     * Create an instance of {@link GetTodosResponse }
     */
    public GetTodosResponse createGetTodosResponse() {
        return new GetTodosResponse();
    }

    /**
     * Create an instance of {@link DeleteTodo }
     */
    public DeleteTodo createDeleteTodo() {
        return new DeleteTodo();
    }

    /**
     * Create an instance of {@link UpdateTodoResponse }
     */
    public UpdateTodoResponse createUpdateTodoResponse() {
        return new UpdateTodoResponse();
    }

    /**
     * Create an instance of {@link CreateTodo }
     */
    public CreateTodo createCreateTodo() {
        return new CreateTodo();
    }

    /**
     * Create an instance of {@link DeleteTodoResponse }
     */
    public DeleteTodoResponse createDeleteTodoResponse() {
        return new DeleteTodoResponse();
    }

    /**
     * Create an instance of {@link ErrorBean }
     */
    public ErrorBean createErrorBean() {
        return new ErrorBean();
    }

    /**
     * Create an instance of {@link Todo }
     */
    public Todo createTodo() {
        return new Todo();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTodos }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "deleteTodos")
    public JAXBElement<DeleteTodos> createDeleteTodos(DeleteTodos value) {
        return new JAXBElement<DeleteTodos>(_DeleteTodos_QNAME, DeleteTodos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFile }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "uploadFile")
    public JAXBElement<UploadFile> createUploadFile(UploadFile value) {
        return new JAXBElement<UploadFile>(_UploadFile_QNAME, UploadFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTodosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "deleteTodosResponse")
    public JAXBElement<DeleteTodosResponse> createDeleteTodosResponse(
            DeleteTodosResponse value) {
        return new JAXBElement<DeleteTodosResponse>(_DeleteTodosResponse_QNAME, DeleteTodosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTodos }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "getTodos")
    public JAXBElement<GetTodos> createGetTodos(GetTodos value) {
        return new JAXBElement<GetTodos>(_GetTodos_QNAME, GetTodos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeoutTest }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "timeoutTest")
    public JAXBElement<TimeoutTest> createTimeoutTest(TimeoutTest value) {
        return new JAXBElement<TimeoutTest>(_TimeoutTest_QNAME, TimeoutTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TimeoutTestResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "timeoutTestResponse")
    public JAXBElement<TimeoutTestResponse> createTimeoutTestResponse(
            TimeoutTestResponse value) {
        return new JAXBElement<TimeoutTestResponse>(_TimeoutTestResponse_QNAME, TimeoutTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTodoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "getTodoResponse")
    public JAXBElement<GetTodoResponse> createGetTodoResponse(
            GetTodoResponse value) {
        return new JAXBElement<GetTodoResponse>(_GetTodoResponse_QNAME, GetTodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WebFaultBean }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "WebFault")
    public JAXBElement<WebFaultBean> createWebFault(WebFaultBean value) {
        return new JAXBElement<WebFaultBean>(_WebFault_QNAME, WebFaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UploadFileResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "uploadFileResponse")
    public JAXBElement<UploadFileResponse> createUploadFileResponse(
            UploadFileResponse value) {
        return new JAXBElement<UploadFileResponse>(_UploadFileResponse_QNAME, UploadFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTodoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "createTodoResponse")
    public JAXBElement<CreateTodoResponse> createCreateTodoResponse(
            CreateTodoResponse value) {
        return new JAXBElement<CreateTodoResponse>(_CreateTodoResponse_QNAME, CreateTodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTodo }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "updateTodo")
    public JAXBElement<UpdateTodo> createUpdateTodo(UpdateTodo value) {
        return new JAXBElement<UpdateTodo>(_UpdateTodo_QNAME, UpdateTodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTodo }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "deleteTodo")
    public JAXBElement<DeleteTodo> createDeleteTodo(DeleteTodo value) {
        return new JAXBElement<DeleteTodo>(_DeleteTodo_QNAME, DeleteTodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateTodoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "updateTodoResponse")
    public JAXBElement<UpdateTodoResponse> createUpdateTodoResponse(
            UpdateTodoResponse value) {
        return new JAXBElement<UpdateTodoResponse>(_UpdateTodoResponse_QNAME, UpdateTodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateTodo }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "createTodo")
    public JAXBElement<CreateTodo> createCreateTodo(CreateTodo value) {
        return new JAXBElement<CreateTodo>(_CreateTodo_QNAME, CreateTodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteTodoResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "deleteTodoResponse")
    public JAXBElement<DeleteTodoResponse> createDeleteTodoResponse(
            DeleteTodoResponse value) {
        return new JAXBElement<DeleteTodoResponse>(_DeleteTodoResponse_QNAME, DeleteTodoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTodo }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "getTodo")
    public JAXBElement<GetTodo> createGetTodo(GetTodo value) {
        return new JAXBElement<GetTodo>(_GetTodo_QNAME, GetTodo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTodosResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://functionaltest.spring.fw.ntt.co.jp/todo", name = "getTodosResponse")
    public JAXBElement<GetTodosResponse> createGetTodosResponse(
            GetTodosResponse value) {
        return new JAXBElement<GetTodosResponse>(_GetTodosResponse_QNAME, GetTodosResponse.class, null, value);
    }

}
