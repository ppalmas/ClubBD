/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Managers;


import Database.Genre;
import java.util.List;

/**
 *
 * @author Paola
 */
public interface GenreManager {
    public void insert(String nom);
    public List<Genre> findGenre();
     
    
}
