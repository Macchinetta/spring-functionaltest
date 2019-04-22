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
package jp.co.ntt.fw.spring.functionaltest.app.oth2;

public class OAuth2Constants {

    private OAuth2Constants() {
    }

    static final String SYSTEM_ERROR_CODE = "e.sf.oth2.9001";

    static final String URL_SYSTEM_ERROR_PAGE = "/oauth/systemError";

    static final String VIEW_NAME_SYSTEM_ERROR_PAGE = "common/error/systemError";

    static final String ACCESS_DENIED_ERROR_CODE = "e.sf.oth2.9002";

    static final String URL_ACCESS_DENIED_ERROR_PAGE = "/oauth/accessDeniedError";

    static final String VIEW_NAME_ACCESS_DENIED_ERROR_PAGE = "oth2/accessDeniedError";

}
