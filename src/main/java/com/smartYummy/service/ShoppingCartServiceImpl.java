package com.smartYummy.service;

import com.smartYummy.model.OrderItem;
import com.smartYummy.model.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by chenglongwei on 5/9/16.
 */
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private HttpSession httpSession;
    private static final String CART_ATTRIBUTE_NAME = "shoppingCart";

    @Override
    public ShoppingCart addItem(OrderItem orderItem) {
        ShoppingCart shoppingCart = getShoppingCartFromSession();
        shoppingCart.addItem(orderItem);
        updateShoppingCartInSession(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart removeItem(OrderItem orderItem) {
        ShoppingCart shoppingCart = getShoppingCartFromSession();
        shoppingCart.removeItem(orderItem);
        updateShoppingCartInSession(shoppingCart);
        return shoppingCart;
    }

    @Override
    public ShoppingCart updateItem(OrderItem orderItem) {
        ShoppingCart shoppingCart = getShoppingCartFromSession();
        shoppingCart.updateItem(orderItem);
        updateShoppingCartInSession(shoppingCart);
        return shoppingCart;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        ShoppingCart shoppingCart = getShoppingCartFromSession();
        return shoppingCart.getOrderItems();
    }

    private ShoppingCart getShoppingCartFromSession() {
        ShoppingCart shoppingCart = (ShoppingCart) httpSession.getAttribute(CART_ATTRIBUTE_NAME);
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            httpSession.setAttribute(CART_ATTRIBUTE_NAME, shoppingCart);

        }
        return shoppingCart;
    }

    private void updateShoppingCartInSession(ShoppingCart shoppingCart) {
        httpSession.setAttribute(CART_ATTRIBUTE_NAME, shoppingCart);
    }

}
