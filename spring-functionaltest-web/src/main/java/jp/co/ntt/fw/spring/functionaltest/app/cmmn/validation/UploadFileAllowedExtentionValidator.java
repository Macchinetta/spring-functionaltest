/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileAllowedExtentionValidator
                                                implements
                                                ConstraintValidator<UploadFileAllowedExtention, MultipartFile> {

    private final Set<String> extentions = new HashSet<>();

    @Override
    public void initialize(UploadFileAllowedExtention constraint) {
        for (String extention : constraint.value()) {
            extentions.add(extention.toLowerCase());
        }
    }

    @Override
    public boolean isValid(MultipartFile multipartFile,
            ConstraintValidatorContext context) {
        if (extentions.isEmpty()) {
            return true;
        }
        if (multipartFile == null) {
            return true;
        }
        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            return true;
        }
        int extensionSeparatorPosition = multipartFile.getOriginalFilename()
                .lastIndexOf(".");
        String extention = "";
        if (0 <= extensionSeparatorPosition) {
            extention = multipartFile.getOriginalFilename().substring(
                    (extensionSeparatorPosition + 1)).toLowerCase();
        }
        return extentions.contains(extention);
    }

}
