
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.User;
import java.util.List;

public interface UserService {
  
  public User login(String username, String password);
  
  public String signup(String username, String password, String email);
  
  public List<Playlist> getCreatedPlaylists(String username);
  
  public void followPlaylist(String userId, int playlistId);
  
  public void unfollowPlaylist(String userId, int playlistId);
  
  public List<User> listAllUsers();

  public List<User> search(String keyword);
  public void followArtist(String userId, int artistId);
  public void unfollowArtist(String userId, int artistId);
  public void followSong(String userId, int songId);
  public void unfollowSong(String userId, int songId);
  public void followAlbum(String userId, int albumId);
  public void unfollowAlbum(String userId, int albumId);
  public void adminAddArtist(String username, String artistName,int popularity, String imagePath);
  public void adminRemoveArtist(String username, int artistId);
  public void adminAddPlaylist( String username, String playlistName,String imagePath, String description);
  public void adminRemovePlaylist(String username, int playlistId);
  public void adminAddSong( String username, String title );
  public void adminRemoveSong(String username, int songId);
   public void adminEditSong(String username, int songId);
  public void adminAddAlbum( String username, String albumName, int popularity, String imagePath );
  public void adminRemoveAlbum(String username, int albumId);
  public void adminEditArtistBio(String username, int artistId);
  public void artistCheckSongMetrics(String username, int artistId);
  public void artistCheckRoyalties(String username, int artistId);
}
