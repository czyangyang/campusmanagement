package com.czxx.campusmanagement.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CzxxHelper {
	public CzxxHelper()
	{
		
	}
	public static String DateToString(Date date)
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
	}
	
	public static Date StringToDate(String dataStr) throws ParseException
	{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dataStr);
	}
	
	public static String readToString(String fileName) {  
        String encoding = "UTF-8";  
        File file = new File(fileName);  
        Long filelength = file.length();  
        byte[] filecontent = new byte[filelength.intValue()];  
        try {  
            FileInputStream in = new FileInputStream(file);  
            in.read(filecontent);  
            in.close();  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        try {  
            return new String(filecontent, encoding);  
        } catch (UnsupportedEncodingException e) {  
            System.err.println("The OS does not support " + encoding);  
            e.printStackTrace();  
            return null;  
        }  
    }
	
	public static void writeToFile(String fileName, String content) throws IOException
	{
		String charSet="UTF-8";
		//写字符转换成字节流
		FileOutputStream fileWriter=new FileOutputStream(fileName);
		OutputStreamWriter writer=new OutputStreamWriter(fileWriter, charSet);
		try {
			writer.write(content);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			writer.close();
		}
		
	}
}
