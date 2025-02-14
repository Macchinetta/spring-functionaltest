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

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UploadFileRequiredValidator
        implements ConstraintValidator<UploadFileRequired, MultipartFile> {

    @Override
    public void initialize(UploadFileRequired constraint) {
        // The default implementation
    }

    /**
     * アップロードファイルの存在確認 ファイルがnull以外で且つ、ファイル名の指定がある場合はtrue ファイルがnullの場合、ファイル名が空の場合はfalse
     * @param multipartFile 検証するアップロードファイル
     * @param context ValidatorContext
     * @see UploadFileNotEmptyValidator#isValid(MultipartFile, ConstraintValidatorContext)
     * @return ファイルが存在する場合はtrue、存在しない場合はfalse
     */
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        return multipartFile != null && StringUtils.hasLength(multipartFile.getOriginalFilename());
    }

}
