package Validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ASUS
 */
public class RegexValid {
    public static boolean isEmpty(String data){
        boolean check = false;
        if (data == null || data.isEmpty()) {
            check = true;
        }
        return check;
    }

    public static boolean checkMaxLenght(String data, int valueMax){
        boolean check = false;
        if (data != null) {
            if (data.length() <= valueMax) {
                check = true;
            }
        }else{
            check = true;
        }
        return check;
    }
    public static boolean checkMinLenght(String data, int valueMin){
        boolean check = false;
        if (data != null) {
            if (data.length() >= valueMin) {
                check = true;
            }
        }else{
            check = true;
        }
        return check;
    }
    
    public static boolean isMail(String email){
        boolean check = false;
        
        if (email != null) {
            String emailCheck = "^[a-zA-Z][a-zA-Z0-9_\\.]{2,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$";
            Pattern p = Pattern.compile(emailCheck);
            Matcher m = p.matcher(email);
            check = m.matches();
        }
        return check;
    }
    
    public static boolean isPhoneNumber(String phone) {
        boolean check = false;

        if (phone != null) {
            String phoneCheck = "^[0][1-9][0-9]{8}$";
            Pattern p = Pattern.compile(phoneCheck);
            Matcher m = p.matcher(phone);
            check = m.matches();
        }

        return check;
    }
    
    public static Date convertStringToDate(String dateConvert, String format) {
        SimpleDateFormat fm = new SimpleDateFormat(format);
        Date date = new Date();

        try {
            date = fm.parse(dateConvert);
        } catch (ParseException ex) {
            System.out.println("common.Common.convertStringToDate()");
            ex.getMessage();
        }

        return date;
    }
    
    /**
     * Kiểm tra đuôi mở rộng của file có nằm trong danh sách cho phép hay không
     *
     * @param extension
     * @return
     */
    public static Boolean isValidImage(String extension) {
        String[] valid = {"jpg", "jpeg", "png"};
        
        return Arrays.asList(valid).contains(extension);
    }
    
    /**
     * Sinh ra 1 chuỗi sử dụng cho việc đặt tên file ảnh hoặc các trường String
     * mà có giá trị unique trong hệ thống Cách làm: nối tên của bảng cần đặt
     * tên với ngày giờ hiện tại kiểu long
     *
     * @param tableName
     * @return
     */
    public static String generateNameTypeB(String tableName) {
        long millis = System.currentTimeMillis();
        return tableName + String.valueOf(millis);
    }
    
    // Chuyển đổi dữ liệu từ giá trị String sang kiểu Int
    public static int convertStringToInt(String data, int defaultValue) {
        try {
            defaultValue = Integer.parseInt(data);
            return defaultValue;
        } catch (NumberFormatException ex) {
            System.out.println("common.Common.convertStringToInt()");
            ex.getMessage();
            return defaultValue;
        }
    }
    
    public static String generateRandomString() 
    { 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz"; 
        StringBuilder sb = new StringBuilder(20); 
  
        for (int i = 0; i < 20; i++) { 
            int index = (int)(AlphaNumericString.length() * Math.random()); 
            sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    }
}
