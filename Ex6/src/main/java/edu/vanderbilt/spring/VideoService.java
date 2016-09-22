package edu.vanderbilt.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoService {

	private VideoRepository videos_;
	
	@Autowired
	public VideoService(VideoRepository repo) {
		videos_ = repo;
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.GET)
	public Video getVideo(@PathVariable("id") Long videoId){
		System.out.println("id:"+videoId);
		return videos_.findOne(videoId);
	}
	
	@RequestMapping(value="/test/foo", method=RequestMethod.GET)
	public String myTest(){
		return "Test";
	}
	
	@RequestMapping(value="/video", method=RequestMethod.GET)
	public Iterable<Video> getVideos() {
		return videos_.findAll();
	}
	
	@RequestMapping(value="/video", method=RequestMethod.POST)
	public Iterable<Video> postVideo(@RequestBody String name, long size, String genre, String url) {
		Video video = new Video();
		video.setName(name);
		video.setSize(size); 
		video.setGenre(genre);
		video.setUrl(url);
		videos_.save(video);
		System.out.println(video.getId().toString() + ",\n" + video.getName() + ",\n" + video.getSize()
										+ ",\n" + video.getGenre() + ",\n" + video.getUrl());
		return videos_.findAll();
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.POST)
	public Iterable<Video> updateVideo(@PathVariable("id") Long videoId, @RequestBody String name, long size, String genre, String url) {
		Video video = videos_.findOne(videoId);
		video.setName(name);
		video.setSize(size); 
		video.setGenre(genre);
		video.setUrl(url);
		videos_.save(video);
//		System.out.println(video.getId().toString() + ",\n" + video.getName() + ",\n" + video.getSize()
//		+ ",\n" + video.getGenre() + ",\n" + video.getUrl());
		return videos_.findAll();
	}
	
	@RequestMapping(value="/video/{id}", method=RequestMethod.DELETE)
	public Iterable<Video> deleteVideo(@PathVariable("id") Long videoId){
		videos_.delete(videoId);
		return videos_.findAll();
	}
}
