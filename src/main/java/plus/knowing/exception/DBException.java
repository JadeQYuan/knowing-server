package plus.knowing.exception;

import lombok.Getter;
import plus.knowing.exception.data.ExceptionEnum;

@Getter
public class DBException extends BaseException {

    public DBException(ExceptionEnum exception) {
        super(exception);
    }
}
