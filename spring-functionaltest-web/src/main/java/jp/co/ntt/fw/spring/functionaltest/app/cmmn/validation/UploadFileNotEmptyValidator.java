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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileNotEmptyValidator
        implements ConstraintValidator<UploadFileNotEmpty, MultipartFile> {

    @Override
    public void initialize(UploadFileNotEmpty constraint) {
        // The default implementation
    }

    /**
     * アップロードファイルの有効確認 以下の場合はファイルが無効という扱いでfalseを返却、以外は有効扱いでtrueを返却。 ・アップロードされたファイルが空
     * ・マルチパート形式でファイルが選択されていない ・選択されたファイルにコンテンツがない ※UploadFileRequiredValidatorと併用して利用することを想定。
     * @param multipartFile 検証するアップロードファイル
     * @param context ValidatorContext
     * @see UploadFileRequiredValidator#isValid(MultipartFile, ConstraintValidatorContext)
     * @return MultipartFileとして有効な場合はtrue、無効の場合はfalse
     */
    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (multipartFile == null || !StringUtils.hasLength(multipartFile.getOriginalFilename())) {
            return true;
        }
        return !multipartFile.isEmpty();
    }

}
