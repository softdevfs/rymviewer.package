package gestor;

import java.util.*;
import view.Menu;
import model.Release;
import model.DataOp;
import model.dao.ReleaseDAO;
import service.ImportService;

/**
 *
 * @author sergio
 */
public class MainApp {
    
    public static void main(String[] args) {
        
	DataOp model = new DataOp();
	ReleaseDAO dao = new ReleaseDAO();
	ImportService service = new ImportService();
        Menu menu = new Menu();
        menu.printMessage("Do you want to load data? [y/N]");
        Scanner scan = new Scanner(System.in);
        String c = scan.nextLine();
        if(c.equals("y") || c.equals("Y")) {
		service.saveAll();
		dao.saveAll("user_albums", service.getAll());
	}
        else menu.printMessage("Data not loaded");
        menu.executeMenu();
        String data = menu.getData();
        String selected = menu.getSelectedOption();
        List<Release> list = dao.getAll(); 
	StringBuilder sb = new StringBuilder();
	try {
		switch(selected) {
		case "1":
		    for(Release r: model.searchByBandName(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "2":
		    for(Release r: model.searchByYear(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "3":
		    for(Release r: model.searchByRating(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "4":
		    for(Release r: model.searchByYearNRating(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "5":
		    int rating = model.getAverageRatingByYear(data, list);
		    if(rating > 0) sb.append( "Average rating of " + data + ": " + rating + "\n");
		    else sb.append("null");
		    break;
		case "6":
		    for(Release r: model.searchBetweenTwoRatings(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "7":
		    for(Release r: model.searchBetweenTwoRatingsByYear(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "8":
		    System.exit(0);
		    break;
		}
	} catch(Exception ex){
		System.out.println(ex.getMessage());
	}
	menu.printMessage(sb.toString());
        menu.printMessage("Do you want to save the results in a XML document? [y/N]");
        c = scan.nextLine();
        if(c.equals("y") || c.equals("Y")) {
            menu.printMessage("Introduce the filename (without format)");
            String fileName = scan.nextLine();
            dao.saveAll(fileName, model.getFiltered()); 
	    menu.printMessage("Operation completed.");
           
        }
    }

    public StringBuilder prepareList(String selected, DataOp model, String data, List<Release> list) {

	StringBuilder sb = new StringBuilder();

	try {

		switch(selected) {
		case "1":
		    for(Release r: model.searchByBandName(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "2":
		    for(Release r: model.searchByYear(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "3":
		    for(Release r: model.searchByRating(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "4":
		    for(Release r: model.searchByYearNRating(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "5":
		    int rating = model.getAverageRatingByYear(data, list);
		    if(rating > 0) sb.append( "Average rating of " + data + ": " + rating + "\n");
		    else sb.append("null");
		    break;
		case "6":
		    for(Release r: model.searchBetweenTwoRatings(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "7":
		    for(Release r: model.searchBetweenTwoRatingsByYear(data, list))
		    {
			sb.append(r.toString() + "\n");
		    }
		    break;
		case "8":
		    System.exit(0);
		    break;
		}

	} catch(Exception ex){
		System.out.println(ex.getMessage());
	}
		return sb;

    }
    
}
