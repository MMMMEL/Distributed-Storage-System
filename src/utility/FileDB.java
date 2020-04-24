package utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileDB {
    private Map<Long, File> files; /** key is file ID */

    public FileDB() {
        files = new HashMap<>();
    }

    public FileDB(FileDB fileDB) {
        files = new HashMap<>();
        files.putAll(fileDB.getFiles());
    }

    public Map<Long, File> getFiles() {
        return files;
    }

    /**
     * get files by list of file ids
     * @param fileIds
     * @return list of files
     */
    public List<File> getFileByIds(List<Long> fileIds) {
        List<File> list = new ArrayList<>();
        for (long id : fileIds) {
            // -- TO DO handle the case if there is no specific file id in files
            list.add(files.get(id));
        }
        return list;
    }

    public void addFile(Long id, File file) {
        files.put(id, file);
    }
}
