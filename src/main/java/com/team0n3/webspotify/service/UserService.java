
package com.team0n3.webspotify.service;

import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Playlist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import java.util.List;

public interface UserService {
  
  public User login(String username, String password);
  public String signup(String username, String password, String email, boolean isArtist);
  public List<Playlist> getCreatedPlaylists(String username);
  public List<User> listAllUsers();
  public User getUser(String username);
  public List<User> search(String keyword);
  public User followPlaylist(String userId, int playlistId);
  public User unfollowPlaylist(String userId, int playlistId);
  public User followArtist(String userId, int artistId);
  public User unfollowArtist(String userId, int artistId);
  public User followSong(String userId, int songId);
  public User unfollowSong(String userId, int songId);
  public User followAlbum(String userId, int albumId);
  public User unfollowAlbum(String userId, int albumId);
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
  public void adminApproveFreeUser(String username,String approve);
  public void adminApproveArtistUser(String username,String approve);
  public void adminRemoveUser(String admin,String removeUser);
  public void changeProfilePic(String username, String path);
  public void adminAddSong(String title );
  public void adminAddAlbum(String albumName, int popularity, String imagePath );
  public void adminEditArtistBio( int artistId);
  public void adminApproveFreeUser(String approve);
  public void adminApproveArtistUser(String approve); 
  public void adminDeleteUser(User u);
  public void adminDeleteArtist(Artist a);
  public void adminDeleteAlbum(Album a);
  public void adminDeleteSong(Song s);
  public void adminDeletePlaylist(Playlist p);
  public void adminSendRoyaltyChecks(String artistId);
  
}
