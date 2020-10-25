/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package project2;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class BookReader {
    
    public static void main(String[] args) {
        System.out.println(new File(".").getAbsoluteFile());
        AVLTree avl = new AVLTree(); 
        ArrayList<Book> array = new ArrayList();
        File file = new File("C:\\Users\\chhua\\Documents\\NetBeansProjects\\Project2\\src\\project2\\books.txt");
        try {
       
        

            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                String isbn = scanner.next();
                String title = scanner.next();
                String author = scanner.next();
                avl.root = avl.insert(avl.root,isbn,new Book(isbn,title,author));
                
                
            }
        }catch(FileNotFoundException e){
            System.out.println("IO error");
            e.printStackTrace();
        }
        
        avl.preOrder(avl.root);
    }

}

/**
 *
 * @author chhua
 */

