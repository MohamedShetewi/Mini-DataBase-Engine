import org.antlr.v4.runtime.DefaultErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;

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

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        try {
            throw new DBAppException("Wrong/Unsupported SQL Statement!");
        } catch (DBAppException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return super.recoverInline(recognizer);
    }
}
