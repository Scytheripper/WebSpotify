
package com.team0n3.webspotify.service.implementation;

import com.team0n3.webspotify.dao.ArtistDAO;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.ArtistService;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service("artistService")
@Transactional(readOnly = true)
public class ArtistServiceHibernateImpl implements ArtistService{
    
    @Autowired
    private ArtistDAO artistDao;
    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    public Artist getArtist(int artistId) {
        Artist artist = artistDao.getArtist(artistId);
        if(artist == null)
            return null;
        return artist;
    }
    
    @Transactional(readOnly = false)
    @Override
    public void addNewArtist(String artistName) {
        Artist artist = new Artist(artistName);
        artistDao.addArtist(artist);
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Artist> listAllArtists()
    {
        List<Artist> listArtists = artistDao.listArtists();
        return listArtists;
    }
    
    @Transactional(readOnly = true)
    @Override
    public List<Artist> search(String keyword)
    {
      List<Artist> listArtists = artistDao.search(keyword);
      return listArtists;
    }
    
  @Override
  @Transactional(readOnly = false)
  public void updatePopularity(int artistId){
    Artist artist = artistDao.getArtist(artistId);
    Collection<User> followers = artist.getFollowers();
    artist.setPopularity(followers.size());
    artistDao.updateArtist(artist);
  }
  
  @Transactional(readOnly = false)
  @Override
  public void calcTotalRoyalties(int artistId){
      
    Artist artist = artistDao.getArtist(artistId);
    Collection<Song> songs = artist.getSongs();
    int totalRoyalty = 0;
    int totalPlays = 0;
    for(Song s : songs){
      totalPlays = s.getTotalPlays();
      if(totalPlays > 0)
        totalRoyalty = totalRoyalty + (s.getRoyaltyPerPlay())*totalPlays;
    }
      artist.setTotalRoyalties(totalRoyalty);
      artistDao.updateArtist(artist);
  }
  
  @Transactional(readOnly = true)
  @Override
  public List<Song> getSongsWithPlays(int artistId){
    Artist artist = artistDao.getArtist(artistId);
    Collection<Song> songs = artist.getSongs();
    List<Song> songsWithPlays = new ArrayList();
    int totalPlays = 0;
    for(Song s : songs){
      totalPlays = s.getTotalPlays();
        if(totalPlays > 0)
          songsWithPlays.add(s);
    }
    return songsWithPlays;
  }
}
