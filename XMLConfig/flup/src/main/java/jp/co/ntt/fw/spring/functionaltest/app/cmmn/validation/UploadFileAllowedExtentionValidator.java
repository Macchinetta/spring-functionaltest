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
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UploadFileAllowedExtentionValidator
        implements ConstraintValidator<UploadFileAllowedExtention, MultipartFile> {

    private final Set<String> extentions = new HashSet<>();

    @Override
    public void initialize(UploadFileAllowedExtention constraint) {
        for (String extention : constraint.value()) {
            extentions.add(extention.toLowerCase());
        }
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        if (CollectionUtils.isEmpty(extentions)) {
            return true;
        }
        if (multipartFile == null) {
            return true;
        }
        String fileName = multipartFile.getOriginalFilename();
        if (!StringUtils.hasText(fileName)) {
            return true;
        }
        String extention = StringUtils.getFilenameExtension(fileName);
        return StringUtils.hasText(extention) && extentions.contains(extention.toLowerCase());
    }

}
