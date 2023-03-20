package com.homework.multilayerprog;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class uicontroler
{
    logic lg;
    @FXML
    ListView lv = new ListView();
    public uicontroler() throws IOException {
        lg = new logic("file.txt");
        for(int i=0;i!=lg.GetEndIndex();i++)
        lv.getItems().add(lg.getName(i));
    }


}
