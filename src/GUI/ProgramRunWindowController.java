package GUI;

import Controller.Controller;
import Model.ADTs.IList;
import Model.Exceptions.MyException;
import Model.ProgramState;
import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.BufferedReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Stack;

public class ProgramRunWindowController implements Initializable {

    private ProgramState currentProgramState;
    private Controller controller;
    private List<ProgramState> programStates;
    private URL location;
    private ResourceBundle resourceBundle;

    @FXML
    private TextField programState;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private ListView<String> programStateIds;

    @FXML
    private ListView<String> exeStack;

    @FXML
    private TableView<Pair<String, String>> symbolTable;

    @FXML
    private ListView<String> fileTable;

    @FXML
    private TableView<Pair<Integer, String>> heapTable;

    @FXML
    private ListView<String> outputList;

    @FXML
    private Button runButton;

    @FXML
    private Button exitButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(this.controller == null) {
            this.location = url;
            this.resourceBundle = resourceBundle;
        }
        else {
            this.programStates = this.controller.getRepository().getProgramStateList();
            this.currentProgramState = this.programStates.get(0);

            this.controller.createThreadPool();
            this.programState.setText(this.currentProgramState.getOriginalProgram().toString());
            this.setTextFieldUneditable(this.programState);
            this.setTextFieldUneditable(this.numberOfProgramStates);

            this.initializeTableHeaders();
        }
    }

    private void initializeTableHeaders() {
        TableColumn<Pair<Integer, String>, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        TableColumn<Pair<Integer, String>, String> valueColumn = new TableColumn<>("Value");
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.heapTable.getColumns().addAll(addressColumn, valueColumn);

        TableColumn<Pair<String, String>, String> variableNameColumn = new TableColumn<>("Variable Name");
        variableNameColumn.setCellValueFactory(new PropertyValueFactory<>("key"));
        TableColumn<Pair<String, String>, String> variableValueColumn = new TableColumn<>("Value");
        variableValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        this.symbolTable.getColumns().addAll(variableNameColumn, variableValueColumn);

    }

    public void setControllerForProgram(Controller controller) {
        this.controller = controller;
        this.initialize(this.location, this.resourceBundle);
    }

    private void setTextFieldUneditable(TextInputControl textField) {
        textField.setEditable(false);
    }

    public void populateExeStack(ProgramState programState) throws MyException {
        this.exeStack.getItems().clear();
        Stack<IStatement> exeStack =  programState.getExeStack().getContent();
        Stack<?> cloneExeStack = (Stack<?>) exeStack.clone();
        while(! cloneExeStack.empty()) {
            this.exeStack.getItems().add(cloneExeStack.pop().toString());
        }
    }

    public void populateOutputList() throws MyException{
        this.outputList.getItems().clear();
        IList<IValue> outputList = this.currentProgramState.getOut();
        for(int i=0; i<outputList.size(); i++) {
            IValue value = outputList.getValue(i);
            this.outputList.getItems().add(value.toString());
        }
    }

    public void populateFileList() throws MyException{
        this.fileTable.getItems().clear();
        Map<StringValue, BufferedReader> fileTable = this.currentProgramState.getFileTable().getContent();
        for(StringValue key: fileTable.keySet()) {
            BufferedReader value = fileTable.get(key);
            this.fileTable.getItems().add(key.toString() + " -> " + value.toString());
        }
    }

    public void populateHeapTable() throws MyException{
        this.heapTable.getItems().clear();
        ObservableList<Pair<Integer, String>> heapData = FXCollections.observableArrayList();
        Map<Integer, IValue> heapTable = this.currentProgramState.getHeapTable().getContent();

        for(Integer key: heapTable.keySet()) {
            IValue value = heapTable.get(key);
            heapData.add(new Pair<>(key, value.toString()));
        }
        this.heapTable.setItems(heapData);
    }

    public void populateSymbolTable(ProgramState programState) throws MyException {
        this.symbolTable.getItems().clear();
        ObservableList<Pair<String, String>> symbolData = FXCollections.observableArrayList();
        Map<String, IValue> symbolTable = programState.getSymbolTabel().getContent();
        for(String key: symbolTable.keySet()) {
            IValue value = symbolTable.get(key);
            symbolData.add(new Pair<>(key, value.toString()));
        }

        this.symbolTable.setItems(symbolData);
    }

    public void populateProgramStateIds() throws MyException {
        this.programStates = this.controller.getRepository().getProgramStateList();
        this.numberOfProgramStates.setText(Integer.toString(this.programStates.size()));

        this.programStateIds.getItems().clear();
        for(ProgramState programState: this.programStates) {
            this.programStateIds.getItems().add(Integer.toString(programState.getId()));
        }
    }

    public void populateBySelectedProgramStateId() throws MyException {
        String selectedProgramStateId = this.programStateIds.getSelectionModel().getSelectedItem();
        for(ProgramState programState: this.programStates) {
            if(Integer.toString(programState.getId()).equals(selectedProgramStateId) && this.currentProgramState != programState) {
                this.currentProgramState = programState;
                this.populateExeStack(this.currentProgramState);
                this.populateSymbolTable(this.currentProgramState);
            }
        }
    }

    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    public void runOneStep() {
        try {
            this.populateProgramStateIds();
            this.populateExeStack(this.currentProgramState);
            this.populateSymbolTable(this.currentProgramState);
            this.populateOutputList();
            this.populateFileList();
            this.populateHeapTable();

            this.controller.runOneStep();
        }
        catch (MyException exception) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, exception.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

}
