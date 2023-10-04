package mslearning.hrapigatway.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseBobyError {
    private String errorCode;

    private String message;

    public String toJson() {
        return "(\"status\": " + getErrorCode() + ", " +
                "\"message\": \"" + getMessage() + "\")";
    }
}
