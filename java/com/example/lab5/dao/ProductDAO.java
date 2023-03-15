package com.example.lab5.dao;

import com.example.lab5.connection.DBConnection;
import com.example.lab5.model.Product;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static Connection conn = DBConnection.getConnection();

    public int addProduct(Product item)
    {
        try {
            PreparedStatement pr = conn.prepareStatement("insert into product(nameproduct,priceproduct) values (?,?)");
            pr.setNString(1,item.getName());
            pr.setNString(2,item.getPrice());
            return pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Product> getAllProduct()
    {
        DecimalFormat twoPlaces = new DecimalFormat("");
        ArrayList<Product> productList = new ArrayList<>();
        try {
            PreparedStatement pr = conn.prepareStatement("select * from product");
            ResultSet resultSet = pr.executeQuery();
            while(resultSet.next())
            {
                Product item = new Product();
                item.setId(resultSet.getInt("idproduct"));
                item.setName(resultSet.getNString("nameproduct"));
                item.setPrice(twoPlaces.format(Double.parseDouble(resultSet.getNString("priceproduct"))));
                item.setDateCreate(resultSet.getTimestamp("datecreate"));
                productList.add(item);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public boolean deleteProduct(int id)
    {
        String sql = "delete from product where idproduct = "+id;
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();
            return true;
        } catch (SQLException throwables)
        {
        }
        return false;
    }
    public Product readProduct(int id)
    {
        Product product = new Product();
        String sql = "select * from product where idproduct="+id;
        try{
            PreparedStatement ps=conn.prepareStatement(sql);
            ResultSet resultSet=ps.executeQuery();
            if(resultSet.next())
            {
                product.setPrice(resultSet.getNString("priceproduct"));
                product.setName(resultSet.getNString("nameproduct"));
                int idp = resultSet.getInt("idproduct");
                product.setId(idp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }
    public boolean update(Product item)
    {
        String sql = "update product set nameproduct = ?, priceproduct = ? where idproduct = ?";
        PreparedStatement statement = null;
        ResultSet resultSet= null;
        try{

            conn.setAutoCommit(false);
            statement=conn.prepareStatement(sql);

            statement.setNString(1,item.getName());
            statement.setNString(2,item.getPrice());
            statement.setInt(3,item.getId());

            int row = statement.executeUpdate();
            conn.commit();
            if(row == 1)
            {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public String url(String input)
    {
        try {
            return URLEncoder.encode(input, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException(e);
        }
    }

}
