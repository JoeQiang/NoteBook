package com.notebook.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileIO { 
   
    public static List<String> readTxtFile(String filePath){ 
    	List<String> strs=new ArrayList<String>();
        try { 
                String encoding="GBK"; 
                File file=new File(filePath); 
                if(file.isFile() && file.exists()){ //判断文件是否存在 
                    InputStreamReader read = new InputStreamReader( 
                    new FileInputStream(file),encoding);//考虑到编码格式 
                    BufferedReader bufferedReader = new BufferedReader(read); 
                    String lineTxt = null; 
                    while((lineTxt = bufferedReader.readLine()) != null){ 
                        strs.add(lineTxt);
                    } 
                    read.close(); 
                    return strs;
        }else{ 
            System.out.println("找不到指定的文件"); 
            return null;
        } 
        } catch (Exception e) { 
            System.out.println("读取文件内容出错"); 
            e.printStackTrace(); 
            return null;
        } 
      
    } 
      
    public static void save(String path,String content) throws IOException{   
    	if(content!=null&&!(content.equals(""))){}
    	FileOutputStream fos = new FileOutputStream(path,true);//true表示在文件末尾追加   
    	fos.write(("\r\n"+content).getBytes());  
    	fos.close();//流要及时关闭   
    }  
    
    public static void save_2(String path,String content) throws IOException{   
    	FileOutputStream fos = new FileOutputStream(path,false);  
    	fos.write(("\r\n"+content).getBytes());  
    	fos.close();//流要及时关闭   
    }  
    
    
    
    public static void main(String argv[]) throws IOException{ 
        String filePath = "E:\\eclipse\\workspace\\notebook\\src\\data.txt"; 
        save(filePath,"组中值");
        List<String> strs=readTxtFile(filePath); 
        for(String s:strs){
        	System.out.println(s);
        }
    } 
      
      
  
} 

