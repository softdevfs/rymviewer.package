package service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.Release;
import model.ReleaseList;

public class ImportService {

	private ReleaseList releases; 

	public void saveAll() {

		List<Release> list = new ArrayList<>();
		
		try {
		    int cont = 0;
		    
		    File file = new File("resources/user_albums");
		    Scanner sc = new Scanner(file);
		    
		    
		    while (sc.hasNextLine()) {
			String line = sc.nextLine();
			if(cont == 0) {
			    cont+=1;
			    continue;
			}
			String bandName = line.split(",\"")[2].replace("\"", "");
			String album = line.split(",\"")[5].replace("\"", "");
			String releaseDateStr = line.split(",\"")[6].replace("\"", "");
			int releaseDate = 0;
			if(!releaseDateStr.equals("")) {
			    releaseDate = Integer.valueOf(releaseDateStr);
			}
			String ratingStr = line.split(",\"")[7].replace("\"", "");
			int rating = Integer.valueOf(ratingStr);
			
			Release r = new Release(bandName, album, releaseDate, rating);
			list.add(r);
			
			cont+=1;
		    }
		    
		    this.releases = new ReleaseList(list);
		
		} catch(Exception ex) {
		    System.out.println(ex.getMessage());
		}
	}

	public List<Release> getAll() {
		return this.releases.getReleases();
	}

}
