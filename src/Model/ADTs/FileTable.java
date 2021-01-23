package Model.ADTs;


import Model.ADTs.Dictionary.Dictionary;
import Model.ADTs.Dictionary.IDictionary;

public class FileTable<T1,T2> extends Dictionary<T1,T2> implements IDictionary<T1,T2> {

    @Override
    public String toString() {
        return dictionary.keySet().toString();
    }
}
