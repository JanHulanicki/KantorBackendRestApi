package com.app.kantor.service;

import com.app.kantor.domain.cart.*;
import com.app.kantor.domain.user.User;
import com.app.kantor.exception.UserNotFoundException;
import com.app.kantor.mapper.CartCryptoMapper;
import com.app.kantor.mapper.CartNbpMapper;
import com.app.kantor.mapper.CartStockMapper;
import com.app.kantor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartCryptoService cartCryptoService;
    @Autowired
    CartCryptoMapper cartCryptoMapper;
    @Autowired
    CartStockService cartStockService;
    @Autowired
    CartStockMapper cartStockMapper;
    @Autowired
    CartNbpService cartNbpService;
    @Autowired
    CartNbpMapper cartNbpMapper;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(final long id) {
        return userRepository.findById(id);
    }

    public void createUser(final User user) throws UserNotFoundException {
        //   User user = new User();
        CartCrypto cartCrypto = new CartCrypto();
        CartStock cartStock = new CartStock();
        CartNbp cartNbp = new CartNbp();

        CartCryptoDto cartCryptoDto = new CartCryptoDto();
        CartNbpDto cartNbpDto = new CartNbpDto();
        CartStockDto cartStockDto = new CartStockDto();
        //user = userService.createUser(userMapper.mapToUser(userDto));
        userRepository.save(user);
        cartCryptoDto.setUserId(userRepository.getOne(user.getId()).getId());
        cartCryptoDto.setCreated(LocalDateTime.now().toString());
        cartNbpDto.setUserId(userRepository.getOne(user.getId()).getId());
        cartNbpDto.setCreated(LocalDateTime.now().toString());
        cartStockDto.setUserId(userRepository.getOne(user.getId()).getId());
        cartStockDto.setCreated(LocalDateTime.now().toString());

        cartCrypto = cartCryptoService.createCartCrypto(cartCryptoMapper.mapToCartCrypto(cartCryptoDto));
        cartStock = cartStockService.createCartStock(cartStockMapper.mapToCartStock(cartStockDto));
        cartNbp = cartNbpService.createCartNbp(cartNbpMapper.mapToCartNbp(cartNbpDto));
        user.setCartCrypto(cartCrypto);
        user.setCartStock(cartStock);
        user.setCartNbp(cartNbp);
        userRepository.save(user);

        // return userRepository.save(user);
    }

    public User blockUser(final long id) {
        userRepository.getOne(id).setIsBlocked(true);
        return userRepository.save(userRepository.getOne(id));
    }

    public void saveUser(final User user) {
        userRepository.save(user);
    }
}
