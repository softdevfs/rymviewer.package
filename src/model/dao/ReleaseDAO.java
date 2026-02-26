package model.dao;

import model.ReleaseList;
import model.Release;
import jakarta.xml.bind.*;
import java.util.*;
import java.io.File;

public class ReleaseDAO {

	JAXBContext jaxbContext = null;
	ReleaseList releaseList = null;

	public List<Release> getAll(){
		try {
			if(jaxbContext == null) jaxbContext = JAXBContext.newInstance(ReleaseList.class);
			if(releaseList == null) {
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				releaseList = (ReleaseList) jaxbUnmarshaller.unmarshal(new File("./resources/user_albums.xml"));
			}
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return releaseList.getReleases();
	}

	public void saveAll(String fileName, List<Release> releases){
		try {
		    if(!releases.isEmpty()) {
			File destFile = new File("resources/" + fileName + ".xml");
			if(jaxbContext == null) jaxbContext = JAXBContext.newInstance(ReleaseList.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(new ReleaseList(releases), destFile);
		    }
		} catch(Exception ex) {
		    System.out.println(ex.getMessage());
		}
	}

}
