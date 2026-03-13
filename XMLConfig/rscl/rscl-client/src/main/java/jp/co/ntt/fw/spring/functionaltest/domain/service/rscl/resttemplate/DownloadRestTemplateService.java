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
package jp.co.ntt.fw.spring.functionaltest.domain.service.rscl.resttemplate;

import java.util.List;

import jp.co.ntt.fw.spring.functionaltest.app.cmmn.model.User;

/**
 * ファイルダウンロード確認用のServiceインターフェース。
 */
public interface DownloadRestTemplateService {

    /**
     * ダウンロード（byte形式）の確認用。
     * 
     * @return ユーザ情報
     */
    List<User> byteDownload();

    /**
     * ダウンロード（Resource形式）の確認用。
     * 
     * @return ユーザ情報
     */
    List<User> resourceDownload();

    /**
     * ダウンロード（InputStream形式）の正常確認用。
     * 
     * @return ユーザ情報
     */
    List<User> inputStreamDownload();

    /**
     * ダウンロード（InputStream形式）のエラー確認用。
     * 
     * @return ユーザ情報
     */
    List<User> inputStreamDownloadError();

    /**
     * ダウンロード（ResponseEntity形式）の正常確認用。
     * 
     * @return ユーザ情報
     */
    List<User> inputStreamResponse();

    /**
     * ダウンロード（ResponseEntity形式）の異常確認用。
     * 
     * @return ユーザ情報
     */
    List<User> inputStreamResponseError();

}