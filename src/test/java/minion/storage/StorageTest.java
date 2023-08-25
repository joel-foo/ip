package minion.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import minion.common.Messages;
import minion.data.TaskList;
import minion.data.task.Deadline;
import minion.data.task.Event;
import minion.data.task.ToDo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StorageTest {
    private TaskList tasks;
    private Storage storage;
    private static final String TEST_DATADIR_VALID = "test/data.txt";
    private static final String TEST_DATADIR_INVALID = "tmp/data.txt";
    @BeforeEach
    public void setUp() throws IOException {
        tasks = new TaskList();
        tasks.add(new ToDo("buy book"));
        tasks.add(new Deadline("return book", "Dec 3 2023 2:00 PM"));
        tasks.add(new Event("pool party", "Dec 3 2023 2:00 PM", "Dec 4 2023 2:30 AM"));
        File file = new File(TEST_DATADIR_VALID);
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(TEST_DATADIR_VALID);
        fw.write(tasks.toStringStorage());
        fw.close();
    }

    @Test
    public void load_fileFound_success() throws IOException {
        storage = new Storage(TEST_DATADIR_VALID);
        assertEquals(tasks.toString(), new TaskList(storage.load()).toString());
    }

    @Test
    public void load_fileNotFound_fail() throws IOException {
        storage = new Storage(TEST_DATADIR_INVALID);
        try {
            assertEquals(null, storage.load());
            fail();
        } catch (FileNotFoundException e) {
            assertEquals(Messages.MESSAGE_FILE_NOT_FOUND, e.getMessage());
        } catch (IOException e) {
            assertEquals(Messages.MESSAGE_IO_EXCEPTION, e.getMessage());
        }
    }

    public static void deleteDirAndFile(String... dirnames) {
        for (String dirname: dirnames) {
            File dirFile = new File(dirname);
            if (!dirFile.exists()) continue;
            File[] dirs = dirFile.listFiles();
            for (File file : dirs) {
                file.delete();
            }
            dirFile.delete();
        }
    }

    @AfterAll
    public static void tearDown() {
        deleteDirAndFile("test", "tmp");
    }
}