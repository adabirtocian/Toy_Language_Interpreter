import Model.ADTs.Dictionary.Dictionary;
import Model.ADTs.Dictionary.IDictionary;
import Model.ADTs.IList;
import Model.ADTs.List;
import Model.Exceptions.EmptyStackException;
import Model.Exceptions.MyException;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Tests_ADTs {
    @Test
    void testDict(){
        IDictionary<String, IValue> symbolTable = new Dictionary<>();
        IValue value = new IntValue(4);
        IValue value1 = new IntValue(24);
        symbolTable.add("v", value);

        assertEquals(4, ((IntValue)symbolTable.lookup("v")).getValue());
        MyException exception  = assertThrows(EmptyStackException.class, () -> symbolTable.add("v", value1));
        assertEquals("Duplicate key", exception.getMessage());
    }

    @Test
    void testList(){
        IList<IValue> out = new List<>();
        IValue value = new BoolValue(true);
        out.add(value);

        assertTrue(((BoolValue) out.pop()).getValue());
    }
}
