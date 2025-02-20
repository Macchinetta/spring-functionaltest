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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.terasoluna.gfw.common.message.ResultMessages;

import com.github.dozermapper.core.Mapper;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Car;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountCopyByReferenceDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountDifferenceCollectionDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountDifferenceTypeDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountNonCumulativeDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.AccountRemoveOrphansDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.CarBidirectionalDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.CarService;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.CarUnidirectionalDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.DifferenceFieldDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.EmailDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.NestedFieldMapperDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.SameNameBeanMapperDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.SameNameDifferenceTypeDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserCopyByReferenceDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserDifferenceCollectionDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserDifferenceTypeDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserNonCumulativeDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.UserRemoveOrphansDto;

@RequestMapping("bnmp")
@Controller
public class BNMP01Controller {

    @Inject
    Mapper beanMapper;

    @Inject
    BeanMappingHelper beanMappingHelper;

    @Inject
    CarService carService;

    @RequestMapping(value = "0101/001", method = RequestMethod.GET)
    public String handle01001(Model model, SameNameBeanMapperForm form) {
        return "bnmp/sameNameSameTypeMapping";
    }

    @RequestMapping(value = "0102/001", method = RequestMethod.GET)
    public String handle02001(Model model, BeanMapperForm form) {
        return "bnmp/sameNameDifferenceTypeMapping";
    }

    @RequestMapping(value = "0103/001", method = RequestMethod.GET)
    public String handle03001(Model model, BeanMapperForm form) {
        return "bnmp/differenceNameMapping";
    }

    @RequestMapping(value = "0104/001", method = RequestMethod.GET)
    public String handle04001(Model model, CarForm form) {
        return "bnmp/unidirectionalBidirectionalMappingCreate";
    }

    @RequestMapping(value = "0104/002", method = RequestMethod.GET)
    public String handle04002(Model model, CarForm form) {
        return "bnmp/unidirectionalBidirectionalMappingCreate";
    }

    @RequestMapping(value = "0104/003", method = RequestMethod.GET)
    public String handle04003(Model model, CarForm form) {
        return "bnmp/unidirectionalBidirectionalMappingCreate";
    }

    @RequestMapping(value = "0104/004", method = RequestMethod.GET)
    public String handle04004(Model model, CarForm form) {
        return "bnmp/unidirectionalBidirectionalMappingCreate";
    }

    @RequestMapping(value = "0104/005", method = RequestMethod.GET)
    public String handle04005(Model model, CarClassBaseOneWayMappingForm form) {
        return "bnmp/unidirectionalClassBaseMappingCreate";
    }

    @RequestMapping(value = "0104/006", method = RequestMethod.GET)
    public String handle04006(Model model, CarSameNameFieldOneWayMappingForm form) {
        return "bnmp/unidirectionalSameNameFieldMappingCreate";
    }

    @RequestMapping(value = "0105/001", method = RequestMethod.GET)
    public String handle05001(Model model, BeanMapperForm form) {
        return "bnmp/nestedFieldMapping";
    }

    @RequestMapping(value = "0106/001", method = RequestMethod.GET)
    public String handle06001(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/002", method = RequestMethod.GET)
    public String handle06002(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/003", method = RequestMethod.GET)
    public String handle06003(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/004", method = RequestMethod.GET)
    public String handle06004(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/005", method = RequestMethod.GET)
    public String handle06005(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/006", method = RequestMethod.GET)
    public String handle06006(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/007", method = RequestMethod.GET)
    public String handle06007(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/008", method = RequestMethod.GET)
    public String handle06008(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/009", method = RequestMethod.GET)
    public String handle06009(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "0106/010", method = RequestMethod.GET)
    public String handle06010(Model model, AccountForm form) {
        model.addAttribute("destinationList", beanMappingHelper.getDestinationString());
        return "bnmp/collectionTypeFieldMapping";
    }

    @RequestMapping(value = "sameNameSameTypeMapping", method = RequestMethod.POST,
            params = "copySameNameSameTypeField")
    public String handleSameNameSameTypeMapping(Model model, SameNameBeanMapperForm form) {

        // コピー元Bean、コピー先Bean作成後、コピー先フィールドにコピー元フィールドとは異なる値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        SameNameBeanMapperDto destinationBean = new SameNameBeanMapperDto();
        destinationBean.setMyoji("DestinationMyoji");
        destinationBean.setLastName("DestinationLastName");
        destinationBean.setAge(18);
        destinationBean.setBirthDate(LocalDate.of(1950, 01, 01));
        destinationBean.setSex("M");
        beanMapper.map(form, destinationBean);
        model.addAttribute("resultBean", destinationBean);

        if (destinationBean.getBirthDate() != null) {
            model.addAttribute("birthDate", destinationBean.getBirthDate()
                    .format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
        }

        return "bnmp/showBeanMappingSameFieldResult";
    }

    @RequestMapping(value = "sameNameDifferenceTypeMapping", method = RequestMethod.POST,
            params = "copySameNameDifferenceTypeBean")
    public String handleSameNameDifferenceTypeMapping(Model model, BeanMapperForm form) {

        // コピー元Bean作成後フィールドに値を設定し、Mapperを使用してコピー先Beanを新規作成する
        SameNameDifferenceTypeDto destinationBean =
                beanMapper.map(form, SameNameDifferenceTypeDto.class);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingSameNameDifferenceTypeResult";
    }

    @RequestMapping(value = "differenceNameMapping", method = RequestMethod.POST,
            params = "copyDifferenceNameField")
    public String handleDifferenceNameMapping(Model model, BeanMapperForm form) {

        // コピー元Bean作成後フィールドに値を設定し、Mapperを使用してコピー先Beanを新規作成する
        DifferenceFieldDto destinationBean = beanMapper.map(form, DifferenceFieldDto.class);

        model.addAttribute("resultBean", destinationBean);

        if (destinationBean.getTanjobi() != null) {
            model.addAttribute("tanjobi",
                    destinationBean.getTanjobi().format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
        }

        return "bnmp/showBeanMappingDifferenceFieldResult";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping", method = RequestMethod.POST,
            params = "copyUnidirectionalBean")
    public String handleUnidirectionalMappingCarCreate(CarForm form,
            RedirectAttributes redirectAttributes) {

        // Mapperを使用して、コピー元Beanからコピー先Beanへフィールドの値をコピーする。
        CarUnidirectionalDto destinationBean = beanMapper.map(form, CarUnidirectionalDto.class);

        // 正方向マッピング結果をDBに登録する。
        Car car = carService.createCar(destinationBean);

        redirectAttributes.addFlashAttribute(car);

        return "redirect:/bnmp/unidirectionalBidirectionalMapping/unidirectional?complete";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping/unidirectional",
            method = RequestMethod.GET, params = "complete")
    public String handleUnidirectionalMappingCarCreateComplete(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.bm.0001");
        model.addAttribute(messages);
        return "bnmp/unidirectionalBidirectionalMappingComplete";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping/car/{carId}",
            method = RequestMethod.GET, params = "unidirectional")
    public String handleUnidirectionalMappingCarDetail(Model model,
            @PathVariable("carId") String carId) {

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する
        // 正方向マッピング結果を反映したコピー先BeanをDBから取得する。
        CarUnidirectionalDto destinationBean = carService.getCarWithUnidirectionalDto(carId);
        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        CarForm carForm = beanMapper.map(destinationBean, CarForm.class);

        model.addAttribute(carForm);

        return "bnmp/unidirectionalBidirectionalMappingDetail";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping", method = RequestMethod.POST,
            params = "copyBidirectionalBean")
    public String handleBidirectionalMappingCreate(CarForm form,
            RedirectAttributes redirectAttributes) {

        // Mapperを使用して、コピー元Beanからコピー先Beanへフィールドの値をコピーする。
        CarBidirectionalDto destinationBean = beanMapper.map(form, CarBidirectionalDto.class);

        // 正方向マッピング結果をDBに登録する。
        Car car = carService.createCar(destinationBean);

        redirectAttributes.addFlashAttribute(car);

        return "redirect:/bnmp/unidirectionalBidirectionalMapping/bidirectional?complete";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping/bidirectional",
            method = RequestMethod.GET, params = "complete")
    public String handleBidirectionalMappingCarCreateComplete(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.bm.0001");
        model.addAttribute(messages);
        return "bnmp/unidirectionalBidirectionalMappingComplete";
    }

    @RequestMapping(value = "unidirectionalBidirectionalMapping/car/{carId}",
            method = RequestMethod.GET, params = "bidirectional")
    public String handleBidirectionalMappingCarDetail(Model model,
            @PathVariable("carId") String carId) {

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する
        // 正方向マッピング結果を反映したコピー先BeanをDBから取得する。
        CarBidirectionalDto destinationBean = carService.getCarWithBidirectionalDto(carId);
        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされること。
        CarForm carForm = beanMapper.map(destinationBean, CarForm.class);

        model.addAttribute(carForm);

        return "bnmp/unidirectionalBidirectionalMappingDetail";
    }

    @RequestMapping(value = "unidirectionalClassBaseMapping", method = RequestMethod.POST,
            params = "copyUnidirectionalBean")
    public String handleUnidirectionalClassBaseMappingCarCreate(CarClassBaseOneWayMappingForm form,
            RedirectAttributes redirectAttributes) {

        // Mapperを使用して、コピー元Beanからコピー先Beanへフィールドの値をコピーする。
        CarUnidirectionalDto destinationBean = beanMapper.map(form, CarUnidirectionalDto.class);

        // 正方向マッピング結果をDBに登録する。
        Car car = carService.createCar(destinationBean);

        redirectAttributes.addFlashAttribute(car);

        return "redirect:/bnmp/unidirectionalClassBaseMapping/unidirectional?complete";
    }

    @RequestMapping(value = "unidirectionalClassBaseMapping/unidirectional",
            method = RequestMethod.GET, params = "complete")
    public String handleUnidirectionalClassBaseMappingCarCreateComplete(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.bm.0001");
        model.addAttribute(messages);
        return "bnmp/unidirectionalClassBaseMappingComplete";
    }

    @RequestMapping(value = "unidirectionalClassBaseMapping/car/{carId}",
            method = RequestMethod.GET, params = "unidirectional")
    public String handleUnidirectionalClassBaseMappingCarDetail(Model model,
            @PathVariable("carId") String carId) {

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する
        // 正方向マッピング結果を反映したコピー先BeanをDBから取得する。
        CarUnidirectionalDto destinationBean = carService.getCarWithUnidirectionalDto(carId);
        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        CarClassBaseOneWayMappingForm carForm =
                beanMapper.map(destinationBean, CarClassBaseOneWayMappingForm.class);

        model.addAttribute(carForm);

        return "bnmp/unidirectionalClassBaseMappingDetail";
    }

    @RequestMapping(value = "unidirectionalSameNameFieldMapping", method = RequestMethod.POST,
            params = "copyUnidirectionalBean")
    public String handleUnidirectionalSameNameFieldMappingCarCreate(
            CarSameNameFieldOneWayMappingForm form, RedirectAttributes redirectAttributes) {

        // Mapperを使用して、コピー元Beanからコピー先Beanへフィールドの値をコピーする。
        CarUnidirectionalDto destinationBean = beanMapper.map(form, CarUnidirectionalDto.class);

        // 正方向マッピング結果をDBに登録する。
        Car car = carService.createCar(destinationBean);

        redirectAttributes.addFlashAttribute(car);

        return "redirect:/bnmp/unidirectionalSameNameFieldMapping/unidirectional?complete";
    }

    @RequestMapping(value = "unidirectionalSameNameFieldMapping/unidirectional",
            method = RequestMethod.GET, params = "complete")
    public String handleUnidirectionalSameNameFieldMappingCarCreateComplete(Model model) {
        ResultMessages messages = ResultMessages.info().add("i.sf.bm.0001");
        model.addAttribute(messages);
        return "bnmp/unidirectionalSameNameFieldMappingComplete";
    }

    @RequestMapping(value = "unidirectionalSameNameFieldMapping/car/{carId}",
            method = RequestMethod.GET, params = "unidirectional")
    public String handleUnidirectionalSameNameFieldMappingCarDetail(Model model,
            @PathVariable("carId") String carId) {

        // コピー先Bean作成後フィールドに値を設定し、Mapperを使用してコピー元Beanを新規作成する
        // 正方向マッピング結果を反映したコピー先BeanをDBから取得する。
        CarUnidirectionalDto destinationBean = carService.getCarWithUnidirectionalDto(carId);
        // コピー先Beanからコピー元Beanへ、フィールドの値がコピーされないこと。
        CarSameNameFieldOneWayMappingForm carForm =
                beanMapper.map(destinationBean, CarSameNameFieldOneWayMappingForm.class);

        model.addAttribute(carForm);

        return "bnmp/unidirectionalSameNameFieldMappingDetail";
    }

    @RequestMapping(value = "nestedFieldMapping", method = RequestMethod.POST,
            params = "copyNestedFieldBean")
    public String handleNestedFieldMapping(Model model, BeanMapperForm form) {

        // コピー元Bean作成後フィールドに値を設定し、Mapperを使用してコピー先Beanを新規作成する
        NestedFieldMapperDto destinationBean = beanMapper.map(form, NestedFieldMapperDto.class);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingNestedFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldCumulativeListToListSameType")
    public String handleCollectionTypeFieldSameFieldCumulativeListToListSameTypeMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        AccountDto destinationBean = new AccountDto();
        destinationBean.setEmails(beanMappingHelper.getDestinationListWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeSameFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldCumulativeListToListDifferenceType")
    public String handleCollectionTypeFieldSameFieldCumulativeListToListDifferenceTypeMapping(
            Model model, AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        AccountDifferenceTypeDto destinationBean = new AccountDifferenceTypeDto();
        destinationBean.setEmails(beanMappingHelper.getDestinationListWithMailAddressDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeSameFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldCumulativeListToSet")
    public String handleCollectionTypeFieldSameFieldCumulativeListToSetMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        AccountDifferenceCollectionDto destinationBean = new AccountDifferenceCollectionDto();
        destinationBean.setEmails(beanMappingHelper.getDestinationSetWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeSameFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldCumulativeListToListSameType")
    public String handleCollectionTypeFieldDifferenceFieldCumulativeListToListSameTypeMapping(
            Model model, AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        UserDto destinationBean = new UserDto();
        destinationBean.setEmailAddresses(beanMappingHelper.getDestinationListWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldCumulativeListToListDifferenceType")
    public String handleCollectionTypeFieldDifferenceFieldCumulativeListToListDifferenceTypeMapping(
            Model model, AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        UserDifferenceTypeDto destinationBean = new UserDifferenceTypeDto();
        destinationBean.setEmailAddresses(beanMappingHelper.getDestinationListWithMailAddressDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldCumulativeListToSet")
    public String handleCollectionTypeFieldDifferenceFieldCumulativeListToSetMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複なしで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        UserDifferenceCollectionDto destinationBean = new UserDifferenceCollectionDto();
        destinationBean.setEmailAddresses(beanMappingHelper.getDestinationSetWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldNonCumulative")
    public String handleCollectionTypeFieldSameFieldNonCumulativeMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        AccountNonCumulativeDto destinationBean = new AccountNonCumulativeDto();
        destinationBean.setEmails(beanMappingHelper.getDestinationListWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeSameFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldNonCumulativeDeepCopy")
    public String handleCollectionTypeFieldSameFieldNonCumulativeDeepCopyMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        // コピー先List
        List<EmailDto> desitinationList = beanMappingHelper.getDestinationListWithEmailDto();
        AccountRemoveOrphansDto destinationBean = new AccountRemoveOrphansDto();
        destinationBean.setEmails(desitinationList);

        beanMapper.map(form, destinationBean);

        // 同じコレクションを参照しているか確認(コピー元Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceFormTo = beanMappingHelper
                .checkSameCollectionRefference(form.getEmails(), destinationBean.getEmails());
        // 同じコレクションを参照しているか確認(コピー先Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceDestTo = beanMappingHelper
                .checkSameCollectionRefference(desitinationList, destinationBean.getEmails());
        // 同じオブジェクト参照をしているか確認
        List<Boolean> objectRefferenceList = beanMappingHelper
                .checkSameObjectRefference(form.getEmails(), destinationBean.getEmails());

        model.addAttribute("resultBean", destinationBean);
        model.addAttribute("collectionRefferenceFormTo", isSameCollectionRefferenceFormTo);
        model.addAttribute("collectionRefferenceDestTo", isSameCollectionRefferenceDestTo);
        model.addAttribute("objectRefference", objectRefferenceList);

        return "bnmp/showBeanMappingCollectionTypeSameFieldWithObjectResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copySameFieldNonCumulativeShallowCopy")
    public String handleCollectionTypeFieldSameFieldNonCumulativeShallowCopyMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        // コピー先List
        List<EmailDto> desitinationList = beanMappingHelper.getDestinationListWithEmailDto();
        AccountCopyByReferenceDto destinationBean = new AccountCopyByReferenceDto();
        destinationBean.setEmails(desitinationList);

        beanMapper.map(form, destinationBean);

        // 同じコレクションを参照しているか確認(コピー元Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceFormTo = beanMappingHelper
                .checkSameCollectionRefference(form.getEmails(), destinationBean.getEmails());
        // 同じコレクションを参照しているか確認(コピー先Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceDestTo = beanMappingHelper
                .checkSameCollectionRefference(desitinationList, destinationBean.getEmails());
        // 同じオブジェクト参照をしているか確認
        List<Boolean> objectRefferenceList = beanMappingHelper
                .checkSameObjectRefference(form.getEmails(), destinationBean.getEmails());

        model.addAttribute("resultBean", destinationBean);
        model.addAttribute("collectionRefferenceFormTo", isSameCollectionRefferenceFormTo);
        model.addAttribute("collectionRefferenceDestTo", isSameCollectionRefferenceDestTo);
        model.addAttribute("objectRefference", objectRefferenceList);

        return "bnmp/showBeanMappingCollectionTypeSameFieldWithObjectResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldNonCumulative")
    public String handleCollectionTypeFieldDifferenceFieldNonCumulativeMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        UserNonCumulativeDto destinationBean = new UserNonCumulativeDto();
        destinationBean.setEmailAddresses(beanMappingHelper.getDestinationListWithEmailDto());

        beanMapper.map(form, destinationBean);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldNonCumulativeDeepCopy")
    public String handleCollectionTypeFieldDifferenceFieldNonCumulativeDeepCopyMapping(Model model,
            AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        // コピー先List
        List<EmailDto> desitinationList = beanMappingHelper.getDestinationListWithEmailDto();
        UserRemoveOrphansDto destinationBean = new UserRemoveOrphansDto();
        destinationBean.setEmailAddresses(desitinationList);

        beanMapper.map(form, destinationBean);

        // 同じコレクションを参照しているか確認(コピー元Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceFormTo = beanMappingHelper.checkSameCollectionRefference(
                form.getEmails(), destinationBean.getEmailAddresses());
        // 同じコレクションを参照しているか確認(コピー先Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceDestTo = beanMappingHelper.checkSameCollectionRefference(
                desitinationList, destinationBean.getEmailAddresses());
        // 同じオブジェクト参照をしているか確認
        List<Boolean> objectRefferenceList = beanMappingHelper
                .checkSameObjectRefference(form.getEmails(), destinationBean.getEmailAddresses());

        model.addAttribute("resultBean", destinationBean);
        model.addAttribute("collectionRefferenceFormTo", isSameCollectionRefferenceFormTo);
        model.addAttribute("collectionRefferenceDestTo", isSameCollectionRefferenceDestTo);
        model.addAttribute("objectRefference", objectRefferenceList);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldWithObjectResult";
    }

    @RequestMapping(value = "collectionTypeFieldMapping", method = RequestMethod.POST,
            params = "copyDifferenceFieldNonCumulativeShallowCopy")
    public String handleCollectionTypeFieldDifferenceFieldNonCumulativeShallowCopyMapping(
            Model model, AccountForm form) {

        // コピー元Bean、コピー先Bean作成後フィールドに重複ありで値を設定し、Mapperを使用してコピー元Beanからコピー先Beanへフィールドをコピーする。
        // コピー先List
        List<EmailDto> desitinationList = beanMappingHelper.getDestinationListWithEmailDto();
        UserCopyByReferenceDto destinationBean = new UserCopyByReferenceDto();
        destinationBean.setEmailAddresses(desitinationList);

        beanMapper.map(form, destinationBean);

        // 同じコレクションを参照しているか確認(コピー元Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceFormTo = beanMappingHelper.checkSameCollectionRefference(
                form.getEmails(), destinationBean.getEmailAddresses());
        // 同じコレクションを参照しているか確認(コピー先Beanとコピー結果Listとの比較)
        Boolean isSameCollectionRefferenceDestTo = beanMappingHelper.checkSameCollectionRefference(
                desitinationList, destinationBean.getEmailAddresses());
        // 同じオブジェクト参照をしているか確認
        List<Boolean> objectRefferenceList = beanMappingHelper
                .checkSameObjectRefference(form.getEmails(), destinationBean.getEmailAddresses());

        model.addAttribute("resultBean", destinationBean);
        model.addAttribute("collectionRefferenceFormTo", isSameCollectionRefferenceFormTo);
        model.addAttribute("collectionRefferenceDestTo", isSameCollectionRefferenceDestTo);
        model.addAttribute("objectRefference", objectRefferenceList);

        return "bnmp/showBeanMappingCollectionTypeDifferenceFieldWithObjectResult";
    }
}
