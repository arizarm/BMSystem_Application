package com.example.aprilwang.bookshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.aprilwang.bookshop.Member.host;

/**
 * Created by April Wang on 21/12/2017.
 */

public class Book extends HashMap<String,String>{
    final static String baseURL="http://172.17.254.6/WCFHosting/Service.svc";
    final static String imageURL="http://172.17.254.6/WCFHosting/Service.svc/Image";

    public Book(String id,String title,String auth,String isbn,String price,String stock){
        put("BookID",id);
        put("Title",title);
        put("Author",auth);
        put("ISBN",isbn);
        put("Price",price);
        put("Stock",stock);
    }

    public Book(){

    }
    public Book(String id, String title, String price){
        put("BookID",id);
        put("Title",title);
        put("Price",price);
    }

    public static List<Book> list() {
        List<Book> list = new ArrayList<Book>();
        JSONArray a = JSONParser.getJSONArrayFromUrl(baseURL + "/Book");
        try {
            for (int i =0; i<a.length(); i++) {
                JSONObject b = a.getJSONObject(i);
                list.add(new Book(b.getString("Id"), b.getString("Title"),b.getString("Price")));
            }
        } catch (Exception e) {
            Log.e("Book.list()", "JSONArray error");
        }
        return(list);
    }


    public static Book getBook(String id) {
        JSONObject b = JSONParser.getJSONFromUrl(baseURL + "/Book/" + id);
        try {
            return new Book(b.getString("Id"),b.getString ("Title"),b.getString ("Author"),b.getString("Isbn"),b.getString ("Price"),b.getString ("Stock"));
        } catch (Exception e) {
            Log.e("Book.getBook()", "JSONArray error");
        }
        return(null);
    }

    public static void updateCustomer(Book cus) {
        JSONObject jcustomer = new JSONObject();
        try {
            jcustomer.put("Id", cus.get("BookID"));
            jcustomer.put("Title", cus.get("Title"));
            jcustomer.put("Author", cus.get("Author"));
            jcustomer.put("Isbn", cus.get("ISBN"));
            jcustomer.put("Price",new BigDecimal(cus.get("Price")));
            jcustomer.put("Stock",Integer.parseInt(cus.get("Stock")));


        } catch (Exception e) {
        }
        String result = JSONParser.postStream(baseURL+"/Update", jcustomer.toString());
    }

    public static void createBook(Book b) {
        JSONObject jbook = new JSONObject();
        try {
            jbook.put("Title", b.get("Title"));
            jbook.put("Author", b.get("Author"));
            jbook.put("Isbn", b.get("ISBN"));
            jbook.put("Price",new BigDecimal(b.get("Price")));
            jbook.put("Stock",Integer.parseInt(b.get("Stock")));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"/New", jbook.toString());
    }

    public static Bitmap getPhoto(String isbn) {
        try {
            URL url = new URL(String.format("%s/%s.jpg",imageURL, isbn));
            URLConnection conn = url.openConnection();
            InputStream ins = conn.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(ins);
            ins.close();
            return bitmap;
        } catch (Exception e) {
            Log.e("Employee.getPhoto()", "Bitmap error");
        }
        return(null);
    }
}
