package GUI;

import Controller.Controller;
import Model.ADTs.Dictionary.Dictionary;
import Model.ADTs.HeapTable.HeapTable;
import Model.ADTs.List.List;
import Model.ADTs.Stack.MyStack;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Repository.Repository;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GUIController implements Initializable {
    @FXML
    private ListView<String> programsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ProgramsGenerator programsGenerator = new ProgramsGenerator();
        for(IStatement program: programsGenerator.getPrograms()){
            this.programsList.getItems().add(program.toString());
        }
    }

    public void startProgram() {
        int selectedProgramIndex = programsList.getSelectionModel().getSelectedIndex();
        //System.out.println("Program: " + selectedProgramIndex);

        ProgramsGenerator programsGenerator = new ProgramsGenerator();
        IStatement selectedProgramStatement = programsGenerator.getPrograms().get(selectedProgramIndex);
        ProgramState programState = new ProgramState(
                new MyStack<>(),
                new Dictionary<>(),
                new List<>(),
                selectedProgramStatement,
                new Dictionary<>(),
                new HeapTable<>());
        int fileNumber = selectedProgramIndex + 1;
        Repository repository = new Repository("log" + fileNumber + ".txt");
        repository.addProgramState(programState);
        Controller controller = new Controller(repository);

        try {
            controller.typeCheck();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("programRunWindow.fxml"));
            Parent parent = loader.load();

            ProgramRunWindowController runWindowController = loader.getController();
            runWindowController.setControllerForProgram(controller);

            Stage newStage = new Stage();
            newStage.setScene(new Scene(parent, 1000, 700));
            newStage.show();

        }
        catch(MyException | IOException exception) {
            Alert alert = new Alert(Alert.AlertType.ERROR, exception.getMessage(), ButtonType.OK);
            alert.showAndWait();
            System.out.println(exception.getMessage());
        }
    }

}
