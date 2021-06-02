import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;

public class ParserErrorHandler extends DefaultErrorStrategy {
    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        try {
            throw new DBAppException("Wrong/Unsupported SQL Statement!");
        } catch (DBAppException dbAppException) {
            dbAppException.printStackTrace();
            System.exit(1);
        }
    }
}
