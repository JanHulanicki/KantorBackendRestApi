package com.app.kantor.service;

import com.app.kantor.domain.cart.CartStock;
import com.app.kantor.domain.cartproduct.CartCryptoProduct;
import com.app.kantor.domain.cartproduct.CartStockProduct;
import com.app.kantor.domain.stock.Stock;
import com.app.kantor.domain.stock.StockDto;
import com.app.kantor.exception.*;
import com.app.kantor.mapper.StockMapper;
import com.app.kantor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartStockService {
    @Autowired
    CartStockRepository cartStockRepository;
    @Autowired
    CartStockProductRepository cartStockProductRepository;
    @Autowired
    StockRepository stockRepository;
    @Autowired
    StockMapper stockMapper;

    public CartStock createCartStock(final CartStock cartStock) {
        return cartStockRepository.save(cartStock);
    }

    public void deleteCartStock(final Long cartId) {
        cartStockRepository.deleteById(cartId);
    }


    public List<StockDto> getStockFromCartStock(final Long cartId) throws  CartStockNotFoundException {
        if (cartStockRepository.findById(cartId).isPresent()) {
            CartStock cartStock = cartStockRepository.findById(cartId).orElseThrow(CartStockNotFoundException::new);
            List<Stock> stockList = cartStock.getCartStockProducts().stream()
                    .map(CartStockProduct::getStockProduct).collect(Collectors.toList());
            return stockMapper.mapToStockDtoList(stockList);
        } else {
            throw new CartStockNotFoundException();
        }
    }

    public void addStockToCartStockProduct(final Long cartStockProductsId,final Long cartStockId, final Long stockId, Double amount) throws  StockNotFoundException, CartStockProductNotFoundException {
        if (cartStockProductRepository.findById(cartStockProductsId).isPresent()) {
            if (stockRepository.findById(stockId).isPresent()) {
                Stock stock = new Stock(stockRepository.findById(stockId).get().get_id(),
                        stockRepository.findById(stockId).get().getStock(),
                        stockRepository.findById(stockId).get().getCode(),
                        stockRepository.findById(stockId).get().getDate(),
                                stockRepository.findById(stockId).get().getMid(),
                                stockRepository.findById(stockId).get().getSymbol(),
                                stockRepository.findById(stockId).get().getOpen(),
                                stockRepository.findById(stockId).get().getHigh(),
                                stockRepository.findById(stockId).get().getLow(),
                                stockRepository.findById(stockId).get().getPrice(),
                                stockRepository.findById(stockId).get().getVolume(),
                        stockRepository.findById(stockId).get().getLatestTradingDay(),
                stockRepository.findById(stockId).get().getPreviousClose(),
                        stockRepository.findById(stockId).get().getChange());
                  CartStockProduct   cartStockProduct = new CartStockProduct(cartStockProductsId,cartStockRepository.findById(cartStockId).get(),stockRepository.findById(stockId).get(),amount)   ;
                cartStockProductRepository.save(cartStockProduct);
            } else {
                throw new StockNotFoundException();
            }
        } else {
            throw new CartStockProductNotFoundException();
        }
    }

    public void deleteCartStockProduct(final Long cartStockProductId) throws CartStockProductNotFoundException {
        if (cartStockProductRepository.findById(cartStockProductId).isPresent()) {
            CartStockProduct cartStockProduct = cartStockProductRepository.findById(cartStockProductId).get();
            cartStockProductRepository.deleteById(cartStockProduct.getId());
        } else {
            throw new CartStockProductNotFoundException();
        }
    }
}

