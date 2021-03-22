/*
 * Copyright 2014-2018 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class UploadFileAllowedExtentionValidator implements
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
