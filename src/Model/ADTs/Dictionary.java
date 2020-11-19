package Model.ADTs;
import Model.Exceptions.InvalidKeyException;
import Model.Exceptions.MyException;
import java.util.HashMap;

public class Dictionary<T1,T2> implements IDictionary<T1,T2>{

    protected final HashMap<T1,T2> dictionary;

    public Dictionary() {
        this.dictionary = new HashMap<>();
    }

    @Override
    public void add(T1 key, T2 value) throws MyException {
        if(this.dictionary.containsKey(key)) {
            throw new InvalidKeyException("Duplicate key");
        }
        this.dictionary.put(key, value);
    }

    @Override
    public void update(T1 key, T2 newValue) throws MyException {
        if(! this.dictionary.containsKey(key)) {
            throw new InvalidKeyException("Inexistent key");
        }
        this.dictionary.replace(key, newValue);
    }

    @Override
    public void remove(T1 key) throws MyException {
        if(! this.dictionary.containsKey(key)) {
            throw new InvalidKeyException("Inexistent key");
        }
        this.dictionary.remove(key);
    }

    @Override
    public T2 lookup(T1 key) throws MyException {
        if(! this.dictionary.containsKey(key)) {
            throw new InvalidKeyException("Inexistent key");
        }
        return this.dictionary.get(key);
    }

    @Override
    public boolean isDefined(T1 key) {
        return this.dictionary.containsKey(key);
    }


    public String toString() {
        return this.dictionary.toString();
    }
}
