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
package jp.co.ntt.fw.spring.functionaltest.app.flup;

import java.io.Serializable;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileAllowedExtention;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileMaxSize;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileNotEmpty;
import jp.co.ntt.fw.spring.functionaltest.app.cmmn.validation.UploadFileRequired;

import org.springframework.web.multipart.MultipartFile;

public class SingleUploadForm implements Serializable {

    private static final long serialVersionUID = 1L;

    public interface UploadFileMaxSize250byte {
    }

    public interface UploadFileAllowJsp {
    }

    @UploadFileRequired
    @UploadFileNotEmpty
    @UploadFileMaxSize.List({ @UploadFileMaxSize,
            @UploadFileMaxSize(value = 250, groups = UploadFileMaxSize250byte.class) })
    @UploadFileAllowedExtention.List({ @UploadFileAllowedExtention("txt"),
            @UploadFileAllowedExtention(value = "jsp", groups = UploadFileAllowJsp.class) })
    private transient MultipartFile multipartFile;

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

}
