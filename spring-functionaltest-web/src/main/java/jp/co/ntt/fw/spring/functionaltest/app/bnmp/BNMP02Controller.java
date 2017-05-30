/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import javax.inject.Inject;

import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.CustomConverterDto;

import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("bnmp")
@Controller
public class BNMP02Controller {

    @Inject
    Mapper beanMapper;

    @ModelAttribute
    public CustomConverterForm setUpForm() {
        return new CustomConverterForm();
    }

    @RequestMapping(value = "0201/001", method = RequestMethod.GET)
    public String handle02001(Model model) {
        return "bnmp/customConverter";
    }

    @RequestMapping(value = "customConverter", method = RequestMethod.POST, params = "copyWithCustomConverter")
    public String handleCustomConverter(Model model, CustomConverterForm form) {

        // コピー元Bean作成後フィールドに値を設定し、Mapperを使用してコピー先Beanを新規作成する
        CustomConverterDto destinationBean = beanMapper.map(form,
                CustomConverterDto.class);

        model.addAttribute("resultBean", destinationBean);

        return "bnmp/showCustomConverterResult";
    }

}
