/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.dao;
import java.util.List;
import com.team0n3.webspotify.model.Artist;

public interface ArtistDAO {
    public void addArtist(Artist artist);
    
    public Artist getArtist(int id);
    
    public List<Artist> listArtists();
     
    public void updateArtist(Artist artist);
     
    public void deleteArtist(Artist artist);
}