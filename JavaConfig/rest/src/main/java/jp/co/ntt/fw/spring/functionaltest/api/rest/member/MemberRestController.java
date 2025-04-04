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
package jp.co.ntt.fw.spring.functionaltest.api.rest.member;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.groups.Default;
import jp.co.ntt.fw.spring.functionaltest.api.rest.member.MemberResource.Detail;
import jp.co.ntt.fw.spring.functionaltest.api.rest.member.MemberResource.PostMembers;
import jp.co.ntt.fw.spring.functionaltest.api.rest.member.MemberResource.PutMember;
import jp.co.ntt.fw.spring.functionaltest.api.rest.member.MemberResource.Summary;
import jp.co.ntt.fw.spring.functionaltest.domain.model.RestMember;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rest.RestMemberForSpecificExceptionService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.rest.RestMemberService;

@RequestMapping("members")
@RestController
public class MemberRestController {

    @Inject
    RestMemberService restMemberService;

    @Inject
    RestMemberForSpecificExceptionService restMemberForSpecificExceptionService;

    @Inject
    MemberRestBeanMapper beanMapper;

    /**
     * getMembers
     * <ul>
     * <li>検索条件に一致するMemberリソースのページ検索 （Pagable利用）</li>
     * </ul>
     */
    @GetMapping(params = "name")
    @ResponseStatus(HttpStatus.OK)
    public Page<MemberResource> getMembers(@Validated MembersSearchQuery query, Pageable pageable) {

        Page<RestMember> page = restMemberService.searchMembers(query.getName(), pageable);

        List<MemberResource> memberResources = new ArrayList<>();
        for (RestMember member : page.getContent()) {
            memberResources.add(beanMapper.map(member));
        }
        Page<MemberResource> responseResource =
                new PageImpl<>(memberResources, pageable, page.getTotalElements());

        return responseResource;
    }

    /**
     * getMembers
     * <ul>
     * <li>Memberリソース一覧完全取得</li>
     * </ul>
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResource> getMembers() {

        List<RestMember> restMembers = restMemberService.findAll();

        List<MemberResource> memberResources = new ArrayList<>();
        for (RestMember member : restMembers) {
            memberResources.add(beanMapper.map(member));
        }

        return memberResources;
    }

    /**
     * getMember
     * <ul>
     * <li>特定のMemberリソースを取得</li>
     * </ul>
     */
    @GetMapping(value = "{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource getMember(@PathVariable("memberId") String memberId,
            UriComponentsBuilder uriBuilder) {

        RestMember member = restMemberService.getMember(memberId);

        MemberResource responseResource = beanMapper.map(member);

        responseResource.addSelf(uriBuilder.path("/members").pathSegment(memberId).build().toUri());

        return responseResource;
    }

    /**
     * postMembers
     * <ul>
     * <li>Memberリソースを追加</li>
     * </ul>
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<MemberResource> postMembers(
            @RequestBody @Validated({PostMembers.class,
                    Default.class}) MemberResource requestedResource,
            UriComponentsBuilder uriBuilder) {

        RestMember creatingMember = beanMapper.map(requestedResource);

        RestMember createdMember = restMemberService.createMember(creatingMember);

        MemberResource responseResource = beanMapper.map(createdMember);

        URI createdUri = uriBuilder.path("/members/{memberId}")
                .buildAndExpand(responseResource.getMemberId()).toUri();

        return ResponseEntity.created(createdUri).body(responseResource);
    }

    /**
     * putMember
     * <ul>
     * <li>特定のMemberリソースを修正</li>
     * </ul>
     */
    @PutMapping(value = "{memberId}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource putMember(@PathVariable("memberId") String memberId,
            @RequestBody @Validated({PutMember.class,
                    Default.class}) MemberResource requestedResource) {

        RestMember updatingMember = beanMapper.map(requestedResource);

        RestMember updatedMember = restMemberService.updateMember(memberId, updatingMember);

        MemberResource responseResource = beanMapper.map(updatedMember);

        return responseResource;
    }

    /**
     * deleteMember
     * <ul>
     * <li>特定のMemberリソースを削除</li>
     * </ul>
     */
    @DeleteMapping(value = "{memberId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMember(@PathVariable("memberId") String memberId) {

        restMemberService.deleteMember(memberId);

    }

    /**
     * callBusinessException
     * <ul>
     * <li>Business Exception 発生させるメソッド</li>
     * </ul>
     */
    @PutMapping(value = "businessExp/{memberId}")
    public void callBusinessException(@PathVariable("memberId") String memberId) {
        restMemberForSpecificExceptionService.callBusinessException(memberId);
    }

    /**
     * callOptimisticFailureException
     * <ul>
     * <li>ObjectOptimisticLockingFailureException Exception 発生させるメソッド</li>
     * </ul>
     */
    @PutMapping(value = "optimisticExp")
    public void callOptimisticFailureException() {
        restMemberForSpecificExceptionService.callOptimisticFailureException("memberId");
    }

    /**
     * callException
     * <ul>
     * <li>通常のException 発生させるメソッド</li>
     * </ul>
     */
    @PutMapping(value = "exp")
    public void callException() {
        restMemberForSpecificExceptionService.callException("memberId");
    }

    /**
     * responseUnknownError
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーのハンドリングで静的なJSONを応答する</li>
     * <li>実際はカスタムエラーを作成し、controllerのリクエストハンドラーから作成したカスタムエラーをサブレットコンテナに通知して、
     * <li>サブレットコンテナ内に(web.xml)カスタムエラーによってはで静的なレスポンスを返すべきでした。
     * <li>既存アプリでは上記とおりで実装するとカスタムエラーはjava.lang.Exceptionに検知されてしまって静的なレスポンスの返却できないです。
     * <li>静的なレスポンスの実装は、リクエストハンドラーからレスポンスエラーコード508 (Unknown Error)をサブレットコンテナに通知して、
     * <li>サブレットコンテナ内に(web.xml)レスポンスエラーコード508に よって静的なレスポンスを返すようにしています。
     * <li>508 Unknown Errorを通知する</li>
     * </ul>
     */
    @GetMapping(value = "unknownError")
    public void responseUnknownError(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Send 508 (Unknown Error) error to servlet container
        response.sendError(508);
    }

    /**
     * responseServiceUnavailable
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーをエラーControllerでハンドリングしエラー応答を行う</li>
     * <li>サーブレットコンテナに通知されていないため、HttpServletResponseのsendErrorを利用</li>
     * <li>505 HTTP Version Not Supportedを通知する</li>
     * </ul>
     */
    @GetMapping(value = "httpVersionNotSupport")
    public void responseServiceUnavailable(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Send 505 (HTTP Version Not Supported) error to servlet container
        response.sendError(505);
    }

    /**
     * responseGatewayTimeout
     * <ul>
     * <li>サーブレットコンテナに通知されたエラーをエラーControllerでハンドリングしエラー応答を行う</li>
     * <li>サーブレットコンテナに通知されていないため、HttpServletResponseのsendErrorを利用</li>
     * <li>504 Gateway Timeoutを通知する</li>
     * </ul>
     */
    @GetMapping(value = "gatewayTimeout")
    public void responseGatewayTimeout(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Send 504 (Gateway Timeout) error to servlet container
        response.sendError(504);
    }

    /**
     * getMember
     * <ul>
     * <li>特定のMemberリソースを取得</li>
     * </ul>
     */
    @JsonView(Summary.class)
    @GetMapping(value = "{memberId}", params = "format=summary")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource getMemberSummary(@PathVariable("memberId") String memberId,
            UriComponentsBuilder uriBuilder) {

        RestMember member = restMemberService.getMember(memberId);

        MemberResource responseResource = beanMapper.map(member);

        responseResource.addSelf(uriBuilder.path("/members").pathSegment(memberId).build().toUri());

        return responseResource;
    }

    /**
     * getMember
     * <ul>
     * <li>特定のMemberリソースを取得</li>
     * </ul>
     */
    @JsonView(Detail.class)
    @GetMapping(value = "{memberId}", params = "format=detail")
    @ResponseStatus(HttpStatus.OK)
    public MemberResource getMemberDetail(@PathVariable("memberId") String memberId,
            UriComponentsBuilder uriBuilder) {

        RestMember member = restMemberService.getMember(memberId);

        MemberResource responseResource = beanMapper.map(member);

        responseResource.addSelf(uriBuilder.path("/members").pathSegment(memberId).build().toUri());

        return responseResource;
    }

    /**
     * getMember
     * <ul>
     * <li>特定のMemberリソースを取得</li>
     * </ul>
     */
    @PostMapping(value = "getMemberWithAdvice")
    @ResponseStatus(HttpStatus.OK)
    public MemberResourceWithAdvice getMemberWithAdvice(
            @RequestBody MemberResourceWithAdvice requestedResource,
            UriComponentsBuilder uriBuilder) {

        String memberId = requestedResource.getMemberId();
        LocalDateTime startDateTime = requestedResource.getStartDateTime();

        RestMember member = restMemberService.getMember(memberId);

        MemberResourceWithAdvice responseResource = beanMapper.mapWithAdvice(member);
        responseResource.setStartDateTime(startDateTime);

        return responseResource;
    }
}
