package org.example.exceptions;

public class FileNotFound extends Exception{
    public FileNotFound(String fileName){
        System.out.printf ("Файл %s не найден \n", fileName);
    }
}
