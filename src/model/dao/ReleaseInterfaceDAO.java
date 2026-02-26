package model.dao;

import model.Release;
import java.util.*;

public interface ReleaseInterfaceDAO {

	List<Release> getAll();
	void saveAll(List<Release> releases);

}
