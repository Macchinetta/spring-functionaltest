/*
 * Copyright(c) 2014-2017 NTT Corporation.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation;

import java.util.Collection;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileNotEmptyForCollectionValidator
                                                     implements
                                                     ConstraintValidator<UploadFileNotEmpty, Collection<MultipartFile>> {

    private final UploadFileNotEmptyValidator validator = new UploadFileNotEmptyValidator();

    @Override
    public void initialize(UploadFileNotEmpty constraintAnnotation) {
        validator.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Collection<MultipartFile> values,
            ConstraintValidatorContext context) {
        for (MultipartFile file : values) {
            if (!validator.isValid(file, context)) {
                return false;
            }
        }
        return true;
    }

}
