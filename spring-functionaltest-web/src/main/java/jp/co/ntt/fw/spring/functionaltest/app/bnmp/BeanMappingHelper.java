/*
 * Copyright(c) 2014-2017 NTT Corporation.
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
