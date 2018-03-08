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
package jp.co.ntt.fw.spring.functionaltest.app.bnmp;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.EmailDto;
import jp.co.ntt.fw.spring.functionaltest.domain.service.bnmp.MailAddressDto;

import org.springframework.stereotype.Component;

@Component
public class BeanMappingHelper {

    public List<String> getDestinationString() {
        List<String> list = new ArrayList<String>();

        list.add("hoge@example.com");
        list.add("fuga@example.com");
        list.add("moge@example.com");

        return list;
    }

    public List<EmailDto> getDestinationListWithEmailDto() {
        List<EmailDto> destinationList = new ArrayList<EmailDto>();

        List<String> desitinationStringList = getDestinationString();
        for (String destinationString : desitinationStringList) {
            destinationList.add(new EmailDto(destinationString));
        }
        return destinationList;
    }

    public List<MailAddressDto> getDestinationListWithMailAddressDto() {
        List<MailAddressDto> destinationList = new ArrayList<MailAddressDto>();

        List<String> desitinationStringList = getDestinationString();
        for (String destinationString : desitinationStringList) {
            destinationList.add(new MailAddressDto(destinationString));
        }
        return destinationList;
    }

    public Set<EmailDto> getDestinationSetWithEmailDto() {
        Set<EmailDto> destinationSet = new LinkedHashSet<EmailDto>();

        List<String> desitinationStringList = getDestinationString();
        for (String destinationString : desitinationStringList) {
            destinationSet.add(new EmailDto(destinationString));
        }
        return destinationSet;
    }

    public List<Boolean> checkSameObjectRefference(List<?> sourceList,
            List<?> destinationList) {
        // コピー元とコピー先のサイズは同じ前提
        List<Boolean> objectRefferenceList = new ArrayList<Boolean>();
        int listSize = sourceList.size();
        for (int i = 0; i < listSize; i++) {
            // オブジェクト参照先(アドレス)が一致するかチェック
            if (sourceList.get(i) == destinationList.get(i)) {
                objectRefferenceList.add(new Boolean(true));
            } else {
                objectRefferenceList.add(new Boolean(false));
            }
        }
        return objectRefferenceList;
    }

    public Boolean checkSameCollectionRefference(List<?> sourceList,
            List<?> destinationList) {
        // Collection(List等)のオブジェクト参照先が一致するかチェック
        if (sourceList == destinationList) {
            return true;
        }
        return false;
    }
}
