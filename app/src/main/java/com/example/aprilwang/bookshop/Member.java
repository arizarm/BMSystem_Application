package com.example.aprilwang.bookshop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by April Wang on 21/12/2017.
 */

public class Member extends java.util.HashMap<String, String> {
    final static String host = "http://172.17.254.6/WCFHosting/MemberService.svc/";

    public Member(String name, String email, String phone, String pass) {
        put("MemberName", name);
        put("Email", email);
        put("PhoneNo", phone);
        put("Password", pass);
    }

    public Member(){}

    public static List<String> listMember() {
        List<String> list = new ArrayList<String>();
        try {
            JSONArray a = JSONParser.getJSONArrayFromUrl(host+"Members");
            for (int i=0; i<a.length(); i++) {
                String c = a.getString(i);
                list.add(c);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public static Member getMember(String name) {
        Member m = null;
        try {
            JSONObject c = JSONParser.getJSONFromUrl(host+"member/"+name);
            m = new Member(c.getString("MemberName"),
                    c.getString("Email"),
                    c.getString("PhoneNo"),
                    c.getString("Password"));
        } catch (Exception e) {
        }
        return m;
    }

    public static void updateMember(Member m) {
        JSONObject jmember = new JSONObject();
        try {
            jmember.put("MemberName", m.get("MemberName"));
            jmember.put("Email", m.get("Email"));
            jmember.put("PhoneNo", m.get("PhoneNo"));
            jmember.put("Password", (m.get("Password")));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"Update", jmember.toString());
    }

    public static void createMember(Member m) {
        JSONObject jmember = new JSONObject();
        try {
            jmember.put("MemberName", m.get("MemberName"));
            jmember.put("Email", m.get("Email"));
            jmember.put("PhoneNo", m.get("PhoneNo"));
            jmember.put("Password", (m.get("Password")));
        } catch (Exception e) {
        }
        String result = JSONParser.postStream(host+"AddMember", jmember.toString());
    }
}
