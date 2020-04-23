package utility;

import java.util.List;

public class ClientMessage extends Message {
    /* Define operation type of the message */
    public enum Action {
        LIST,
        UPDATE,
        SHARE,
        ACCEPT, //accept invitation of sharing
    }

    private Account account;
    private Action action;
    private List<File> files;
    private long fileID;

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account user) {
        this.account = user;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<File> getFiles() {
        return this.files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public long getFileID() {
        return fileID;
    }

    public void setFileID(long fileID) {
        this.fileID = fileID;
    }
}
