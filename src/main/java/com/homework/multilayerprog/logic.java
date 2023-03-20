package com.homework.multilayerprog;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class logic {
    ArrayList<phone> phones = new ArrayList<>();
    datacontroler dt;
    static String strKey = "113";
    List<String> lines;
    logic(String file) throws IOException {
        dt = new datacontroler(file);
        for(int i = 0; i!= dt.GetEndIndex();i++) {
            try {
                phones.add(new phone(dcrypt(dt.GetLine(i))));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public String crypt1(String a){
        if((a=="")||(a.length()<1))return a ;
        String d = "";
        for(int i=0; i!=a.length();i++){
            d+=(char) (((int) a.toCharArray()[i]) ^ 2);
        }
return d;
    }
    public String crypt(String a){
        if((a=="")||(a.length()<1))return a ;
        String d = "";
        for(int i=0; i!=a.length();i++){
            d+=(char) (((int) a.toCharArray()[i])+1);
        }
        return d;
    }
    public String dcrypt(String a){
        if((a=="")||(a.length()<1))return a ;
        String d = "";
        for(int i=0; i!=a.length();i++){
            d+=(char) (((int) a.toCharArray()[i])-1);
        }
        return d;
    }
    public static String encrypt(String strClearText) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.ENCRYPT_MODE, skeyspec);
            byte[] encrypted=cipher.doFinal(strClearText.getBytes());
            strData=new String(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }



    public static String decrypt(String strEncrypted) throws Exception{
        String strData="";

        try {
            SecretKeySpec skeyspec=new SecretKeySpec(strKey.getBytes(),"Blowfish");
            Cipher cipher=Cipher.getInstance("Blowfish");
            cipher.init(Cipher.DECRYPT_MODE, skeyspec);
            byte[] decrypted=cipher.doFinal(strEncrypted.getBytes());
            strData=new String(decrypted);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e);
        }
        return strData;
    }








    public int GetEndIndex(){
        return phones.size();
    }
    public String getName(int n) {
        return (phones.get(n).getName());
    }

    public String getPhoneNumber(int n) {
        return (phones.get(n).getPhoneNumber());
    }

    public String getHomeAddress(int n) {
        return (phones.get(n).getHomeAddress());
    }

    public String getEmail(int n) {
        return (phones.get(n).getEmail());
    }
    public void remove(int n){
        phones.remove(n);
        dt.deleteLine(n);
    }
    public void add(String a,String b,String c,String d){
        phones.add(new phone((a+"|"+b+"|"+c+"|"+d)));
        try {
            dt.addLine(crypt(a+"|"+b+"|"+c+"|"+d));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void save() throws IOException {
        dt.saveToFile();

    }
    public int search(String a){
        for(int i = 0; i!= dt.GetEndIndex();i++){
            if(phones.get(i).getEmail().equals(a))return i;
        if(phones.get(i).getName().equals(a))return i;
        if(phones.get(i).getHomeAddress().equals(a))return i;
        if(phones.get(i).getPhoneNumber().equals(a))return i;
        }
        return -1;
    }
}
