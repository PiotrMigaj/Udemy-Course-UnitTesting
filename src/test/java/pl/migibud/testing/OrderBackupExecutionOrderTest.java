package pl.migibud.testing;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderBackupExecutionOrderTest {

    @Test
    void callingBackupWithoutCreatingAFileFirstShouldThrowExeption() throws IOException{

        //given
        OrderBackup orderBackup = new OrderBackup();

        //then

        assertThrows(IOException.class,()->orderBackup.backupOrder(new Order()));
        //orderBackup.backupOrder(new Order());

    }
}
