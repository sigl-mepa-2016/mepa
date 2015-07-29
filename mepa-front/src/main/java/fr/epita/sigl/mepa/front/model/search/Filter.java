package fr.epita.sigl.mepa.front.model.search;

import fr.epita.sigl.mepa.core.domain.DataSet;
import org.springframework.ui.ModelMap;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by emeline on 24/07/2015.
 */
public class Filter {
    public static List<String> listFilter = new ArrayList<>();
    public static int nbCartoDataset;
    public static int nbGraphDataset;
    public static int nbDataset;
    public static int size;
    public static HashMap<String, Integer> themeMap;
    public static HashMap<String, Integer> dateMap;
    public static List<DataSet> listDataset = new ArrayList<>();
    protected static final String THEME_FILTER_ATTRIBUTE = "themeFilter";
    protected static final String DATE_FILTER_ATTRIBUTE = "dateFilter";
    protected static final String FILTER_ATTRIBUTE = "filters";

    public static void initFilter(ModelMap modelMap, List<DataSet> dataSets) {
        List<DataSet> allCartoDatasets = new ArrayList<>();
        List<DataSet> allGraphicDatasets = new ArrayList<>();
        getCartoAndGraphicDataset(allCartoDatasets, allGraphicDatasets, dataSets);
        nbCartoDataset = allCartoDatasets.size();
        nbGraphDataset = allGraphicDatasets.size();
        nbDataset = dataSets.size();
        themeMap = new HashMap<>();
        dateMap = new HashMap<>();
        for (DataSet dataSet : dataSets) {
            String theme = dataSet.getTheme();
            if (themeMap.containsKey(theme)) {
                Integer numberOfOccurence = themeMap.get(theme);
                themeMap.replace(theme, numberOfOccurence, numberOfOccurence +1);
            } else {
                themeMap.put(theme, 1);
            }
            Date lastModified = dataSet.getLastModified();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(lastModified);
            if (dateMap.containsKey(date)) {
                Integer numberOfOccurence = dateMap.get(date);
                dateMap.replace(date, numberOfOccurence, numberOfOccurence +1);
            } else {
                dateMap.put(date, 1);
            }
        }
        listDataset = sortByDate(dataSets);
        printAll(modelMap);
    }

    private static void getCartoAndGraphicDataset(List<DataSet> allCartoDatasets, List<DataSet> allGraphicDatasets, List<DataSet> dataSets) {
        for (DataSet dataSet : dataSets) {
            if (null != dataSet.getIsCarto() && dataSet.getIsCarto()){
                allCartoDatasets.add(dataSet);
            }
            if (null != dataSet.getIsGraphic() && dataSet.getIsGraphic()){
                allGraphicDatasets.add(dataSet);
            }
        }
    }

    private static void printAll(ModelMap modelMap) {
        modelMap.addAttribute("resFilterGraph", nbGraphDataset);
        modelMap.addAttribute("resFilterCarto", nbCartoDataset);
        modelMap.addAttribute(THEME_FILTER_ATTRIBUTE, themeMap);
        modelMap.addAttribute(DATE_FILTER_ATTRIBUTE, dateMap);
        modelMap.addAttribute(FILTER_ATTRIBUTE, listFilter);
        modelMap.addAttribute("nbDataset", nbDataset);
    }

    public static List<DataSet> CartoFilter(List<DataSet> dataSets, List<DataSet> allCartoDatasets) {
        for (DataSet dataSet : dataSets) {
            if (dataSet.getIsCarto()){
                allCartoDatasets.add(dataSet);
            }
        }
        return allCartoDatasets;
    }

    public static List<DataSet> GraphicFilter(List<DataSet> dataSets, List<DataSet> allGraphicDatasets) {
        for (DataSet dataSet : dataSets) {
            if (dataSet.getIsGraphic()){
                allGraphicDatasets.add(dataSet);
            }
        }
        return allGraphicDatasets;
    }

    public static List<DataSet> ThemeFilter(List<DataSet> dataSets, List<DataSet> allThemeDatasets, String theme) {
        for (DataSet dataSet : dataSets) {
            if (dataSet.getTheme().equals(theme)){
                allThemeDatasets.add(dataSet);
            }
        }
        return allThemeDatasets;
    }

    public static List<DataSet> DateFilter(List<DataSet> dataSets, List<DataSet> allDateDatasets, String yearChoose) {
        for (DataSet dataSet : dataSets) {
            Date lastModified = dataSet.getLastModified();
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String date = df.format(lastModified);
            if (yearChoose.equals(date)){
                allDateDatasets.add(dataSet);
            }
        }
        return allDateDatasets;
    }


    public static List<DataSet> sortByDate(List<DataSet> dataSets) {
        int longueur = dataSets.size();
        DataSet tampon;
        boolean permut;

        do {
            // hypothèse : le tableau est trié
            permut = false;
            for (int i = 0; i < longueur - 1; i++) {
                DataSet first = dataSets.get(i);
                DataSet second = dataSets.get(i + 1);
                // Teste si 2 éléments successifs sont dans le bon ordre ou non
                if (first.getLastModified().before(second.getLastModified())) {
                    // s'ils ne le sont pas, on échange leurs positions
                    tampon = first;
                    dataSets.set(i, second);
                    dataSets.set(i + 1, tampon);
                    permut = true;
                }
            }
        } while (permut);
        return dataSets;
    }

    public static List<DataSet> sortByTitle(List<DataSet> dataSets) {
        int longueur = dataSets.size();
        DataSet tampon;
        boolean permut;
        do {
            // hypothèse : le tableau est trié
            permut = false;
            for (int i = 0; i < longueur - 1; i++) {
                DataSet first = dataSets.get(i);
                DataSet second = dataSets.get(i + 1);
                // Teste si 2 éléments successifs sont dans le bon ordre ou non
                if (first.getName().compareToIgnoreCase(second.getName()) > 0) {
                    // s'ils ne le sont pas, on échange leurs positions
                    tampon = first;
                    dataSets.set(i, second);
                    dataSets.set(i + 1, tampon);
                    permut = true;
                }
            }
        } while (permut);
        return dataSets;
    }
}

