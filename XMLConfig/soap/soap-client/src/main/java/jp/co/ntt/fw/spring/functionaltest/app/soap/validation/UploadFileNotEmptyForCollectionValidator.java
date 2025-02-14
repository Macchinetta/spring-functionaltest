/*
 * Copyright(c) 2025 NTT Corporation.
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
package jp.co.ntt.fw.spring.functionaltest.app.soap.validation;

import java.util.Collection;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UploadFileNotEmptyForCollectionValidator
        implements ConstraintValidator<UploadFileNotEmpty, Collection<MultipartFile>> {

    private final UploadFileNotEmptyValidator validator = new UploadFileNotEmptyValidator();

    @Override
    public void initialize(UploadFileNotEmpty constraintAnnotation) {
        validator.initialize(constraintAnnotation);
    }

    /**
     * 複数アップロードファイルの有効確認
     * @param values 検証するアップロードファイルのリスト
     * @param context ValidatorContext
     * @see UploadFileNotEmptyValidator#isValid(MultipartFile, ConstraintValidatorContext)
     * @return MultipartFileとして有効な場合はtrue、無効の場合はfalse
     */
    @Override
    public boolean isValid(Collection<MultipartFile> values, ConstraintValidatorContext context) {
        for (MultipartFile file : values) {
            if (!validator.isValid(file, context)) {
                return false;
            }
        }
        return true;
    }

}
