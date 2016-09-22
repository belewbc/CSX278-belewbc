package edu.vanderbilt.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExerciseApplicationTests {

	@Autowired
	VideoService videoService;
	
	@Test
	public void contextLoads() {	
	}
	
	@Test
	public void videoServiceLoaded() {
		assertNotNull(videoService);
	}
	
	@Test
	public void postVideoReturnsAListOfVideos() {
		Iterable<Video> videos = videoService.postVideo("test", 1, "comedy", "testurl");
		assertNotNull(videos);
		assertTrue(videos.iterator().hasNext());
	}
	
	@Test
	public void postVideoAddsMetadata() {
		Iterable<Video> videos = videoService.postVideo("test", 1, "comedy", "testurl");
		assertNotNull(videos);
		Video myVid = videos.iterator().next();
		assertTrue(myVid.getName() == "test");
		assertTrue(myVid.getSize() == 1);
		assertTrue(myVid.getGenre() == "comedy");
		assertTrue(myVid.getUrl() == "testurl");
	}
	
	@Test
	public void getVideosReturnsAListOfVideos() {
		videoService.postVideo("test", 1, "comedy", "testurl");
		assertNotNull(videoService.getVideos());
		assertTrue(videoService.getVideos().iterator().hasNext());
	}
	
	@Test
	public void updateVideoChangesMetadata() {
		Iterable<Video> videos = videoService.postVideo("test", 1, "comedy", "testurl");
		assertNotNull(videos);
		Video myVid = videos.iterator().next();
		Long id = myVid.getId();
		videoService.updateVideo(id, "newTest", 2, "drama", "newurl");
		Video curVid = videoService.getVideo(id);
		System.out.println("\n" + curVid.getName() + "\n");
		assertTrue(curVid.getName() == "newTest");
		assertTrue(curVid.getSize() == 2);
		assertTrue(curVid.getGenre() == "drama");
		assertTrue(curVid.getUrl() == "newurl");
	}
	
	@Test
	public void deleteVideoRemovesVideo() {
		Iterable<Video> videos = videoService.postVideo("test", 1, "comedy", "testurl");
		assertTrue(videos.iterator().hasNext());
		Long id = videos.iterator().next().getId();
		videoService.deleteVideo(videos.iterator().next().getId());
		assertNull(videoService.getVideo(id));
	}

}
