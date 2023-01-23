/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.music;
 /**
* <h1>Id and Passwords</h1>
* This program stores the hashmap and uses the static keyword and setters and getters to give to classes. 
* <p>
* @author  Austin Liu
* @version 19
* @since   2023-01-23
  **/
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JTable;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class IDandPasswords {
  private static JFrame Frame;
  public static JTable Table;
  public static HashMap<String, String> logininfo = (HashMap<String, String>) HashMapFromTextFile();
  final static String FilePath = "Storage.txt";
  
  IDandPasswords() {
	  
  }

  public static HashMap getLoginInfo() {
    return logininfo;
    /**
     * @param Nothing
     * @return logininfo
     *
     **/
  }

  public void settest(String key, String value) {
    logininfo.put(key, value);
    /** putting an element in
    * @param key
    * @param value
    * @return Nothing
    **/
  }

  public void delete(String key) {
    logininfo.remove(key);
    /** removing the row from the hashmap
    * @param key
    * @return Nothing
    **/
  }

  public void register(String User, String Password) {
    logininfo.put(User, Password);
    /**
     * registering
     * @param User
     * @param Password
     * @return Nothing
    **/
  }

  public static int getlength() {
    return logininfo.size();
    /** 
     * @param Nothing
     * @return int
    **/
  }

  //adjusting the frame to the optimized settings
public static boolean checkkey(String key){
     String value = logininfo.get(key);
    if (value != null) {
      return true;
    } else {
      return false;
  }  
    }
  public void Store() {
      /**
     * Storing
     * @param none
     * @return Nothing
    **/
	  File file = new File(FilePath);
	  
      BufferedWriter bf = null;

      try {

          // create new BufferedWriter for the output file
          bf = new BufferedWriter(new FileWriter(file));

          // iterate map entries
          for (Entry<String, String> entry :
               logininfo.entrySet()) {

              // put key and value separated by a colon
              bf.write(entry.getKey() + ":"
                       + entry.getValue());

              // new line
              bf.newLine();
          }

          bf.flush();
      }
      catch (IOException e) {
          e.printStackTrace();
      }
      finally {

          try {

              // always close the writer
              bf.close();
          }
          catch (Exception e) {
          }
      }
  }
  public static Map<String, String> Get()
  {
      //method that gets from a file

      BufferedReader br = null;

      try {

          // create file object
          File file = new File(FilePath);

          // create BufferedReader object from the File
          br = new BufferedReader(new FileReader(file));

          String line = null;

          // read file line by line
          while ((line = br.readLine()) != null) {

              // split the line by :
              String[] parts = line.split(":");

              // first part is name, second is number
              String name = parts[0].trim();
              String number = parts[1].trim();

              // put name, number in HashMap if they are
              // not empty
              if (!name.equals("") && !number.equals(""))
                  logininfo.put(name, number);
          }
      }
      catch (Exception e) {
          e.printStackTrace();
      }
      finally {

          // Always close the BufferedReader
          if (br != null) {
              try {
                  br.close();
              }
              catch (Exception e) {
              };
          }
      }

      return logininfo;
  }

public static String get(String userID) {
	// TODO Auto-generated method stub
	return logininfo.get(userID);
}

public static void remove(String wd) {
    // create file object
	File file = new File(FilePath);
    boolean done = false;
    String word = wd.toLowerCase();
    String[] line = word.split("[,\\s]+");
    for (int j = 0; j < line.length; j++) {
        // remove and read file line by line
        logininfo.remove(line[j]);
    }
    try {
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
         // create BufferedReader object from the File
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(logininfo.toString());
        bw.close();
        done = true;
    } catch (Exception e) {
        e.printStackTrace();
    }
    if (done == true) {
        System.out.println("Success");
    }
}
public static Map<String, String> HashMapFromTextFile()
{

    Map<String, String> map
        = new HashMap<String, String>();
    BufferedReader br = null;

    try {

        // create file object
        File file = new File(FilePath);

        // create BufferedReader object from the File
        br = new BufferedReader(new FileReader(file));

        String line = null;

        // read file line by line
        while ((line = br.readLine()) != null) {

            // split the line by :
            String[] parts = line.split(":");

            // first part is name, second is number
            String name = parts[0].trim();
            String number = parts[1].trim();

            // put name, password in HashMap if they are
            // not empty
            if (!name.equals("") && !number.equals(""))
                map.put(name, number);
        }
    }
    catch (Exception e) {
      System.err.println(e);
    }
    finally {

        // Always close the BufferedReader
        if (br != null) {
            try {
                br.close();
            }
            catch (Exception e) {
                System.err.println(e);
            }
        }
    }

    return map;
}
public String getter(String key){
    return logininfo.get(key);
    //gets the second value of the hashmap
}
}

  

