/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team0n3.webspotify.controller;

import com.team0n3.webspotify.enums.AccountType;
import com.team0n3.webspotify.model.Album;
import com.team0n3.webspotify.model.Artist;
import com.team0n3.webspotify.model.Song;
import com.team0n3.webspotify.model.User;
import com.team0n3.webspotify.service.ArtistService;
import com.team0n3.webspotify.service.UserService;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author spike
 */
@Controller
@RequestMapping("/artist")
public class ArtistController {
  
  @Autowired
  private UserService userService;
  
  @Autowired
  private ArtistService artistService;
  
  @RequestMapping(value="/followArtist", method=RequestMethod.POST)
  @ResponseBody
  public void followArtist(@RequestParam int artistId, HttpSession session){
    User currentUser = (User)session.getAttribute("currentUser");
    (currentUser.getFollowedArtists()).add(artistService.getArtist(artistId));
    userService.followArtist(currentUser.getUsername(), artistId);
    artistService.updatePopularity(artistId);
    session.setAttribute("currentUser",currentUser);
    //do we need a followedartists session attr??
  }
  
  @RequestMapping(value="/unfollowArtist", method=RequestMethod.POST)
  @ResponseBody
  public void unfollowArtist(@RequestParam int artistId, HttpSession session){
    boolean found = false;
    User currentUser = (User)session.getAttribute("currentUser");
    Collection<Artist> followedArtists = currentUser.getFollowedArtists();
    for(Artist a:followedArtists){
      if(a.getArtistId() == artistId){
        followedArtists.remove(a);
        found = true;
        break;
      }
    }
    if(found){
      userService.unfollowArtist(currentUser.getUsername(), artistId);
      artistService.updatePopularity(artistId);
      session.setAttribute("currentUser",currentUser);
    }
  }
  
  @RequestMapping(value = "/viewArtist", method = RequestMethod.GET)
  @ResponseBody
  public void viewArtist(@RequestParam int artistID, HttpSession session){
      Artist artist = artistService.getArtist(artistID);
      List<Album> artistAlbums = (List<Album>) artist.getAlbums();
      List<Song> artistSongs = (List<Song>) artist.getSongs();
      session.setAttribute("currentArtist",artist);
      session.setAttribute("artistSongs",artistSongs);
      session.setAttribute("artistAlbums",artistAlbums);
  }
  
  @RequestMapping( value = "/viewAllArtists", method = RequestMethod.GET)
  @ResponseBody
  public void viewAllArtists(HttpSession session){
    List<Artist> allArtists = artistService.listAllArtists();
    session.setAttribute("allArtists",allArtists);
  }
  
    @RequestMapping( value = "/adminAddArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminAddArtist(@RequestParam String artistName, @RequestParam int popularity, @RequestParam String imagePath, HttpSession session)
  {
    User user = (User)session.getAttribute("currentUser");
    System.out.println(user.toString());
    if(user.getAccountType() == AccountType.Admin)
    {
        System.out.println(user.toString());
        userService.adminAddArtist( user.getUsername(),  artistName, popularity,  imagePath);
    }
  }
  
  @RequestMapping( value = "/adminRemoveArtist", method = RequestMethod.POST)
  @ResponseBody
  public void adminRemoveArtist(@RequestParam int artistId, HttpSession session){
    List<Artist> allArtists = artistService.listAllArtists();
    boolean found = false;
    for(Artist a : allArtists){
      if(a.getArtistId() == artistId){
        allArtists.remove(a);
        found = true;
        break;
      }
    }
    if(found){
      User currentUser = (User)session.getAttribute("currentUser");
      userService.adminRemoveArtist(currentUser.getUsername(), artistId);
      session.setAttribute("allArtists",allArtists);
    }
  }
}
