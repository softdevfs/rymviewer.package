package model.dao;

import model.Release;
import java.util.*;

public interface ReleaseInterfaceDAO {

	List<Release> getAll();
	void saveAll(String filename, List<Release> releases);

}
