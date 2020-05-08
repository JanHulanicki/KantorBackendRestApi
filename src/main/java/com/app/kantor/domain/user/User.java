package com.app.kantor.domain.user;

import com.app.kantor.domain.cart.CartCrypto;
import com.app.kantor.domain.cart.CartNbp;
import com.app.kantor.domain.cart.CartStock;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@PersistenceContext
@Entity(name = "USERS")
public class User {
    @JoinColumn(name = "CART_NBP_ID", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    public CartNbp cartNbp;
    @JoinColumn(name = "CART_CRYPTO_ID", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    public CartCrypto cartCrypto;
    @JoinColumn(name = "CART_STOCK_ID", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    public CartStock cartStock;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private long id;
    @Column(name = "NICK")
    private String nick;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "E_MAIL")
    private String emailAddress;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SURNAME")
    private String surname;
    @Column(name = "STATUS")
    private Boolean isBlocked;
    @Column(name = "UUID_KEY")
    private String uuidKey;
    @Column(name = "EXPIRED_DATE")
    private LocalDateTime expiredDate;


    public User(String nick, String password, String emailAddress, String name, String surname, Boolean isBlocked, String uuidKey, LocalDateTime expiredDate) {

        this.nick = nick;
        this.password = password;
        this.emailAddress = emailAddress;
        this.name = name;
        this.surname = surname;
        this.isBlocked = isBlocked;
        this.uuidKey = uuidKey;
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", isBlocked=" + isBlocked +
                ", uuidKey='" + uuidKey + '\'' +
                ", expiredDate=" + expiredDate +
                ", cartNbp=" + cartNbp +
                ", cartCrypto=" + cartCrypto +
                ", cartStock=" + cartStock +
                '}';
    }
}