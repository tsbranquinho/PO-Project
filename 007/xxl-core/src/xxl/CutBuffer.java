package xxl;

import java.util.TreeMap;
import xxl.content.Content;
import java.util.Map;
import java.util.ArrayList;


public class CutBuffer{

    private Map<String, Content> _contents = new TreeMap<>();
    private ArrayList<String> _coords = new ArrayList<>();
    
    private Gamma _gamma;
    
    public CutBuffer(Gamma gamma, MapCells storeData){

        ArrayList<String> coords = gamma.getCoords();
        int i = 1;

        //its implemented in a way that could be something different than a row/column
        for (String key : coords) {
            
            if(gamma.isHorizontalInterval()){
                String cutBuffKey = String.format("%d;%d", 1, i);
                _coords.add(cutBuffKey);
                Content content = storeData.getCell(key).getContent().shallowCopy();
                _contents.put(cutBuffKey, content);
            }
            else{
                String cutBuffKey = String.format("%d;%d", i, 1);
                _coords.add(cutBuffKey);
                Content content = storeData.getCell(key).getContent().shallowCopy();
                _contents.put(cutBuffKey, content);

            }
            i++;
        }
        String range = coords.get(0) + ":" + coords.get(coords.size()-1);
        _gamma = new Gamma(range);
    }

    public Gamma getGamma(){
        return _gamma;
    }
    public ArrayList<String> getCoords(){
        return _coords;
    }

    public Map<String, Content> getContents() {
        return _contents;
    }

    public String toString() {

        String printCells = "";
        
        for (String key : _contents.keySet()) {

            String[] parts = key.split(";");
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);

            if(row==1){
                printCells += 1 + ";" + col + "|" + _contents.get(key).toString() + "\n";
            }
            else{
                printCells += row + ";" + 1 + "|" + _contents.get(key).toString() + "\n";
            }
                  
        }     
       
        return printCells.substring(0, printCells.length()-1);
    }
}