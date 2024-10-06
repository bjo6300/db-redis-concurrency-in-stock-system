package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    // @Transactional(propagation = Propagation.REQUIRES_NEW)
    // Transaction을 사용하면 decrease가 완료되고 트랜잭션이 종료되어 db에 반영하기 전에 다른 쓰레드가 quantity를 가져가는 상황이 발생해 재고 수량이 맞지 않는다.
    // 한 프로세스 안에서만 동시성이 보장된다. -> 서버가 여러 대인 경우 동시성을 보장하지 못한다.
    public synchronized void decrease(Long id, Long quantity) {
        Stock stock = stockRepository.findById(id).orElseThrow();

        stock.decrease(quantity);

        stockRepository.saveAndFlush(stock);
    }
}
