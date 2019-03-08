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
package jp.co.ntt.fw.spring.functionaltest.domain.service.excn;

import org.springframework.transaction.annotation.Transactional;

import jp.co.ntt.fw.spring.functionaltest.domain.model.Stock;

// DB2以外では使用しないので、component-scanさせない
//@Service
@Transactional
// DB2では、`SELECT FOR UPDATE`ステートメントに対する`NOWAIT`オプションが提供されておらず、
// 以下のように分離レベルRS(Repeatable Read)と`CURRENT LOCK TIMEOUT`値の操作で即時タイムアウトを実現する。
//
// `SET CURRENT LOCK TIMEOUT NOT WAIT;`
// `SELECT * FROM table FOR UPDATE WITH RS` * n
// `SET CURRENT LOCK TIMEOUT NULL;`
//
// タイムアウト値についてはステートメント単位ではなくセッション単位での操作となるため、
// 当該セッション中で発行するすべてのステートメントに影響がある点に注意が必要である。
public class StockPessimisticLockServiceImplForDB2 extends
                                                   StockPessimisticLockServiceImpl {

    @Override
    public Stock buy(Stock stock, int purchasingQuantity) {
        // 即時タイムアウトさせるため、タイムアウト値をセット
        stockRepository.setCurrentLockTimeoutNotWait();
        try {
            return super.buy(stock, purchasingQuantity);
        } finally {
            // タイムアウト値をリセット
            stockRepository.setCurrentLockTimeoutDefault();
        }
    }

}
