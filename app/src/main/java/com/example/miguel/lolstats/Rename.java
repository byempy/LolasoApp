package com.example.miguel.lolstats;

import java.io.File;

public class Rename {
    public static void main(String[] args){
        File dir = new File("D:\\Carpeta Personal\\Downloads\\splash");

        for(File f: dir.listFiles()){
            if(!f.getName().contains("0")){
                f.delete();
            }
        }

        for(File f: dir.listFiles()){
            f.renameTo(new File("D:\\Carpeta Personal\\Downloads\\splash\\" + f.getName().toLowerCase().replace("_", "")));
        }
    }
}
