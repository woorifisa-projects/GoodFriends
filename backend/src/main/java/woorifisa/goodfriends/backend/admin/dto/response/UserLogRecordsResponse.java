package woorifisa.goodfriends.backend.admin.dto.response;

import java.util.List;

public class UserLogRecordsResponse {



    private List<UserLogRecordResponse> userLogRecord;

    public List<UserLogRecordResponse> getUserLogRecord() {
        return userLogRecord;
    }
    public UserLogRecordsResponse(List<UserLogRecordResponse> userLogRecord) {
        this.userLogRecord = userLogRecord;
    }
}
