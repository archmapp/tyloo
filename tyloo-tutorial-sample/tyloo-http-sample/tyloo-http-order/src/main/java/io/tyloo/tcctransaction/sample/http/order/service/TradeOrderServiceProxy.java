package io.tyloo.tcctransaction.sample.http.order.service;

import io.tyloo.api.Propagation;
import io.tyloo.api.Tyloo;
import io.tyloo.api.TylooContext;
import io.tyloo.tcctransaction.context.MethodTylooContextLoader;
import io.tyloo.tcctransaction.sample.http.capital.api.CapitalTradeOrderService;
import io.tyloo.tcctransaction.sample.http.capital.api.dto.CapitalTradeOrderDto;
import io.tyloo.tcctransaction.sample.http.redpacket.api.RedPacketTradeOrderService;
import io.tyloo.tcctransaction.sample.http.redpacket.api.dto.RedPacketTradeOrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 *
 * @Author:Zh1Cheung 945503088@qq.com
 * @Date: 10:07 2019/12/5
 *
 */
@Component
public class TradeOrderServiceProxy {

    @Autowired
    CapitalTradeOrderService capitalTradeOrderService;

    @Autowired
    RedPacketTradeOrderService redPacketTradeOrderService;

    /*the propagation need set Propagation.SUPPORTS,otherwise the recover doesn't work,
      The default value is Propagation.REQUIRED, which means will begin new transaction when recover.
    */
    @Tyloo(propagation = Propagation.SUPPORTS, confirmMethod = "record", cancelMethod = "record", tylooContextLoader = MethodTylooContextLoader.class)
    public String record(TylooContext transactionContext, CapitalTradeOrderDto tradeOrderDto) {
        return capitalTradeOrderService.record(transactionContext, tradeOrderDto);
    }

    @Tyloo(propagation = Propagation.SUPPORTS, confirmMethod = "record", cancelMethod = "record", tylooContextLoader = MethodTylooContextLoader.class)
    public String record(TylooContext transactionContext, RedPacketTradeOrderDto tradeOrderDto) {
        return redPacketTradeOrderService.record(transactionContext, tradeOrderDto);
    }
}
