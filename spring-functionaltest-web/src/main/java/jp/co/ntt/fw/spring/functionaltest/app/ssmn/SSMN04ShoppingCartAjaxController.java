/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.ssmn;

import java.util.Locale;

import javax.inject.Inject;

import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("shopping/cart")
@Controller
public class SSMN04ShoppingCartAjaxController {

    @Inject
    Cart cart;

    @Inject
    SSMN04ShoppingCartHelper shoppingCartHelper;

    @Inject
    MessageSource messageSource;

    @Inject
    Mapper beanMapper;

    @RequestMapping(method = RequestMethod.POST, params = "add")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public CartItemAddResult addItem(CartItemForm form, Locale locale) {

        // カートに商品を追加する
        CartItem cartItem = beanMapper.map(form, CartItem.class);
        shoppingCartHelper.addItem(cartItem, cart);

        // カートに商品を追加したことを通知するメッセージを設定する
        CartItemAddResult result = new CartItemAddResult();
        result.setMessage(messageSource.getMessage("i.sf.ssmn.0002", null,
                locale));

        return result;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public CartItemAddResult handleBindException(BindException e, Locale locale) {
        CartItemAddResult result = new CartItemAddResult();
        if (e.hasFieldErrors()) {
            FieldError error = e.getFieldError();
            result.setMessage(messageSource.getMessage(error, locale));
            return result;
        }
        if (e.hasGlobalErrors()) {
            ObjectError error = e.getGlobalError();
            result.setMessage(messageSource.getMessage(error, locale));
            return result;
        }
        return result;
    }
}
