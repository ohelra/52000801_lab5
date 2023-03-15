package com.example.lab5;

import com.example.lab5.dao.ProductDAO;
import com.example.lab5.dao.UserDAO;
import com.example.lab5.model.Product;
import com.example.lab5.model.User;
import com.mysql.cj.Session;

//import java.text.DecimalFormat;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args)
    {
        ProductDAO productDAO = new ProductDAO();
        Product product = new Product();
        product.setName("Oppo");
        product.setPrice("1200");
//        System.out.println(productDAO.update(product));
//        productDAO.addProduct(product);
//        UserDAO userDAO = new UserDAO();
//        System.out.println(userDAO.readUser("mvmanh@gmail.com").getName());
//        String amount = "20000000";
//        DecimalFormat twoPlaces = new DecimalFormat("");
//        String money = twoPlaces.format(Double.parseDouble(amount));
//        System.out.println(productDAO.getAllProduct());

    }
}
