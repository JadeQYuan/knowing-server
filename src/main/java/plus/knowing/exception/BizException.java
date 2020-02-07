package plus.knowing.exception;

import lombok.Getter;
import plus.knowing.exception.data.ExceptionEnum;

@Getter
public class BizException extends BaseException {

    public BizException(ExceptionEnum exception) {
        super(exception);
    }
}
